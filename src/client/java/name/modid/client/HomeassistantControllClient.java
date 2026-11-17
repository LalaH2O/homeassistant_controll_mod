package name.modid.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.mojang.blaze3d.platform.InputConstants;

import name.modid.HomeassistantControll;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LightLayer;


public class HomeassistantControllClient implements ClientModInitializer {
	private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
	private static float lastHealth = -1.0F;
	private static int lastLight = -3;

	private static final KeyMapping OPEN_CONFIG_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
			"key.homeassistant-controll.open_config",
			InputConstants.Type.KEYBOARD,
			InputConstants.KEY_K,
			KeyMapping.Category.MISC
	));

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null) {
				lastHealth = -1.0F;
			} else {
				float currentHealth = client.player.getHealth();
				if (ModConfig.get().isMasterSwitchEnabled() && ModConfig.get().isHurtEnabled()
						&& lastHealth >= 0.0F && currentHealth < lastHealth) {
					HomeassistantControll.LOGGER.info("{} took damage", client.player.getName().getString());
					triggerHomeAssistantScript(ModConfig.get().getScriptId());
				}
				lastHealth = currentHealth;
			}

			while (OPEN_CONFIG_KEY.consumeClick()) {
				client.gui.setScreen(new ConfigScreen(
						Component.translatable("screen.homeassistant-controll.config.title")));
			}

			if (ModConfig.get().isMasterSwitchEnabled() && ModConfig.get().isLightLevelEnabled()
					&& client.player != null && client.level != null) {
				BlockPos standingOn = client.player.blockPosition();
					int skyLight = client.level.getBrightness(LightLayer.SKY, standingOn);
					int blockLight = client.level.getBrightness(LightLayer.BLOCK, standingOn);
					int totalLight = Math.max(skyLight, blockLight);

				if (Math.abs(totalLight - lastLight) > 1) {
					lastLight = totalLight;
					float brightness = totalLight * 100.0F / 15.0F;
					setLightBrightness(ModConfig.get().getLightsId(), brightness);
				}
			}

		});
	}

	private static void triggerHomeAssistantScript(String scriptId) {
		ModConfig config = ModConfig.get();
		if (config.getServerUrl().isBlank() || config.getAccessToken().isBlank() || scriptId.isBlank()) {
			HomeassistantControll.LOGGER.warn("Home Assistant configuration is incomplete; cannot trigger script");
			return;
		}

		String url = config.getServerUrl().replaceAll("/+$", "") + "/api/services/script/"
				+ removeEntityDomain(scriptId, "script");
		String body = "{}";

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.header("Authorization", "Bearer " + config.getAccessToken())
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(body))
					.build();

			HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
					.thenAccept(response -> {
						if (response.statusCode() / 100 != 2) {
							HomeassistantControll.LOGGER.error(
									"Home Assistant returned HTTP {}: {}",
									response.statusCode(),
									response.body()
							);
						}
					})
					.exceptionally(error -> {
						HomeassistantControll.LOGGER.error("Could not trigger Home Assistant script", error);
						return null;
					});
		} catch (IllegalArgumentException exception) {
			HomeassistantControll.LOGGER.error("Invalid Home Assistant server URL: {}", url, exception);
		}
	}
	private static void setLightBrightness(String lightId, float brightness) {
		ModConfig config = ModConfig.get();
		if (config.getServerUrl().isBlank() || config.getAccessToken().isBlank() || lightId.isBlank()) {
			HomeassistantControll.LOGGER.warn("Home Assistant configuration is incomplete; cannot change light");
			return;
		}

		String target = buildLightTarget(lightId);
		int brightnessPercent = Math.round(Math.max(0.0F, Math.min(100.0F, brightness)));

		if (brightnessPercent <= 1) {
			String url = config.getServerUrl().replaceAll("/+$", "") + "/api/services/light/turn_off";
			String body = """
					{%s}
					""".formatted(target);

			try {
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create(url))
						.header("Authorization", "Bearer " + config.getAccessToken())
						.header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(body))
						.build();

				HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
						.thenAccept(response -> {
							if (response.statusCode() / 100 != 2) {
								HomeassistantControll.LOGGER.error(
										"Home Assistant returned HTTP {}: {}",
										response.statusCode(),
										response.body()
								);
							}
						})
						.exceptionally((Throwable error) -> {
							HomeassistantControll.LOGGER.error("Could not set Home Assistant light brightness", error);
							return null;
						});
			} catch (IllegalArgumentException exception) {
				HomeassistantControll.LOGGER.error("Invalid Home Assistant server URL: {}", url, exception);
			}

			return;
		}

		String url = config.getServerUrl().replaceAll("/+$", "") + "/api/services/light/turn_on";
		String body = """
				{%s,"brightness_pct":%d}
				""".formatted(target, brightnessPercent);

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.header("Authorization", "Bearer " + config.getAccessToken())
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(body))
					.build();

			HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
					.thenAccept(response -> {
						if (response.statusCode() / 100 != 2) {
							HomeassistantControll.LOGGER.error(
									"Home Assistant returned HTTP {}: {}",
									response.statusCode(),
									response.body()
							);
						}
					})
					.exceptionally((Throwable error) -> {
						HomeassistantControll.LOGGER.error("Could not set Home Assistant light brightness", error);
						return null;
					});
		} catch (IllegalArgumentException exception) {
			HomeassistantControll.LOGGER.error("Invalid Home Assistant server URL: {}", url, exception);
		}
	}

	private static String normalizeEntityId(String entityId, String domain) {
		return entityId.contains(".") ? entityId : domain + "." + entityId;
	}

	private static String buildLightTarget(String configuredId) {
		String id = configuredId.trim();
		if (id.startsWith("area:")) {
			return "\"area_id\":\"" + id.substring("area:".length()) + "\"";
		}
		if (id.startsWith("room:")) {
			return "\"area_id\":\"" + id.substring("room:".length()) + "\"";
		}
		return "\"entity_id\":\"" + normalizeEntityId(id, "light") + "\"";
	}

	private static String removeEntityDomain(String entityId, String domain) {
		String prefix = domain + ".";
		return entityId.startsWith(prefix) ? entityId.substring(prefix.length()) : entityId;
	}

}
