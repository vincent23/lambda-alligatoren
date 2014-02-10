package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingController;
import de.croggle.game.profile.Profile;
import de.croggle.game.profile.ProfileChangeListener;
import de.croggle.ui.ConfirmInterface;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.NotificationDialog;
import de.croggle.ui.actors.YesNoDialog;

/**
 * Screen within which the player can see the chosen settings and change dem
 * according to his will. For reference see ``Pflichtenheft 10.5.10 / Abbildung
 * 19''.
 */
public class SettingsScreen extends AbstractScreen implements
		ProfileChangeListener {

	CheckBox zoomCheckBox;
	CheckBox colorBlindnessCheckBox;
	Slider musicSlider;
	Slider effectsSlider;

	private final SettingController settingController;

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
	}

	@Override
	protected void initializeWidgets() {
		super.initializeWidgets();
		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		ImageButton back = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-back"));

		Table scrollTable = new Table();
		// ScrollPane scroll = new ScrollPane(scrollTable);

		Label gameplay = new Label(_("settings_title_gameplay"),
				helper.getLabelStyle(40));
		Label zoom = new Label(_("settings_title_zoom"), helper.getLabelStyle());
		Label colorBlindness = new Label(_("settings_title_color_blindness"),
				helper.getLabelStyle());
		Label sound = new Label(_("settings_title_sound"),
				helper.getLabelStyle(40));
		Label music = new Label(_("settings_title_music"),
				helper.getLabelStyle());
		Label effects = new Label(_("settings_title_effects"),
				helper.getLabelStyle());
		Label profile = new Label(_("settings_title_profile"),
				helper.getLabelStyle(40));

		zoomCheckBox = new CheckBox("", helper.getCheckBoxStyle());
		colorBlindnessCheckBox = new CheckBox("", helper.getCheckBoxStyle());

		musicSlider = new Slider(0, 100, 1, false, helper.getSliderStyle());
		effectsSlider = new Slider(0, 100, 1, false, helper.getSliderStyle());

		musicSlider.setValue(50);
		effectsSlider.setValue(50);

		TextButton editProfile = new TextButton(
				_("settings_button_edit_profile"), helper.getTextButtonStyle());

		// add listeners
		back.addListener(new LogicalPredecessorListener());

		SettingListener settingListener = new SettingListener();

		zoomCheckBox.addListener(settingListener);
		colorBlindnessCheckBox.addListener(settingListener);

		musicSlider.addListener(settingListener);
		effectsSlider.addListener(settingListener);

		editProfile.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (game.getProfileController().getCurrentProfile() != null) {
					Dialog dialog = new EditProfileDialog();
					dialog.show(stage);
				} else {
					Dialog dialog = new NotificationDialog(
							_("warning_no_profile_selected"));
					dialog.show(stage);
				}

			}
		});

		scrollTable.defaults().left().space(10);
		scrollTable.add(gameplay).row();
		scrollTable.add(zoom).expandX().padLeft(30);
		scrollTable.add(zoomCheckBox).size(50).center().row();
		scrollTable.add(colorBlindness).expandX().padLeft(30);
		scrollTable.add(colorBlindnessCheckBox).size(50).center().row();
		scrollTable.add(sound).row();
		scrollTable.add(music).padLeft(30);
		scrollTable.add(musicSlider).width(300).height(50).row();
		scrollTable.add(effects).padLeft(30);
		scrollTable.add(effectsSlider).width(300).height(50).row();
		scrollTable.add(profile).row();
		scrollTable.add(editProfile).width(300).height(75);

		scrollTable.pad(50).padRight(150);
		table.add(back).size(100).top().left();
		table.add(scrollTable).expand().fill();
		table.pad(30);

		onProfileChange(null);
	}

	@Override
	public void onProfileChange(Profile profile) {
		Setting setting = settingController.getCurrentSetting();
		if (setting != null && areWidgetsInitialized()) {
			musicSlider.setValue(setting.getVolumeMusic() * 100);
			effectsSlider.setValue(setting.getVolumeEffects() * 100);
			zoomCheckBox.setChecked(setting.isZoomEnabled());
			colorBlindnessCheckBox.setChecked(setting.isColorblindEnabled());
		} else if (areWidgetsInitialized()) {
			musicSlider.setValue(50);
			effectsSlider.setValue(50);
			zoomCheckBox.setChecked(false);
			colorBlindnessCheckBox.setChecked(false);

		}

	}

	private class SettingListener extends ChangeListener {

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			if (!(actor instanceof Slider) || !((Slider) actor).isDragging()) {
				Setting setting = new Setting(musicSlider.getValue() / 100,
						effectsSlider.getValue() / 100,
						zoomCheckBox.isChecked(),
						colorBlindnessCheckBox.isChecked());
				settingController.editCurrentSetting(setting);
			}
		}
	}

	private class EditProfileDialog extends Dialog {

		public EditProfileDialog() {
			super("", StyleHelper.getInstance().getDialogStyle());

			StyleHelper helper = StyleHelper.getInstance();
			Label message = new Label(_("title_edit_profile"),
					helper.getBlackLabelStyle());
			TextButton name = new TextButton(_("edit_profile_button_rename"),
					helper.getTextButtonStyle());
			TextButton avatar = new TextButton(
					_("edit_profile_button_change_avatar"),
					helper.getTextButtonStyle());
			TextButton delete = new TextButton(_("edit_profile_button_delete"),
					helper.getTextButtonStyle());
			TextButton nothing = new TextButton(
					_("edit_profile_button_nothing"),
					helper.getTextButtonStyle());
			name.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					game.getProfileSetNameScreen().setIsInEditMode(true);
					EditProfileDialog.this.hide();
					game.showProfileSetNameScreen(false);
				}
			});

			avatar.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					game.getProfileSetAvatarScreen().setInEditMode(true);
					EditProfileDialog.this.hide();
					game.showProfileSetAvatarScreen("", false);
				};
			});

			delete.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					Dialog dialog = new YesNoDialog(String.format(
							_("edit_profile_delete_confirmation"), game
									.getProfileController()
									.getCurrentProfileName()),
							new ConfirmInterface() {

								@Override
								public void yes() {
									game.getProfileController()
											.deleteCurrentProfile();
									game.showSelectProfileScreen();

								}

								@Override
								public void no() {
									// TODO Auto-generated method stub

								}
							});
					dialog.show(stage);
					EditProfileDialog.this.hide();

				}
			});
			nothing.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					EditProfileDialog.this.hide();
				};

			});
			clear();
			add(message).center().pad(100).colspan(4).row();
			add(name).minWidth(150).height(70).pad(10);
			add(avatar).minWidth(150).height(70).pad(10);
			add(delete).minWidth(150).height(70).pad(10);
			add(nothing).minWidth(150).height(70).pad(10);

		}

	}

}
