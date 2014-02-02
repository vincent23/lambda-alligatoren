package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.ProfileController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.NotificationDialog;

/**
 * Screen which is used for both creating a new account with a given name as
 * well as changing the name of an existing account. For reference see
 * ``Pflichtenheft 10.5.13 / Abbildung 22''.
 */
public class ProfileSetNameScreen extends AbstractScreen {

	private ProfileController profileController;
	private TextField nameInput;
	private String profileName;
	private boolean isInEditMode = false;

	/**
	 * Creates the screen that is shown to the player while changing his player
	 * name.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the profile controller, which is responsible for the currently
	 *            selected profile
	 */
	public ProfileSetNameScreen(AlligatorApp game) {
		super(game);
		profileController = game.getProfileController();

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		Table innerTable = new Table();
		Label askName = new Label(_("screen_title_set_name"),
				helper.getBlackLabelStyle());
		nameInput = new TextField("", helper.getTextFieldStyle());
		ImageButton next = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-next"));
		ImageButton back = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-back"));

		// any longer name won't fit on the profile button
		nameInput.setMaxLength(20);

		// add listeners
		back.addListener(new LogicalPredecessorListener());
		next.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				String name = nameInput.getText();

				if (name.length() != 0
						&& profileController.isValidUserName(name)) {
					if (isInEditMode) {
						profileController.editCurrentProfile(name,
								profileController.getCurrentProfile()
										.getPicturePath());
						isInEditMode = false;
						game.showSettingsScreen(false);
					} else {
						game.showProfileSetAvatarScreen(name, false);

					}

				} else if (name.length() == 0) {
					Dialog notification = new NotificationDialog(
							"I guess your name isn't empty?");
					notification.show(stage);
				} else if (!profileController.isValidUserName(name)) {
					Dialog notification = new NotificationDialog(
							"This name is already in use");
					notification.show(stage);
				}

			}
		});

		innerTable.setBackground(helper
				.getDrawable("widgets/default-background"));
		innerTable.pad(30);
		innerTable.add(askName).left().padLeft(100).height(50);
		innerTable.row();
		innerTable.add(nameInput).width(500).height(50).space(30);
		innerTable.row();
		innerTable.add(back).size(100).expand().left().bottom();
		innerTable.add(next).size(100).expand().right().bottom();

		table.add(innerTable).width(700).height(350);

	}

	@Override
	protected void onShow() {
		nameInput.setText("");
	}

	public void setIsInEditMode(boolean isInEditMode) {
		this.isInEditMode = isInEditMode;
	}

}
