package de.croggle.ui.screens;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.SettingController;

/**
 * Screen within which the player can see the chosen settings and change dem
 * according to his will. For reference see ``Pflichtenheft 10.5.10 / Abbildung
 * 19''.
 */
public class SettingsScreen extends AbstractScreen {
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
	}
}
