package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.ProfileController;

/**
 * Screen which is used for both creating a new account with a given avatar as
 * well as changing the avatar of an existing account. For reference see
 * ``Pflichtenheft 10.5.14 / Abbildung 23''.
 */
public class ProfileSetAvatarScreen extends AbstractScreen {
	/**
	 * Creates the screen that is shown to the player while changing his player
	 * avatar.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the profile controller, which is responsible for the currently
	 *            selected profile
	 */
	public ProfileSetAvatarScreen(AlligatorApp game,
			ProfileController controller) {
		super(game);
	}
}
