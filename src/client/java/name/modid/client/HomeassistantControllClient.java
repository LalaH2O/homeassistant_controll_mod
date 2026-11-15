package name.modid.client;

import com.mojang.blaze3d.platform.InputConstants;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import name.modid.HomeassistantControll;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;

public class HomeassistantControllClient implements ClientModInitializer {
	private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
	private static float lastHealth = -1.0F;

	private static final KeyMapping OPEN_CONFIG_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
			"key.homeassistant-controll.open_config",
			InputConstants.Type.KEYBOARD,
			InputConstants.KEY_O,
			KeyMapping.Category.MISC
	));

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null) {
				lastHealth = -1.0F;
			} else {
				float currentHealth = client.player.getHealth();
				if (lastHealth >= 0.0F && currentHealth < lastHealth) {
					HomeassistantControll.LOGGER.info("{} took damage", client.player.getName().getString());
					triggerHomeAssistantScript();
				}
				lastHealth = currentHealth;
			}

			while (OPEN_CONFIG_KEY.consumeClick()) {
				client.gui.setScreen(new ConfigScreen(Component.literal("Home Assistant Control")));
			}
		});
	}

	private static void triggerHomeAssistantScript() {
		ModConfig config = ModConfig.get();
		if (config.getServerUrl().isBlank() || config.getAccessToken().isBlank() || config.getScriptId().isBlank()) {
			HomeassistantControll.LOGGER.warn("Home Assistant configuration is incomplete; cannot trigger script");
			return;
		}

		String url = config.getServerUrl().replaceAll("/+$", "") + "/api/services/script/flash_red";
		String body = """
				{"entity_id":"%s"}
				""".formatted(config.getScriptId());

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
}
