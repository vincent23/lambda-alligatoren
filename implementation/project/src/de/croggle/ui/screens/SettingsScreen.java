package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingController;
import de.croggle.game.profile.OnProfileChangeListener;
import de.croggle.ui.StyleHelper;

/**
 * Screen within which the player can see the chosen settings and change dem
 * according to his will. For reference see ``Pflichtenheft 10.5.10 / Abbildung
 * 19''.
 */
public class SettingsScreen extends AbstractScreen implements
		OnProfileChangeListener {

	CheckBox zoomCheckBox;
	CheckBox colorBlindnessCheckBox;
	Slider musicSlider;
	Slider effectsSlider;

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
	public SettingsScreen(AlligatorApp game) {
		super(game);
		settingController = game.getSettingController();

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		ImageButton back = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-back"));

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

		zoomCheckBox = new CheckBox("", helper.getCheckBoxStyle());
		colorBlindnessCheckBox = new CheckBox("", helper.getCheckBoxStyle());

		musicSlider = new Slider(0, 100, 1, false, helper.getSliderStyle());
		effectsSlider = new Slider(0, 100, 1, false, helper.getSliderStyle());

		TextButton editProfile = new TextButton("Edit Profile",
				helper.getTextButtonStyle());

		// add listeners
		back.addListener(new BackButtonListener());
		back.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Setting setting = new Setting(musicSlider.getValue() / 100,
						effectsSlider.getValue() / 100, zoomCheckBox
								.isChecked(), colorBlindnessCheckBox
								.isChecked());
				settingController.editCurrentSetting(setting);
			}
		});

		Setting setting = settingController.getCurrentSetting();
		if (setting != null) {
			musicSlider.setValue(setting.getVolumeMusic());
			effectsSlider.setValue(setting.getVolumeEffects());
			zoomCheckBox.setChecked(setting.isZoomEnabled());
			colorBlindnessCheckBox.setChecked(setting.isColorblindEnabled());
		}

		scrollTable.defaults().left().space(10);
		scrollTable.add(gameplay).row();
		scrollTable.add(zoom).expandX().padLeft(30);
		scrollTable.add(zoomCheckBox).size(50).row();
		scrollTable.add(colorBlindness).expandX().padLeft(30);
		scrollTable.add(colorBlindnessCheckBox).size(50).row();
		scrollTable.add(sound).row();
		scrollTable.add(music).padLeft(30);
		scrollTable.add(musicSlider).width(300).height(50).row();
		scrollTable.add(effects).padLeft(30);
		scrollTable.add(effectsSlider).width(300).height(50).row();
		scrollTable.add(profile).row();
		scrollTable.add(editProfile).width(200).height(75);

		scrollTable.pad(50).padRight(200);
		table.add(back).size(100).top().left();
		table.add(scrollTable).expand().fill();
		table.pad(30);
	}

	@Override
	public void onProfileChange(String name) {
		Setting setting = settingController.getCurrentSetting();
		if (setting != null) {
			musicSlider.setValue(setting.getVolumeMusic() * 100);
			effectsSlider.setValue(setting.getVolumeEffects() * 100);
			zoomCheckBox.setChecked(setting.isZoomEnabled());
			colorBlindnessCheckBox.setChecked(setting.isColorblindEnabled());
		}

	}

	private class EditProfileDialog extends Dialog {

		public EditProfileDialog() {
			super("", StyleHelper.getInstance().getDialogStyle());

			StyleHelper helper = StyleHelper.getInstance();
			TextButton name = new TextButton("Rename",
					helper.getTextButtonStyle());
			TextButton avatar = new TextButton("Change Avatar",
					helper.getTextButtonStyle());
			TextButton delete = new TextButton("Delete Profile",
					helper.getTextButtonStyle());

			clear();

		}
	}
}
