package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.ProfileController;
import de.croggle.ui.StyleHelper;

/**
 * Screen which is used for both creating a new account with a given name as
 * well as changing the name of an existing account. For reference see
 * ``Pflichtenheft 10.5.13 / Abbildung 22''.
 */
public class ProfileSetNameScreen extends AbstractScreen {

	private ProfileController profileController;

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
		Label askName = new Label("What's your name?", helper.getLabelStyle());
		TextField nameInput = new TextField("", helper.getTextFieldStyle());
		ImageButton next = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-play"));

		innerTable.setBackground(helper
				.getDrawable("widgets/default-background"));
		innerTable.pad(30);
		innerTable.add(askName).left().padLeft(100).height(50);
		innerTable.row();
		innerTable.add(nameInput).width(500).height(50).space(30);
		innerTable.row();
		innerTable.add(next).size(100).expand().right().bottom();

		table.add(innerTable).width(700).height(350);
	}
}
