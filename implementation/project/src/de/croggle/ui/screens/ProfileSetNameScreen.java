package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.ProfileController;

/**
 * Screen which is used for both creating a new account with a given name as
 * well as changing the name of an existing account. For reference see
 * ``Pflichtenheft 10.5.13 / Abbildung 22''.
 */
public class ProfileSetNameScreen extends AbstractScreen {
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
	public ProfileSetNameScreen(AlligatorApp game, ProfileController controller) {
		super(game);
	}
}
