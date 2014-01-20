package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.SettingController;
import de.croggle.ui.StyleHelper;

/**
 * Screen within which the player can see the chosen settings and change dem
 * according to his will. For reference see ``Pflichtenheft 10.5.10 / Abbildung
 * 19''.
 */
public class SettingsScreen extends AbstractScreen {

	private SettingController settingController;

	/**
	 * Creates the screen that is shown to the player while changing his
	 * profile's settings.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the settings controller, which is responsible for the
	 *            currently selected profile
	 */
	public SettingsScreen(AlligatorApp game, SettingController controller) {
		super(game);
		settingController = controller;

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		ImageButton back = new ImageButton(
				helper.getImageButtonStyleRound("widgets/dummy-icon"));

		Table scrollTable = new Table();
		// ScrollPane scroll = new ScrollPane(scrollTable);

		Label gameplay = new Label("Gameplay", helper.getLabelStyle());
		Label zoom = new Label("Zoom Buttons On", helper.getLabelStyle());
		Label colorBlindness = new Label("Color Blindness Mode",
				helper.getLabelStyle());
		Label sound = new Label("Sound", helper.getLabelStyle());
		Label music = new Label("Music", helper.getLabelStyle());
		Label effects = new Label("Effects", helper.getLabelStyle());
		Label profile = new Label("Profile", helper.getLabelStyle());

		CheckBox zoomCheckBox = new CheckBox("", helper.getCheckBoxStyle());
		CheckBox colorBlindnessCheckBox = new CheckBox("",
				helper.getCheckBoxStyle());

		Slider musicSlider = new Slider(0, 100, 1, false,
				helper.getSliderStyle());
		Slider effectsSlider = new Slider(0, 100, 1, false,
				helper.getSliderStyle());

		TextButton editProfile = new TextButton("Edit Profile",
				helper.getTextButtonStyle());

		// add listeners
		back.addListener(new BackButtonListener());

		scrollTable.defaults().left().space(20);
		scrollTable.add(gameplay).row();
		scrollTable.add(zoom).expandX().padLeft(30);
		scrollTable.add(zoomCheckBox).row();
		scrollTable.add(colorBlindness).expandX().padLeft(30);
		scrollTable.add(colorBlindnessCheckBox).row();
		scrollTable.add(sound).row();
		scrollTable.add(music).padLeft(30);
		scrollTable.add(musicSlider).width(screenWidth / 4).row();
		scrollTable.add(effects).padLeft(30);
		scrollTable.add(effectsSlider).width(screenWidth / 4).row();
		scrollTable.add(profile).row();
		scrollTable.add(editProfile).width(screenWidth / 5);

		scrollTable.pad(50).padRight(screenWidth / 5);
		table.add(back).width(screenWidth / 10).height(screenWidth / 10).top()
				.left();
		table.add(scrollTable).expand().fill();
		table.pad(30);
	}
}
