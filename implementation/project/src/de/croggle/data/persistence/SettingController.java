package de.croggle.data.persistence;

import android.util.Log;
import de.croggle.AlligatorApp;

/**
 * Controller which handles the different settings currently applied.
 */
public class SettingController {

	/**
	 * The currently active setting.
	 */
	private Setting currentSetting;

	/**
	 * The backreference to the central game object.
	 */
	private AlligatorApp game;

	/**
	 * Creates a new SettingController. On initialization the active setting is
	 * set to null.
	 * 
	 * @param game
	 *            the backreference to the central game object
	 */
	public SettingController(AlligatorApp game) {
		this.game = game;
	}

	/**
	 * Replaces the current setting with a new one. The new setting gets stored
	 * in the database and overwrites the values of the old setting.
	 * 
	 * @param newSetting
	 *            the setting used to replace the currently active setting
	 */
	public void editCurrentSetting(Setting newSetting) {
		currentSetting = newSetting;
		String profileName = game.getProfileController()
				.getCurrentProfileName();
		game.getPersistenceManager().editSetting(profileName, newSetting);
	}

	/**
	 * Loads the setting which belongs to the user identified with the profile
	 * name and sets it as the current setting.
	 * 
	 * @param profileName
	 *            the name of the user whose settings are loaded
	 */
	public void changeCurrentSetting(String profileName) {
		currentSetting = game.getPersistenceManager().getSetting(profileName);
	}

	/**
	 * Returns the current setting.
	 * 
	 * @return the currently active settings
	 */
	public Setting getCurrentSetting() {
		return currentSetting;
	}

}
