package name.modid.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.util.LinkedHashMap;
import java.util.Map;

public class SecondarySettingsScreen extends Screen {
	private final Screen parent;
	private final Map<EditBox, Integer> fieldBaseY = new LinkedHashMap<>();
	private int scrollOffset;

	private EditBox absorptionScript;
	private EditBox unluckScript;
	private EditBox bad_omenScript;
	private EditBox blindnessScript;
	private EditBox breath_of_the_nautilusScript;
	private EditBox conduit_powerScript;
	private EditBox darknessScript;
	private EditBox dolphins_graceScript;
	private EditBox fire_resistanceScript;
	private EditBox freezingScript;
	private EditBox glowingScript;
	private EditBox hasteScript;
	private EditBox health_boostScript;
	private EditBox hero_of_the_villageScript;
	private EditBox hungerScript;
	private EditBox infestedScript;
	private EditBox instant_damageScript;
	private EditBox instant_healthScript;
	private EditBox invisibilityScript;
	private EditBox jump_boostScript;
	private EditBox levitationScript;
	private EditBox luckScript;
	private EditBox mining_fatigueScript;
	private EditBox nauseaScript;
	private EditBox night_visionScript;
	private EditBox oozingScript;
	private EditBox poisonScript;
	private EditBox raid_omenScript;
	private EditBox regenerationScript;
	private EditBox resistanceScript;
	private EditBox saturationScript;
	private EditBox slow_fallingScript;
	private EditBox speedScript;
	private EditBox strengthScript;
	private EditBox trial_omenScript;
	private EditBox water_breathingScript;
	private EditBox weaknessScript;
	private EditBox weavingScript;
	private EditBox wind_chargeScript;
	private EditBox witherScript;
	
	private EditBox endabsorptionScript;
	private EditBox endunluckScript;
	private EditBox endbad_omenScript;
	private EditBox endblindnessScript;
	private EditBox endbreath_of_the_nautilusScript;
	private EditBox endconduit_powerScript;
	private EditBox enddarknessScript;
	private EditBox enddolphins_graceScript;
	private EditBox endfire_resistanceScript;
	private EditBox endfreezingScript;
	private EditBox endglowingScript;
	private EditBox endhasteScript;
	private EditBox endhealth_boostScript;
	private EditBox endhero_of_the_villageScript;
	private EditBox endhungerScript;
	private EditBox endinfestedScript;
	private EditBox endinstant_damageScript;
	private EditBox endinstant_healthScript;
	private EditBox endinvisibilityScript;
	private EditBox endjump_boostScript;
	private EditBox endlevitationScript;
	private EditBox endluckScript;
	private EditBox endmining_fatigueScript;
	private EditBox endnauseaScript;
	private EditBox endnight_visionScript;
	private EditBox endoozingScript;
	private EditBox endpoisonScript;
	private EditBox endraid_omenScript;
	private EditBox endregenerationScript;
	private EditBox endresistanceScript;
	private EditBox endsaturationScript;
	private EditBox endslow_fallingScript;
	private EditBox endspeedScript;
	private EditBox endstrengthScript;
	private EditBox endtrial_omenScript;
	private EditBox endwater_breathingScript;
	private EditBox endweaknessScript;
	private EditBox endweavingScript;
	private EditBox endwind_chargeScript;
	private EditBox endwitherScript;

	private EditBox fireScript;
	private EditBox endfireScript;

	public SecondarySettingsScreen(Screen parent) {
		super(Component.translatable("screen.homeassistant-controll.secondary.title"));
		this.parent = parent;
	}

	@Override
	protected void init() {
		ModConfig config = ModConfig.get();

		int columnWidth = 280;
		int fieldWidth = 74;
		int startX = Math.max(10, (this.width - columnWidth * 2) / 2);
		int y = 86;
		int rowHeight = 40;

		int[] xPositions = {startX, startX + columnWidth};

		absorptionScript = createField(xPositions[0], y, fieldWidth, config.getabsorptionScript());
		endabsorptionScript = createField(xPositions[0] + 78, y, fieldWidth, config.getendabsorptionScript());
		unluckScript = createField(xPositions[0], y + rowHeight, fieldWidth, config.getunluckScript());
		endunluckScript = createField(xPositions[0] + 78, y + rowHeight, fieldWidth, config.getendunluckScript());
		bad_omenScript = createField(xPositions[0], y + rowHeight * 2, fieldWidth, config.getbad_omenScript());
		endbad_omenScript = createField(xPositions[0] + 78, y + rowHeight * 2, fieldWidth, config.getendbad_omenScript());
		blindnessScript = createField(xPositions[0], y + rowHeight * 3, fieldWidth, config.getblindnessScript());
		endblindnessScript = createField(xPositions[0] + 78, y + rowHeight * 3, fieldWidth, config.getendblindnessScript());
		breath_of_the_nautilusScript = createField(xPositions[0], y + rowHeight * 4, fieldWidth, config.getbreath_of_the_nautilusScript());
		endbreath_of_the_nautilusScript = createField(xPositions[0] + 78, y + rowHeight * 4, fieldWidth, config.getendbreath_of_the_nautilusScript());
		conduit_powerScript = createField(xPositions[0], y + rowHeight * 5, fieldWidth, config.getconduit_powerScript());
		endconduit_powerScript = createField(xPositions[0] + 78, y + rowHeight * 5, fieldWidth, config.getendconduit_powerScript());
		darknessScript = createField(xPositions[0], y + rowHeight * 6, fieldWidth, config.getdarknessScript());
		enddarknessScript = createField(xPositions[0] + 78, y + rowHeight * 6, fieldWidth, config.getenddarknessScript());
		dolphins_graceScript = createField(xPositions[0], y + rowHeight * 7, fieldWidth, config.getdolphins_graceScript());
		enddolphins_graceScript = createField(xPositions[0] + 78, y + rowHeight * 7, fieldWidth, config.getenddolphins_graceScript());
		fire_resistanceScript = createField(xPositions[0], y + rowHeight * 8, fieldWidth, config.getfire_resistanceScript());
		endfire_resistanceScript = createField(xPositions[0] + 78, y + rowHeight * 8, fieldWidth, config.getendfire_resistanceScript());
		freezingScript = createField(xPositions[0], y + rowHeight * 9, fieldWidth, config.getfreezingScript());
		endfreezingScript = createField(xPositions[0] + 78, y + rowHeight * 9, fieldWidth, config.getendfreezingScript());
		glowingScript = createField(xPositions[0], y + rowHeight * 10, fieldWidth, config.getglowingScript());
		endglowingScript = createField(xPositions[0] + 78, y + rowHeight * 10, fieldWidth, config.getendglowingScript());
		hasteScript = createField(xPositions[0], y + rowHeight * 11, fieldWidth, config.gethasteScript());
		endhasteScript = createField(xPositions[0] + 78, y + rowHeight * 11, fieldWidth, config.getendhasteScript());
		health_boostScript = createField(xPositions[0], y + rowHeight * 12, fieldWidth, config.gethealth_boostScript());
		endhealth_boostScript = createField(xPositions[0] + 78, y + rowHeight * 12, fieldWidth, config.getendhealth_boostScript());
		hero_of_the_villageScript = createField(xPositions[0], y + rowHeight * 13, fieldWidth, config.gethero_of_the_villageScript());
		endhero_of_the_villageScript = createField(xPositions[0] + 78, y + rowHeight * 13, fieldWidth, config.getendhero_of_the_villageScript());
		hungerScript = createField(xPositions[0], y + rowHeight * 14, fieldWidth, config.gethungerScript());
		endhungerScript = createField(xPositions[0] + 78, y + rowHeight * 14, fieldWidth, config.getendhungerScript());
		infestedScript = createField(xPositions[0], y + rowHeight * 15, fieldWidth, config.getinfestedScript());
		endinfestedScript = createField(xPositions[0] + 78, y + rowHeight * 15, fieldWidth, config.getendinfestedScript());
		instant_damageScript = createField(xPositions[0], y + rowHeight * 16, fieldWidth, config.getinstant_damageScript());
		endinstant_damageScript = createField(xPositions[0] + 78, y + rowHeight * 16, fieldWidth, config.getendinstant_damageScript());
		instant_healthScript = createField(xPositions[0], y + rowHeight * 17, fieldWidth, config.getinstant_healthScript());
		endinstant_healthScript = createField(xPositions[0] + 78, y + rowHeight * 17, fieldWidth, config.getendinstant_healthScript());
		invisibilityScript = createField(xPositions[0], y + rowHeight * 18, fieldWidth, config.getinvisibilityScript());
		endinvisibilityScript = createField(xPositions[0] + 78, y + rowHeight * 18, fieldWidth, config.getendinvisibilityScript());
		jump_boostScript = createField(xPositions[0], y + rowHeight * 19, fieldWidth, config.getjump_boostScript());
		endjump_boostScript = createField(xPositions[0] + 78, y + rowHeight * 19, fieldWidth, config.getendjump_boostScript());
		
		fireScript = createField(xPositions[0], y + rowHeight * 20, fieldWidth, config.getfireScript());
		endfireScript = createField(xPositions[0] + 78, y + rowHeight * 20, fieldWidth, config.getendfireScript());

		levitationScript = createField(xPositions[1], y, fieldWidth, config.getlevitationScript());
		endlevitationScript = createField(xPositions[1] + 78, y, fieldWidth, config.getendlevitationScript());
		luckScript = createField(xPositions[1], y + rowHeight, fieldWidth, config.getluckScript());
		endluckScript = createField(xPositions[1] + 78, y + rowHeight, fieldWidth, config.getendluckScript());
		mining_fatigueScript = createField(xPositions[1], y + rowHeight * 2, fieldWidth, config.getmining_fatigueScript());
		endmining_fatigueScript = createField(xPositions[1] + 78, y + rowHeight * 2, fieldWidth, config.getendmining_fatigueScript());
		nauseaScript = createField(xPositions[1], y + rowHeight * 3, fieldWidth, config.getnauseaScript());
		endnauseaScript = createField(xPositions[1] + 78, y + rowHeight * 3, fieldWidth, config.getendnauseaScript());
		night_visionScript = createField(xPositions[1], y + rowHeight * 4, fieldWidth, config.getnight_visionScript());
		endnight_visionScript = createField(xPositions[1] + 78, y + rowHeight * 4, fieldWidth, config.getendnight_visionScript());
		oozingScript = createField(xPositions[1], y + rowHeight * 5, fieldWidth, config.getoozingScript());
		endoozingScript = createField(xPositions[1] + 78, y + rowHeight * 5, fieldWidth, config.getendoozingScript());
		poisonScript = createField(xPositions[1], y + rowHeight * 6, fieldWidth, config.getpoisonScript());
		endpoisonScript = createField(xPositions[1] + 78, y + rowHeight * 6, fieldWidth, config.getendpoisonScript());
		raid_omenScript = createField(xPositions[1], y + rowHeight * 7, fieldWidth, config.getraid_omenScript());
		endraid_omenScript = createField(xPositions[1] + 78, y + rowHeight * 7, fieldWidth, config.getendraid_omenScript());
		regenerationScript = createField(xPositions[1], y + rowHeight * 8, fieldWidth, config.getregenerationScript());
		endregenerationScript = createField(xPositions[1] + 78, y + rowHeight * 8, fieldWidth, config.getendregenerationScript());
		resistanceScript = createField(xPositions[1], y + rowHeight * 9, fieldWidth, config.getresistanceScript());
		endresistanceScript = createField(xPositions[1] + 78, y + rowHeight * 9, fieldWidth, config.getendresistanceScript());
		saturationScript = createField(xPositions[1], y + rowHeight * 10, fieldWidth, config.getsaturationScript());
		endsaturationScript = createField(xPositions[1] + 78, y + rowHeight * 10, fieldWidth, config.getendsaturationScript());
		slow_fallingScript = createField(xPositions[1], y + rowHeight * 11, fieldWidth, config.getslow_fallingScript());
		endslow_fallingScript = createField(xPositions[1] + 78, y + rowHeight * 11, fieldWidth, config.getendslow_fallingScript());
		speedScript = createField(xPositions[1], y + rowHeight * 12, fieldWidth, config.getspeedScript());
		endspeedScript = createField(xPositions[1] + 78, y + rowHeight * 12, fieldWidth, config.getendspeedScript());
		strengthScript = createField(xPositions[1], y + rowHeight * 13, fieldWidth, config.getstrengthScript());
		endstrengthScript = createField(xPositions[1] + 78, y + rowHeight * 13, fieldWidth, config.getendstrengthScript());
		trial_omenScript = createField(xPositions[1], y + rowHeight * 14, fieldWidth, config.gettrial_omenScript());
		endtrial_omenScript = createField(xPositions[1] + 78, y + rowHeight * 14, fieldWidth, config.getendtrial_omenScript());
		water_breathingScript = createField(xPositions[1], y + rowHeight * 15, fieldWidth, config.getwater_breathingScript());
		endwater_breathingScript = createField(xPositions[1] + 78, y + rowHeight * 15, fieldWidth, config.getendwater_breathingScript());
		weaknessScript = createField(xPositions[1], y + rowHeight * 16, fieldWidth, config.getweaknessScript());
		endweaknessScript = createField(xPositions[1] + 78, y + rowHeight * 16, fieldWidth, config.getendweaknessScript());
		weavingScript = createField(xPositions[1], y + rowHeight * 17, fieldWidth, config.getweavingScript());
		endweavingScript = createField(xPositions[1] + 78, y + rowHeight * 17, fieldWidth, config.getendweavingScript());
		wind_chargeScript = createField(xPositions[1], y + rowHeight * 18, fieldWidth, config.getwind_chargeScript());
		endwind_chargeScript = createField(xPositions[1] + 78, y + rowHeight * 18, fieldWidth, config.getendwind_chargeScript());
		witherScript = createField(xPositions[1], y + rowHeight * 19, fieldWidth, config.getwitherScript());
		endwitherScript = createField(xPositions[1] + 78, y + rowHeight * 19, fieldWidth, config.getendwitherScript());

		this.addRenderableWidget(Button.builder(
				Component.translatable("screen.homeassistant-controll.secondary.save"),
				button -> {
					saveScriptIds();
					this.minecraft.gui.setScreen(this.parent);
				})
				.bounds(this.width / 2 - 110, this.height - 40, 100, 20).build());

		this.addRenderableWidget(Button.builder(
				Component.translatable("screen.homeassistant-controll.secondary.back"),
				button -> this.minecraft.gui.setScreen(this.parent))
				.bounds(this.width / 2 + 10, this.height - 40, 100, 20).build());

		updateScroll();
	}

	private void saveScriptIds() {
		ModConfig savedConfig = ModConfig.get();
		savedConfig.setValuesEffects(
				absorptionScript.getValue(), unluckScript.getValue(), bad_omenScript.getValue(),
				blindnessScript.getValue(), breath_of_the_nautilusScript.getValue(),
				conduit_powerScript.getValue(), darknessScript.getValue(), dolphins_graceScript.getValue(),
				fire_resistanceScript.getValue(), freezingScript.getValue(), glowingScript.getValue(),
				hasteScript.getValue(), health_boostScript.getValue(), hero_of_the_villageScript.getValue(),
				hungerScript.getValue(), infestedScript.getValue(), instant_damageScript.getValue(),
				instant_healthScript.getValue(), invisibilityScript.getValue(), jump_boostScript.getValue(),
				levitationScript.getValue(), luckScript.getValue(), mining_fatigueScript.getValue(),
				nauseaScript.getValue(), night_visionScript.getValue(), oozingScript.getValue(),
				poisonScript.getValue(), raid_omenScript.getValue(), regenerationScript.getValue(),
				resistanceScript.getValue(), saturationScript.getValue(), slow_fallingScript.getValue(),
				speedScript.getValue(), strengthScript.getValue(), trial_omenScript.getValue(),
				water_breathingScript.getValue(), weaknessScript.getValue(), weavingScript.getValue(),
				wind_chargeScript.getValue(), witherScript.getValue());
		savedConfig.setendValuesEffects(
				endabsorptionScript.getValue(), endunluckScript.getValue(), endbad_omenScript.getValue(),
				endblindnessScript.getValue(), endbreath_of_the_nautilusScript.getValue(),
				endconduit_powerScript.getValue(), enddarknessScript.getValue(), enddolphins_graceScript.getValue(),
				endfire_resistanceScript.getValue(), endfreezingScript.getValue(), endglowingScript.getValue(),
				endhasteScript.getValue(), endhealth_boostScript.getValue(), endhero_of_the_villageScript.getValue(),
				endhungerScript.getValue(), endinfestedScript.getValue(), endinstant_damageScript.getValue(),
				endinstant_healthScript.getValue(), endinvisibilityScript.getValue(), endjump_boostScript.getValue(),
				endlevitationScript.getValue(), endluckScript.getValue(), endmining_fatigueScript.getValue(),
				endnauseaScript.getValue(), endnight_visionScript.getValue(), endoozingScript.getValue(),
				endpoisonScript.getValue(), endraid_omenScript.getValue(), endregenerationScript.getValue(),
				endresistanceScript.getValue(), endsaturationScript.getValue(), endslow_fallingScript.getValue(),
				endspeedScript.getValue(), endstrengthScript.getValue(), endtrial_omenScript.getValue(),
				endwater_breathingScript.getValue(), endweaknessScript.getValue(), endweavingScript.getValue(),
				endwind_chargeScript.getValue(), endwitherScript.getValue());
		savedConfig.setFireScript(fireScript.getValue(), endfireScript.getValue());
		savedConfig.save();
	}

	private EditBox createField(int x, int y, int width, String value) {
		EditBox field = new EditBox(this.font, x, y, width, 20, Component.empty());
		field.setMaxLength(512);
		field.setValue(value);
		fieldBaseY.put(field, y);
		this.addRenderableWidget(field);
		return field;
	}

	private int visibleTop() {
		return 48;
	}

	private int visibleBottom() {
		return this.height - 50;
	}

	private int maxScrollOffset() {
		int contentBottom = 86 + 20 * 40 + 20;
		return Math.max(1, contentBottom - visibleBottom());
	}

	private void updateScroll() {
		scrollOffset = Math.max(0, Math.min(scrollOffset, maxScrollOffset()));
		int top = visibleTop();
		int bottom = visibleBottom();

		for (Map.Entry<EditBox, Integer> entry : fieldBaseY.entrySet()) {
			EditBox field = entry.getKey();
			int y = entry.getValue() - scrollOffset;
			field.setY(y);
			field.visible = y + 20 >= top && y <= bottom;
		}
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
		scrollOffset -= (int) (verticalAmount * 20);
		scrollOffset = Math.max(0, Math.min(scrollOffset, maxScrollOffset()));
		updateScroll();
		return true;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractRenderState(graphics, mouseX, mouseY, delta);

		Component title = Component.translatable("screen.homeassistant-controll.secondary.title");
		graphics.text(this.font, title, (this.width - this.font.width(title)) / 2, 20, 0xFFFFFFFF, true);

		int columnWidth = 280;
		int startX = Math.max(10, (this.width - columnWidth * 2) / 2);
		int labelY = 70;
		int[] xPositions = {startX, startX + columnWidth};
		Component startHint = Component.translatable("screen.homeassistant-controll.secondary.start");
		Component endHint = Component.translatable("screen.homeassistant-controll.secondary.end");

		for (int column = 0; column < xPositions.length; column++) {
			graphics.text(this.font, startHint, xPositions[column], 32, 0xFFFFFFFF, true);
			graphics.text(this.font, endHint, xPositions[column] + 78, 32, 0xFFFFFFFF, true);
		}

		String[][] effectKeys = {
				{"absorption", "unluck", "bad_omen", "blindness", "breath_of_the_nautilus",
						"conduit_power", "darkness", "dolphins_grace", "fire_resistance", "freezing",
						"glowing", "haste", "health_boost", "hero_of_the_village", "hunger",
						"infested", "instant_damage", "instant_health", "invisibility", "jump_boost", "fire"},
				{"levitation", "luck", "mining_fatigue", "nausea", "night_vision",
						"oozing", "poison", "raid_omen", "regeneration", "resistance",
						"saturation", "slow_falling", "speed", "strength", "trial_omen",
						"water_breathing", "weakness", "weaving", "wind_charge", "wither"}
		};

		int top = visibleTop();
		int bottom = visibleBottom();
		graphics.enableScissor(0, top, this.width, bottom);
		for (int column = 0; column < effectKeys.length; column++) {
			for (int row = 0; row < effectKeys[column].length; row++) {
				Component label = Component.translatable(effectTranslationKey(effectKeys[column][row]));
				int rowY = labelY + row * 40 - scrollOffset;
				graphics.text(this.font, label, xPositions[column], rowY, 0xFFFFFFFF, true);
			}
		}
		graphics.disableScissor();

		int maxScroll = maxScrollOffset();
		int scrollbarX = this.width - 8;
		int trackTop = top;
		int trackHeight = bottom - top;
		graphics.fill(scrollbarX, trackTop, scrollbarX + 5, bottom, 0x88000000);

		int thumbHeight = Math.max(20, trackHeight * trackHeight / (trackHeight + maxScroll));
		int thumbY = trackTop + (trackHeight - thumbHeight) * scrollOffset / maxScroll;
		graphics.fill(scrollbarX, thumbY, scrollbarX + 5, thumbY + thumbHeight, 0xCCFFFFFF);
	}

	private String effectTranslationKey(String effectId) {
		if (effectId.equals("fire") || effectId.equals("freezing")) {
			return "effect.homeassistant-controll." + effectId;
		}
		if (effectId.equals("wind_charge")) {
			return "effect.minecraft.wind_charged";
		}
		return "effect.minecraft." + effectId;
	}
}
