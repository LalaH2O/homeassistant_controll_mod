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

		serverUrlField = createField(40, 35, config.getServerUrl(), "Server URL");
		accessTokenField = createField(40, 75, config.getAccessToken(), "Access token");
		scriptIdField = createField(40, 115, config.getScriptId(), "Script ID");
		lightsIdField = createField(40, 155, config.getLightsId(), "Light ID or area:<id>");
		
		hurtEnabled = config.isHurtEnabled();
		lightLevelEnabled = config.isLightLevelEnabled();
		masterSwitch = config.isMasterSwitchEnabled();

		this.addRenderableWidget(serverUrlField);
		this.addRenderableWidget(accessTokenField);
		this.addRenderableWidget(scriptIdField);
		this.addRenderableWidget(lightsIdField);

		this.addRenderableWidget(Button.builder(toggleLabel("Damage trigger", hurtEnabled), button -> {
			hurtEnabled = !hurtEnabled;
			button.setMessage(toggleLabel("Damage trigger", hurtEnabled));
		}).bounds(285, 115, 140, 20).build());

		this.addRenderableWidget(Button.builder(toggleLabel("Light-level sync", lightLevelEnabled), button -> {
			lightLevelEnabled = !lightLevelEnabled;
			button.setMessage(toggleLabel("Light-level sync", lightLevelEnabled));
		}).bounds(285, 155, 140, 20).build());

		this.addRenderableWidget(Button.builder(toggleLabel("Master switch", masterSwitch), button -> {
			masterSwitch = !masterSwitch;
			button.setMessage(toggleLabel("Master switch", masterSwitch));
		}).bounds(285, 75, 140, 20).build());

		this.addRenderableWidget(Button.builder(Component.literal("Save"), button -> {
			config.setValues(serverUrlField.getValue(), accessTokenField.getValue(), scriptIdField.getValue(),
					lightsIdField.getValue(), hurtEnabled, lightLevelEnabled, masterSwitch);
			config.save();
			this.minecraft.gui.setScreen(null);
		}).bounds(40, 240, 100, 20).build());

		this.addRenderableWidget(Button.builder(Component.literal("Cancel"), button -> this.minecraft.gui.setScreen(null))
				.bounds(150, 240, 100, 20).build());
	}

	private Component toggleLabel(String name, boolean enabled) {
		return Component.literal(name + ": " + (enabled ? "ON" : "OFF"));
	}

	private EditBox createField(int x, int y, String value, String hint) {
		EditBox field = new EditBox(this.font, x, y, 240, 20, Component.literal(hint));
		field.setMaxLength(512);
		field.setValue(value);
		return field;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractRenderState(graphics, mouseX, mouseY, delta);

		graphics.text(this.font, "Server URL", 40, 25, 0xFFFFFFFF, true);
		graphics.text(this.font, "Access token", 40, 65, 0xFFFFFFFF, true);
		graphics.text(this.font, "Script ID", 40, 105, 0xFFFFFFFF, true);
		graphics.text(this.font, "Light ID or area:<id>", 40, 145, 0xFFFFFFFF, true);
	}
}
