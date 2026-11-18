package name.modid.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;
import name.modid.HomeassistantControll;

public final class ModConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("homeassistant-controll.json");
	private static final ModConfig INSTANCE = load();

	private String serverUrl = "";
	private String accessToken = "";
	@SerializedName(value = "scriptId", alternate = "entityId")
	private String scriptId = "";
	private String lightsId = "";
	private boolean hurt = true;
	private boolean lightlevel = true;
	private boolean masterSwitch = true;

	private String endabsorptionScript = "";
	private String absorptionScript = "";
	private String endunluckScript = "";
	private String unluckScript = "";
	private String endbad_omenScript = "";
	private String bad_omenScript = "";
	private String endblindnessScript = "";
	private String blindnessScript = "";
	private String endbreath_of_the_nautilusScript = "";
	private String breath_of_the_nautilusScript = "";
	private String endconduit_powerScript = "";
	private String conduit_powerScript = "";
	private String enddarknessScript = "";
	private String darknessScript = "";
	private String enddolphins_graceScript = "";
	private String dolphins_graceScript = "";
	private String endfire_resistanceScript = "";
	private String fire_resistanceScript = "";
	private String endfreezingScript = "";
	private String freezingScript = "";
	private String endglowingScript = "";
	private String glowingScript = "";
	private String endhasteScript = "";
	private String hasteScript = "";
	private String endhealth_boostScript = "";
	private String health_boostScript = "";
	private String endhero_of_the_villageScript = "";
	private String hero_of_the_villageScript = "";
	private String endhungerScript = "";
	private String hungerScript = "";
	private String endinfestedScript = "";
	private String infestedScript = "";
	private String endinstant_damageScript = "";
	private String instant_damageScript = "";
	private String endinstant_healthScript = "";
	private String instant_healthScript = "";
	private String endinvisibilityScript = "";
	private String invisibilityScript = "";
	private String endjump_boostScript = "";
	private String jump_boostScript = "";
	private String endlevitationScript = "";
	private String levitationScript = "";
	private String endluckScript = "";
	private String luckScript = "";
	private String endmining_fatigueScript = "";
	private String mining_fatigueScript = "";
	private String endnauseaScript = "";
	private String nauseaScript = "";
	private String endnight_visionScript = "";
	private String night_visionScript = "";
	private String endoozingScript = "";
	private String oozingScript = "";
	private String endpoisonScript = "";
	private String poisonScript = "";
	private String endraid_omenScript = "";
	private String raid_omenScript = "";
	private String endregenerationScript = "";
	private String regenerationScript = "";
	private String endresistanceScript = "";
	private String resistanceScript = "";
	private String endsaturationScript = "";
	private String saturationScript = "";
	private String endslow_fallingScript = "";
	private String slow_fallingScript = "";
	private String endspeedScript = "";
	private String speedScript = "";
	private String endstrengthScript = "";
	private String strengthScript = "";
	private String endtrial_omenScript = "";
	private String trial_omenScript = "";
	private String endwater_breathingScript = "";
	private String water_breathingScript = "";
	private String endweaknessScript = "";
	private String weaknessScript = "";
	private String endweavingScript = "";
	private String weavingScript = "";
	private String endwind_chargeScript = "";
	private String wind_chargeScript = "";
	private String endwitherScript = "";
	private String witherScript = "";

	private String fireScript = "";
	private String endfireScript = "";

	public static ModConfig get() {
		return INSTANCE;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getScriptId() {
		return scriptId;
	}

	public String getLightsId() {
		return lightsId;
	}

	public boolean isHurtEnabled() {
		return hurt;
	}

	public boolean isLightLevelEnabled() {
		return lightlevel;
	}

	public boolean isMasterSwitchEnabled() {
		return masterSwitch;
	}

	public void setValues(String serverUrl, String accessToken, String scriptId, String lightsId,
			boolean hurt, boolean lightlevel, boolean masterSwitch) {
		this.serverUrl = serverUrl;
		this.accessToken = accessToken;
		this.scriptId = scriptId;
		this.lightsId = lightsId;
		this.hurt = hurt;
		this.lightlevel = lightlevel;
		this.masterSwitch = masterSwitch;
	}

	public String getabsorptionScript() {
		return absorptionScript;
	}

	public String getunluckScript() {
		return unluckScript;
	}

	public String getbad_omenScript() {
		return bad_omenScript;
	}

	public String getblindnessScript() {
		return blindnessScript;
	}

	public String getbreath_of_the_nautilusScript() {
		return breath_of_the_nautilusScript;
	}

	public String getconduit_powerScript() {
		return conduit_powerScript;
	}

	public String getdarknessScript() {
		return darknessScript;
	}

	public String getdolphins_graceScript() {
		return dolphins_graceScript;
	}

	public String getfire_resistanceScript() {
		return fire_resistanceScript;
	}

	public String getfreezingScript() {
		return freezingScript;
	}

	public String getglowingScript() {
		return glowingScript;
	}

	public String gethasteScript() {
		return hasteScript;
	}

	public String gethealth_boostScript() {
		return health_boostScript;
	}

	public String gethero_of_the_villageScript() {
		return hero_of_the_villageScript;
	}

	public String gethungerScript() {
		return hungerScript;
	}

	public String getinfestedScript() {
		return infestedScript;
	}

	public String getinstant_damageScript() {
		return instant_damageScript;
	}

	public String getinstant_healthScript() {
		return instant_healthScript;
	}

	public String getinvisibilityScript() {
		return invisibilityScript;
	}

	public String getjump_boostScript() {
		return jump_boostScript;
	}

	public String getlevitationScript() {
		return levitationScript;
	}

	public String getluckScript() {
		return luckScript;
	}

	public String getmining_fatigueScript() {
		return mining_fatigueScript;
	}

	public String getnauseaScript() {
		return nauseaScript;
	}

	public String getnight_visionScript() {
		return night_visionScript;
	}

	public String getoozingScript() {
		return oozingScript;
	}

	public String getpoisonScript() {
		return poisonScript;
	}

	public String getraid_omenScript() {
		return raid_omenScript;
	}

	public String getregenerationScript() {
		return regenerationScript;
	}

	public String getresistanceScript() {
		return resistanceScript;
	}

	public String getsaturationScript() {
		return saturationScript;
	}

	public String getslow_fallingScript() {
		return slow_fallingScript;
	}

	public String getspeedScript() {
		return speedScript;
	}

	public String getstrengthScript() {
		return strengthScript;
	}

	public String gettrial_omenScript() {
		return trial_omenScript;
	}

	public String getwater_breathingScript() {
		return water_breathingScript;
	}

	public String getweaknessScript() {
		return weaknessScript;
	}

	public String getweavingScript() {
		return weavingScript;
	}

	public String getwind_chargeScript() {
		return wind_chargeScript;
	}

	public String getwitherScript() {
		return witherScript;
	}
	
	public void setValuesEffects(String absorptionScript, 
								 String unluckScript,
								 String bad_omenScript,
								 String blindnessScript,
								 String breath_of_the_nautilusScript,
								 String conduit_powerScript,
								 String darknessScript,
								 String dolphins_graceScript,
								 String fire_resistanceScript,
								 String freezingScript,
								 String glowingScript,
								 String hasteScript,
								 String health_boostScript,
								 String hero_of_the_villageScript,
								 String hungerScript,
								 String infestedScript,
								 String instant_damageScript,
								 String instant_healthScript,
								 String invisibilityScript,
								 String jump_boostScript,
								 String levitationScript,
								 String luckScript,
								 String mining_fatigueScript,
								 String nauseaScript,
								 String night_visionScript,
								 String oozingScript,
								 String poisonScript,
								 String raid_omenScript,
								 String regenerationScript,
								 String resistanceScript,
								 String saturationScript,
								 String slow_fallingScript,
								 String speedScript,
								 String strengthScript,
								 String trial_omenScript,
								 String water_breathingScript,
								 String weaknessScript,
								 String weavingScript,
								 String wind_chargeScript,
								 String witherScript) {

		this.absorptionScript = absorptionScript;
		this.unluckScript = unluckScript;
		this.bad_omenScript = bad_omenScript;
		this.blindnessScript = blindnessScript;
		this.breath_of_the_nautilusScript = breath_of_the_nautilusScript;
		this.conduit_powerScript = conduit_powerScript;
		this.darknessScript = darknessScript;
		this.dolphins_graceScript = dolphins_graceScript;
		this.fire_resistanceScript = fire_resistanceScript;
		this.freezingScript = freezingScript;
		this.glowingScript = glowingScript;
		this.hasteScript = hasteScript;
		this.health_boostScript = health_boostScript;
		this.hero_of_the_villageScript = hero_of_the_villageScript;
		this.hungerScript = hungerScript;
		this.infestedScript = infestedScript;
		this.instant_damageScript = instant_damageScript;
		this.instant_healthScript = instant_healthScript;
		this.invisibilityScript = invisibilityScript;
		this.jump_boostScript = jump_boostScript;
		this.levitationScript = levitationScript;
		this.luckScript = luckScript;
		this.mining_fatigueScript = mining_fatigueScript;
		this.nauseaScript = nauseaScript;
		this.night_visionScript = night_visionScript;
		this.oozingScript = oozingScript;
		this.poisonScript = poisonScript;
		this.raid_omenScript = raid_omenScript;
		this.regenerationScript = regenerationScript;
		this.resistanceScript = resistanceScript;
		this.saturationScript = saturationScript;
		this.slow_fallingScript = slow_fallingScript;
		this.speedScript = speedScript;
		this.strengthScript = strengthScript;
		this.trial_omenScript = trial_omenScript;
		this.water_breathingScript = water_breathingScript;
		this.weaknessScript = weaknessScript;
		this.weavingScript = weavingScript;
		this.wind_chargeScript = wind_chargeScript;
		this.witherScript = witherScript;
	}

	public String getendabsorptionScript() {
		return endabsorptionScript; 
	}
	public String getendunluckScript() {
		return endunluckScript; 
	}
	public String getendbad_omenScript() {
		return endbad_omenScript; 
	}
	public String getendblindnessScript() {
		return endblindnessScript; 
	}
	public String getendbreath_of_the_nautilusScript() {
		return endbreath_of_the_nautilusScript; 
	}
	public String getendconduit_powerScript() {
		return endconduit_powerScript; 
	}
	public String getenddarknessScript() {
		return enddarknessScript; 
	}
	public String getenddolphins_graceScript() {
		return enddolphins_graceScript; 
	}
	public String getendfire_resistanceScript() {
		return endfire_resistanceScript; 
	}
	public String getendfreezingScript() {
		return endfreezingScript; 
	}
	public String getendglowingScript() {
		return endglowingScript; 
	}
	public String getendhasteScript() {
		return endhasteScript; 
	}
	public String getendhealth_boostScript() {
		return endhealth_boostScript; 
	}
	public String getendhero_of_the_villageScript() {
		return endhero_of_the_villageScript; 
	}
	public String getendhungerScript() {
		return endhungerScript; 
	}
	public String getendinfestedScript() {
		return endinfestedScript; 
	}
	public String getendinstant_damageScript() {
		return endinstant_damageScript; 
	}
	public String getendinstant_healthScript() {
		return endinstant_healthScript; 
	}
	public String getendinvisibilityScript() {
		return endinvisibilityScript; 
	}
	public String getendjump_boostScript() {
		return endjump_boostScript; 
	}
	public String getendlevitationScript() {
		return endlevitationScript; 
	}
	public String getendluckScript() {
		return endluckScript; 
	}
	public String getendmining_fatigueScript() {
		return endmining_fatigueScript; 
	}
	public String getendnauseaScript() {
		return endnauseaScript; 
	}
	public String getendnight_visionScript() {
		return endnight_visionScript; 
	}
	public String getendoozingScript() {
		return endoozingScript; 
	}
	public String getendpoisonScript() {
		return endpoisonScript; 
	}
	public String getendraid_omenScript() {
		return endraid_omenScript; 
	}
	public String getendregenerationScript() {
		return endregenerationScript; 
	}
	public String getendresistanceScript() {
		return endresistanceScript; 
	}
	public String getendsaturationScript() {
		return endsaturationScript; 
	}
	public String getendslow_fallingScript() {
		return endslow_fallingScript; 
	}
	public String getendspeedScript() {
		return endspeedScript; 
	}
	public String getendstrengthScript() {
		return endstrengthScript; 
	}
	public String getendtrial_omenScript() {
		return endtrial_omenScript; 
	}
	public String getendwater_breathingScript() {
		return endwater_breathingScript; 
	}
	public String getendweaknessScript() {
		return endweaknessScript; 
	}
	public String getendweavingScript() {
		return endweavingScript; 
	}
	public String getendwind_chargeScript() {
		return endwind_chargeScript; 
	}
	public String getendwitherScript() {
		return endwitherScript; 
	}

	public void setendValuesEffects(String endabsorptionScript, 
								 String endunluckScript,
								 String endbad_omenScript,
								 String endblindnessScript,
								 String endbreath_of_the_nautilusScript,
								 String endconduit_powerScript,
								 String enddarknessScript,
								 String enddolphins_graceScript,
								 String endfire_resistanceScript,
								 String endfreezingScript,
								 String endglowingScript,
								 String endhasteScript,
								 String endhealth_boostScript,
								 String endhero_of_the_villageScript,
								 String endhungerScript,
								 String endinfestedScript,
								 String endinstant_damageScript,
								 String endinstant_healthScript,
								 String endinvisibilityScript,
								 String endjump_boostScript,
								 String endlevitationScript,
								 String endluckScript,
								 String endmining_fatigueScript,
								 String endnauseaScript,
								 String endnight_visionScript,
								 String endoozingScript,
								 String endpoisonScript,
								 String endraid_omenScript,
								 String endregenerationScript,
								 String endresistanceScript,
								 String endsaturationScript,
								 String endslow_fallingScript,
								 String endspeedScript,
								 String endstrengthScript,
								 String endtrial_omenScript,
								 String endwater_breathingScript,
								 String endweaknessScript,
								 String endweavingScript,
								 String endwind_chargeScript,
								 String endwitherScript) {

		this.endabsorptionScript = endabsorptionScript;
		this.endunluckScript = endunluckScript;
		this.endbad_omenScript = endbad_omenScript;
		this.endblindnessScript = endblindnessScript;
		this.endbreath_of_the_nautilusScript = endbreath_of_the_nautilusScript;
		this.endconduit_powerScript = endconduit_powerScript;
		this.enddarknessScript = enddarknessScript;
		this.enddolphins_graceScript = enddolphins_graceScript;
		this.endfire_resistanceScript = endfire_resistanceScript;
		this.endfreezingScript = endfreezingScript;
		this.endglowingScript = endglowingScript;
		this.endhasteScript = endhasteScript;
		this.endhealth_boostScript = endhealth_boostScript;
		this.endhero_of_the_villageScript = endhero_of_the_villageScript;
		this.endhungerScript = endhungerScript;
		this.endinfestedScript = endinfestedScript;
		this.endinstant_damageScript = endinstant_damageScript;
		this.endinstant_healthScript = endinstant_healthScript;
		this.endinvisibilityScript = endinvisibilityScript;
		this.endjump_boostScript = endjump_boostScript;
		this.endlevitationScript = endlevitationScript;
		this.endluckScript = endluckScript;
		this.endmining_fatigueScript = endmining_fatigueScript;
		this.endnauseaScript = endnauseaScript;
		this.endnight_visionScript = endnight_visionScript;
		this.endoozingScript = endoozingScript;
		this.endpoisonScript = endpoisonScript;
		this.endraid_omenScript = endraid_omenScript;
		this.endregenerationScript = endregenerationScript;
		this.endresistanceScript = endresistanceScript;
		this.endsaturationScript = endsaturationScript;
		this.endslow_fallingScript = endslow_fallingScript;
		this.endspeedScript = endspeedScript;
		this.endstrengthScript = endstrengthScript;
		this.endtrial_omenScript = endtrial_omenScript;
		this.endwater_breathingScript = endwater_breathingScript;
		this.endweaknessScript = endweaknessScript;
		this.endweavingScript = endweavingScript;
		this.endwind_chargeScript = endwind_chargeScript;
		this.endwitherScript = endwitherScript;
	}

	public void setFireScript(String fireScript, String endfireScript) {
		this.fireScript = fireScript;
		this.endfireScript = endfireScript;
	}

	public String getfireScript() {
		return fireScript;
	}

	public String getendfireScript() {
		return endfireScript;
	}

	public void save() {
		try {
			Files.createDirectories(FILE.getParent());
			try (Writer writer = Files.newBufferedWriter(FILE)) {
				GSON.toJson(this, writer);
			}
		} catch (IOException exception) {
			HomeassistantControll.LOGGER.error("Could not save configuration to {}", FILE, exception);
		}
	}

	private static ModConfig load() {
		if (!Files.exists(FILE)) {
			return new ModConfig();
		}

		try (Reader reader = Files.newBufferedReader(FILE)) {
			ModConfig config = GSON.fromJson(reader, ModConfig.class);
			return config == null ? new ModConfig() : config;
		} catch (IOException | JsonParseException exception) {
			HomeassistantControll.LOGGER.error("Could not load configuration from {}", FILE, exception);
			return new ModConfig();
		}
	}
}
