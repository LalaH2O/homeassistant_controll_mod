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
