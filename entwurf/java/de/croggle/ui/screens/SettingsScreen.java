package de.croggle.ui.screens;

import de.croggle.data.persistence.SettingController;

/**
 * Screen within which the player can see the chosen settings and change dem according to his will.
 * For reference see ``Pflichtenheft 10.5.10 / Abbildung 19''.
 * 
 * @depend - <uses> - de.croggle.data.persistence.SettingController
 */
public class SettingsScreen extends AbstractScreen {
	/**
	 * Creates the screen that is shown to the player while changing his profile's settings.
	 * @param controller the settings controller, which is responsible for the currently selected profile
	 */
	public SettingsScreen(SettingController controller) {

	}
}
