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

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;


public class HomeassistantControllClient implements ClientModInitializer {
	private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
	private static float lastHealth = -1.0F;
	private static int lastLight = -3;

	private static boolean hadabsorption = false; 
	private static boolean hadunluck = false; 
	private static boolean hadbad_omen = false; 
	private static boolean hadblindness = false; 
	private static boolean hadbreath_of_the_nautilus = false; 
	private static boolean hadconduit_power = false; 
	private static boolean haddarkness = false; 
	private static boolean haddolphins_grace = false; 
	private static boolean hadfire_resistance = false; 
	private static boolean hadfreezing = false; 
	private static boolean hadglowing = false; 
	private static boolean hadhaste = false; 
	private static boolean hadhealth_boost = false; 
	private static boolean hadhero_of_the_village = false; 
	private static boolean hadhunger = false; 
	private static boolean hadinfested = false; 
	private static boolean hadinstant_damage = false; 
	private static boolean hadinstant_health = false; 
	private static boolean hadinvisibility = false; 
	private static boolean hadjump_boost = false; 
	private static boolean hadlevitation = false; 
	private static boolean hadluck = false; 
	private static boolean hadmining_fatigue = false; 
	private static boolean hadnausea = false; 
	private static boolean hadnight_vision = false; 
	private static boolean hadoozing = false; 
	private static boolean hadpoison = false; 
	private static boolean hadraid_omen = false; 
	private static boolean hadregeneration = false; 
	private static boolean hadresistance = false; 
	private static boolean hadsaturation = false; 
	private static boolean hadslow_falling = false; 
	private static boolean hadspeed = false; 
	private static boolean hadstrength = false; 
	private static boolean hadtrial_omen = false; 
	private static boolean hadwater_breathing = false; 
	private static boolean hadweakness = false; 
	private static boolean hadweaving = false; 
	private static boolean hadwind_charge = false; 
	private static boolean hadwither = false; 

	private static final KeyMapping OPEN_CONFIG_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
			"key.homeassistant-controll.open_config",
			InputConstants.Type.KEYBOARD,
			InputConstants.KEY_K,
			KeyMapping.Category.MISC
	));

	@Override
	public void onInitializeClient() {
		ModConfig config = ModConfig.get();
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			Player player = client.player;
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

			if (config.isMasterSwitchEnabled() && player != null) {

			if (player.hasEffect(MobEffects.ABSORPTION) && !hadabsorption) {hadabsorption = true; triggerHomeAssistantScript(config.getabsorptionScript());}
			if (player.hasEffect(MobEffects.UNLUCK) && !hadunluck) {hadunluck = true; triggerHomeAssistantScript(config.getunluckScript());}
			if (player.hasEffect(MobEffects.BAD_OMEN) && !hadbad_omen) {hadbad_omen = true; triggerHomeAssistantScript(config.getbad_omenScript());}
			if (player.hasEffect(MobEffects.BLINDNESS) && !hadblindness) {hadblindness = true; triggerHomeAssistantScript(config.getblindnessScript());}
			if (player.hasEffect(MobEffects.BREATH_OF_THE_NAUTILUS) && !hadbreath_of_the_nautilus) {hadbreath_of_the_nautilus = true; triggerHomeAssistantScript(config.getbreath_of_the_nautilusScript());}
			if (player.hasEffect(MobEffects.CONDUIT_POWER) && !hadconduit_power) {hadconduit_power = true; triggerHomeAssistantScript(config.getconduit_powerScript());}
			if (player.hasEffect(MobEffects.DARKNESS) && !haddarkness) {haddarkness = true; triggerHomeAssistantScript(config.getdarknessScript());}
			if (player.hasEffect(MobEffects.DOLPHINS_GRACE) && !haddolphins_grace) {haddolphins_grace = true; triggerHomeAssistantScript(config.getdolphins_graceScript());}
			if (player.hasEffect(MobEffects.FIRE_RESISTANCE) && !hadfire_resistance) {hadfire_resistance = true; triggerHomeAssistantScript(config.getfire_resistanceScript());}
			if ((player.getTicksFrozen() > 0) && !hadfreezing) {hadfreezing = true; triggerHomeAssistantScript(config.getfreezingScript());}
			if (player.hasEffect(MobEffects.GLOWING) && !hadglowing) {hadglowing = true; triggerHomeAssistantScript(config.getglowingScript());}
			if (player.hasEffect(MobEffects.HASTE) && !hadhaste) {hadhaste = true; triggerHomeAssistantScript(config.gethasteScript());}
			if (player.hasEffect(MobEffects.HEALTH_BOOST) && !hadhealth_boost) {hadhealth_boost = true; triggerHomeAssistantScript(config.gethealth_boostScript());}
			if (player.hasEffect(MobEffects.HERO_OF_THE_VILLAGE) && !hadhero_of_the_village) {hadhero_of_the_village = true; triggerHomeAssistantScript(config.gethero_of_the_villageScript());}
			if (player.hasEffect(MobEffects.HUNGER) && !hadhunger) {hadhunger = true; triggerHomeAssistantScript(config.gethungerScript());}
			if (player.hasEffect(MobEffects.INFESTED) && !hadinfested) {hadinfested = true; triggerHomeAssistantScript(config.getinfestedScript());}
			if (player.hasEffect(MobEffects.INSTANT_DAMAGE) && !hadinstant_damage) {hadinstant_damage = true; triggerHomeAssistantScript(config.getinstant_damageScript());}
			if (player.hasEffect(MobEffects.INSTANT_HEALTH) && !hadinstant_health) {hadinstant_health = true; triggerHomeAssistantScript(config.getinstant_healthScript());}
			if (player.hasEffect(MobEffects.INVISIBILITY) && !hadinvisibility) {hadinvisibility = true; triggerHomeAssistantScript(config.getinvisibilityScript());}
			if (player.hasEffect(MobEffects.JUMP_BOOST) && !hadjump_boost) {hadjump_boost = true; triggerHomeAssistantScript(config.getjump_boostScript());}
			if (player.hasEffect(MobEffects.LEVITATION) && !hadlevitation) {hadlevitation = true; triggerHomeAssistantScript(config.getlevitationScript());}
			if (player.hasEffect(MobEffects.LUCK) && !hadluck) {hadluck = true; triggerHomeAssistantScript(config.getluckScript());}
			if (player.hasEffect(MobEffects.MINING_FATIGUE) && !hadmining_fatigue) {hadmining_fatigue = true; triggerHomeAssistantScript(config.getmining_fatigueScript());}
			if (player.hasEffect(MobEffects.NAUSEA) && !hadnausea) {hadnausea = true; triggerHomeAssistantScript(config.getnauseaScript());}
			if (player.hasEffect(MobEffects.NIGHT_VISION) && !hadnight_vision) {hadnight_vision = true; triggerHomeAssistantScript(config.getnight_visionScript());}
			if (player.hasEffect(MobEffects.OOZING) && !hadoozing) {hadoozing = true; triggerHomeAssistantScript(config.getoozingScript());}
			if (player.hasEffect(MobEffects.POISON) && !hadpoison) {hadpoison = true; triggerHomeAssistantScript(config.getpoisonScript());}
			if (player.hasEffect(MobEffects.RAID_OMEN) && !hadraid_omen) {hadraid_omen = true; triggerHomeAssistantScript(config.getraid_omenScript());}
			if (player.hasEffect(MobEffects.REGENERATION) && !hadregeneration) {hadregeneration = true; triggerHomeAssistantScript(config.getregenerationScript());}
			if (player.hasEffect(MobEffects.RESISTANCE) && !hadresistance) {hadresistance = true; triggerHomeAssistantScript(config.getresistanceScript());}
			if (player.hasEffect(MobEffects.SATURATION) && !hadsaturation) {hadsaturation = true; triggerHomeAssistantScript(config.getsaturationScript());}
			if (player.hasEffect(MobEffects.SLOW_FALLING) && !hadslow_falling) {hadslow_falling = true; triggerHomeAssistantScript(config.getslow_fallingScript());}
			if (player.hasEffect(MobEffects.SPEED) && !hadspeed) {hadspeed = true; triggerHomeAssistantScript(config.getspeedScript());}
			if (player.hasEffect(MobEffects.STRENGTH) && !hadstrength) {hadstrength = true; triggerHomeAssistantScript(config.getstrengthScript());}
			if (player.hasEffect(MobEffects.TRIAL_OMEN) && !hadtrial_omen) {hadtrial_omen = true; triggerHomeAssistantScript(config.gettrial_omenScript());}
			if (player.hasEffect(MobEffects.WATER_BREATHING) && !hadwater_breathing) {hadwater_breathing = true; triggerHomeAssistantScript(config.getwater_breathingScript());}
			if (player.hasEffect(MobEffects.WEAKNESS) && !hadweakness) {hadweakness = true; triggerHomeAssistantScript(config.getweaknessScript());}
			if (player.hasEffect(MobEffects.WEAVING) && !hadweaving) {hadweaving = true; triggerHomeAssistantScript(config.getweavingScript());}
			if (player.hasEffect(MobEffects.WIND_CHARGED) && !hadwind_charge) {hadwind_charge = true; triggerHomeAssistantScript(config.getwind_chargeScript());}
			if (player.hasEffect(MobEffects.WITHER) && !hadwither) {hadwither = true; triggerHomeAssistantScript(config.getwitherScript());}

			if (hadabsorption && !player.hasEffect(MobEffects.ABSORPTION)) {hadabsorption = false; triggerHomeAssistantScript(config.getendabsorptionScript());}
			if (hadunluck && !player.hasEffect(MobEffects.UNLUCK)) {hadunluck = false; triggerHomeAssistantScript(config.getendunluckScript());}
			if (hadbad_omen && !player.hasEffect(MobEffects.BAD_OMEN)) {hadbad_omen = false; triggerHomeAssistantScript(config.getendbad_omenScript());}
			if (hadblindness && !player.hasEffect(MobEffects.BLINDNESS)) {hadblindness = false; triggerHomeAssistantScript(config.getendblindnessScript());}
			if (hadbreath_of_the_nautilus && !player.hasEffect(MobEffects.BREATH_OF_THE_NAUTILUS)) {hadbreath_of_the_nautilus = false; triggerHomeAssistantScript(config.getendbreath_of_the_nautilusScript());}
			if (hadconduit_power && !player.hasEffect(MobEffects.CONDUIT_POWER)) {hadconduit_power = false; triggerHomeAssistantScript(config.getendconduit_powerScript());}
			if (haddarkness && !player.hasEffect(MobEffects.DARKNESS)) {haddarkness = false; triggerHomeAssistantScript(config.getenddarknessScript());}
			if (haddolphins_grace && !player.hasEffect(MobEffects.DOLPHINS_GRACE)) {haddolphins_grace = false; triggerHomeAssistantScript(config.getenddolphins_graceScript());}
			if (hadfire_resistance && !player.hasEffect(MobEffects.FIRE_RESISTANCE)) {hadfire_resistance = false; triggerHomeAssistantScript(config.getendfire_resistanceScript());}
			if (hadfreezing && !(player.getTicksFrozen() > 0)) {hadfreezing = false; triggerHomeAssistantScript(config.getendfreezingScript());}
			if (hadglowing && !player.hasEffect(MobEffects.GLOWING)) {hadglowing = false; triggerHomeAssistantScript(config.getendglowingScript());}
			if (hadhaste && !player.hasEffect(MobEffects.HASTE)) {hadhaste = false; triggerHomeAssistantScript(config.getendhasteScript());}
			if (hadhealth_boost && !player.hasEffect(MobEffects.HEALTH_BOOST)) {hadhealth_boost = false; triggerHomeAssistantScript(config.getendhealth_boostScript());}
			if (hadhero_of_the_village && !player.hasEffect(MobEffects.HERO_OF_THE_VILLAGE)) {hadhero_of_the_village = false; triggerHomeAssistantScript(config.getendhero_of_the_villageScript());}
			if (hadhunger && !player.hasEffect(MobEffects.HUNGER)) {hadhunger = false; triggerHomeAssistantScript(config.getendhungerScript());}
			if (hadinfested && !player.hasEffect(MobEffects.INFESTED)) {hadinfested = false; triggerHomeAssistantScript(config.getendinfestedScript());}
			if (hadinstant_damage && !player.hasEffect(MobEffects.INSTANT_DAMAGE)) {hadinstant_damage = false; triggerHomeAssistantScript(config.getendinstant_damageScript());}
			if (hadinstant_health && !player.hasEffect(MobEffects.INSTANT_HEALTH)) {hadinstant_health = false; triggerHomeAssistantScript(config.getendinstant_healthScript());}
			if (hadinvisibility && !player.hasEffect(MobEffects.INVISIBILITY)) {hadinvisibility = false; triggerHomeAssistantScript(config.getendinvisibilityScript());}
			if (hadjump_boost && !player.hasEffect(MobEffects.JUMP_BOOST)) {hadjump_boost = false; triggerHomeAssistantScript(config.getendjump_boostScript());}
			if (hadlevitation && !player.hasEffect(MobEffects.LEVITATION)) {hadlevitation = false; triggerHomeAssistantScript(config.getendlevitationScript());}
			if (hadluck && !player.hasEffect(MobEffects.LUCK)) {hadluck = false; triggerHomeAssistantScript(config.getendluckScript());}
			if (hadmining_fatigue && !player.hasEffect(MobEffects.MINING_FATIGUE)) {hadmining_fatigue = false; triggerHomeAssistantScript(config.getendmining_fatigueScript());}
			if (hadnausea && !player.hasEffect(MobEffects.NAUSEA)) {hadnausea = false; triggerHomeAssistantScript(config.getendnauseaScript());}
			if (hadnight_vision && !player.hasEffect(MobEffects.NIGHT_VISION)) {hadnight_vision = false; triggerHomeAssistantScript(config.getendnight_visionScript());}
			if (hadoozing && !player.hasEffect(MobEffects.OOZING)) {hadoozing = false; triggerHomeAssistantScript(config.getendoozingScript());}
			if (hadpoison && !player.hasEffect(MobEffects.POISON)) {hadpoison = false; triggerHomeAssistantScript(config.getendpoisonScript());}
			if (hadraid_omen && !player.hasEffect(MobEffects.RAID_OMEN)) {hadraid_omen = false; triggerHomeAssistantScript(config.getendraid_omenScript());}
			if (hadregeneration && !player.hasEffect(MobEffects.REGENERATION)) {hadregeneration = false; triggerHomeAssistantScript(config.getendregenerationScript());}
			if (hadresistance && !player.hasEffect(MobEffects.RESISTANCE)) {hadresistance = false; triggerHomeAssistantScript(config.getendresistanceScript());}
			if (hadsaturation && !player.hasEffect(MobEffects.SATURATION)) {hadsaturation = false; triggerHomeAssistantScript(config.getendsaturationScript());}
			if (hadslow_falling && !player.hasEffect(MobEffects.SLOW_FALLING)) {hadslow_falling = false; triggerHomeAssistantScript(config.getendslow_fallingScript());}
			if (hadspeed && !player.hasEffect(MobEffects.SPEED)) {hadspeed = false; triggerHomeAssistantScript(config.getendspeedScript());}
			if (hadstrength && !player.hasEffect(MobEffects.STRENGTH)) {hadstrength = false; triggerHomeAssistantScript(config.getendstrengthScript());}
			if (hadtrial_omen && !player.hasEffect(MobEffects.TRIAL_OMEN)) {hadtrial_omen = false; triggerHomeAssistantScript(config.getendtrial_omenScript());}
			if (hadwater_breathing && !player.hasEffect(MobEffects.WATER_BREATHING)) {hadwater_breathing = false; triggerHomeAssistantScript(config.getendwater_breathingScript());}
			if (hadweakness && !player.hasEffect(MobEffects.WEAKNESS)) {hadweakness = false; triggerHomeAssistantScript(config.getendweaknessScript());}
			if (hadweaving && !player.hasEffect(MobEffects.WEAVING)) {hadweaving = false; triggerHomeAssistantScript(config.getendweavingScript());}
			if (hadwind_charge && !player.hasEffect(MobEffects.WIND_CHARGED)) {hadwind_charge = false; triggerHomeAssistantScript(config.getendwind_chargeScript());}
			if (hadwither && !player.hasEffect(MobEffects.WITHER)) {hadwither = false; triggerHomeAssistantScript(config.getendwitherScript());}

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
