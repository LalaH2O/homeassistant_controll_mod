package name.modid.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {
	private EditBox serverUrlField;
	private EditBox accessTokenField;
	private EditBox scriptIdField;
	private EditBox lightsIdField;
	private boolean hurtEnabled;
	private boolean lightLevelEnabled;
	private boolean masterSwitch;

	public ConfigScreen(Component title) {
		super(title);
	}

	@Override
	protected void init() {
		ModConfig config = ModConfig.get();

		serverUrlField = createField(40, 35, config.getServerUrl(), "screen.homeassistant-controll.config.server_url");
		accessTokenField = createField(40, 75, config.getAccessToken(), "screen.homeassistant-controll.config.access_token");
		scriptIdField = createField(40, 115, config.getScriptId(), "screen.homeassistant-controll.config.script_id");
		lightsIdField = createField(40, 155, config.getLightsId(), "screen.homeassistant-controll.config.light_id");
		
		hurtEnabled = config.isHurtEnabled();
		lightLevelEnabled = config.isLightLevelEnabled();
		masterSwitch = config.isMasterSwitchEnabled();

		this.addRenderableWidget(serverUrlField);
		this.addRenderableWidget(accessTokenField);
		this.addRenderableWidget(scriptIdField);
		this.addRenderableWidget(lightsIdField);

		this.addRenderableWidget(Button.builder(toggleLabel("screen.homeassistant-controll.config.damage_trigger", hurtEnabled), button -> {
			hurtEnabled = !hurtEnabled;
			button.setMessage(toggleLabel("screen.homeassistant-controll.config.damage_trigger", hurtEnabled));
		}).bounds(285, 115, 140, 20).build());

		this.addRenderableWidget(Button.builder(toggleLabel("screen.homeassistant-controll.config.light_level_sync", lightLevelEnabled), button -> {
			lightLevelEnabled = !lightLevelEnabled;
			button.setMessage(toggleLabel("screen.homeassistant-controll.config.light_level_sync", lightLevelEnabled));
		}).bounds(285, 155, 140, 20).build());

		this.addRenderableWidget(Button.builder(toggleLabel("screen.homeassistant-controll.config.master_switch", masterSwitch), button -> {
			masterSwitch = !masterSwitch;
			button.setMessage(toggleLabel("screen.homeassistant-controll.config.master_switch", masterSwitch));
		}).bounds(285, 75, 140, 20).build());

		this.addRenderableWidget(Button.builder(Component.translatable("screen.homeassistant-controll.config.save"), button -> {
			config.setValues(serverUrlField.getValue(), accessTokenField.getValue(), scriptIdField.getValue(),
					lightsIdField.getValue(), hurtEnabled, lightLevelEnabled, masterSwitch);
			config.save();
			this.minecraft.gui.setScreen(null);
		}).bounds(40, 240, 100, 20).build());

		this.addRenderableWidget(Button.builder(Component.translatable("screen.homeassistant-controll.config.cancel"), button -> this.minecraft.gui.setScreen(null))
				.bounds(150, 240, 100, 20).build());

		this.addRenderableWidget(Button.builder(
				Component.translatable("screen.homeassistant-controll.config.more_settings"),
				button -> this.minecraft.gui.setScreen(new SecondarySettingsScreen(this)))
				.bounds(260, 240, 160, 20).build());
	}

	private Component toggleLabel(String key, boolean enabled) {
		return Component.translatable(key, Component.translatable(
				enabled ? "screen.homeassistant-controll.config.on" : "screen.homeassistant-controll.config.off"));
	}

	private EditBox createField(int x, int y, String value, String hintKey) {
		EditBox field = new EditBox(this.font, x, y, 240, 20, Component.translatable(hintKey));
		field.setMaxLength(512);
		field.setValue(value);
		return field;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractRenderState(graphics, mouseX, mouseY, delta);

		graphics.text(this.font, Component.translatable("screen.homeassistant-controll.config.server_url"), 40, 25, 0xFFFFFFFF, true);
		graphics.text(this.font, Component.translatable("screen.homeassistant-controll.config.access_token"), 40, 65, 0xFFFFFFFF, true);
		graphics.text(this.font, Component.translatable("screen.homeassistant-controll.config.script_id"), 40, 105, 0xFFFFFFFF, true);
		graphics.text(this.font, Component.translatable("screen.homeassistant-controll.config.light_id"), 40, 145, 0xFFFFFFFF, true);
	}
}
