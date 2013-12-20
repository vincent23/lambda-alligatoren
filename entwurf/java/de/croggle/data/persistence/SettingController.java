package de.croggle.data.persistence;

import de.croggle.AlligatorApp;


/**
 * Controller, which handles the different settings are currently applied onto
 * the app.
 * 
 * @navassoc 1 - 1 de.croggle.data.persistence.Setting
 */
public class SettingController {

	/**
	 * The currently active setting.
	 */
	private Setting currentSetting;
	
	/**
	 * Backreference to the game.
	 */
	private AlligatorApp game;

	/**
	 * Creates a new SettingController. If a profile name is stored in the
	 * shared preferences the corresponding setting is loaded and stored in
	 * currentSetting.
	 *
	 * @param game The backreference to the central game object
	 */
	public SettingController(AlligatorApp game) {

	}

	/**
	 * Replaces the current setting with newSetting. newSetting gets stored in
	 * the database.
	 * 
	 * @param newSetting The setting used to replace the currently active setting.
	 */
	public void editCurrentSetting(Setting newSetting) {

	}

	/**
	 * Loads the setting, which belongs to the user identified with profile name
	 * and sets it as the current profile.
	 * 
	 * @param profileName The name of the user, whose settings are loaded.
	 */
	public void changeCurrentSetting(String profileName) {

	}

	/**
	 * Returns the current setting.
	 */
	public Setting getCurrentSetting() {
		return null;
	}



}
