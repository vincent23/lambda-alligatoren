package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.ProfileController;

/**
 * Screen within which the player can change the name of his profile. For
 * reference see ``Pflichtenheft 10.5.12 / Abbildung 21''.
 */
public class SelectProfileScreen extends AbstractScreen {
	/**
	 * Creates the screen that is shown to the player while changing his
	 * profile.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the profile controller, which is responsible for the profiles
	 */
	public SelectProfileScreen(AlligatorApp game, ProfileController controller) {
		super(game);
	}
}
