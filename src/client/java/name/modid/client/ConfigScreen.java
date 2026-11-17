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

	public ConfigScreen(Component title) {
		super(title);
	}

	@Override
	protected void init() {
		ModConfig config = ModConfig.get();

		serverUrlField = createField(40, 35, config.getServerUrl(), "Server URL");
		accessTokenField = createField(40, 75, config.getAccessToken(), "Access token");
		scriptIdField = createField(40, 115, config.getScriptId(), "Script ID");
		lightsIdField = createField(40, 155, config.getLightsId(), "Light/Room ID");

		this.addRenderableWidget(serverUrlField);
		this.addRenderableWidget(accessTokenField);
		this.addRenderableWidget(scriptIdField);
		this.addRenderableWidget(lightsIdField);

		this.addRenderableWidget(Button.builder(Component.literal("Save"), button -> {
			config.setValues(serverUrlField.getValue(), accessTokenField.getValue(), scriptIdField.getValue(), lightsIdField.getValue());
			config.save();
			this.minecraft.gui.setScreen(null);
		}).bounds(40, 195, 100, 20).build());

		this.addRenderableWidget(Button.builder(Component.literal("Cancel"), button -> this.minecraft.gui.setScreen(null))
				.bounds(150, 195, 100, 20).build());
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
		graphics.text(this.font, "Light/Room ID", 40, 145, 0xFFFFFFFF, true);
	}
}
