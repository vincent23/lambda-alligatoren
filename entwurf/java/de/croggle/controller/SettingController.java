package de.croggle.controller;



import de.croggle.data.persistence.Setting;
import android.content.SharedPreferences;

public class SettingController {

	/**
	* The currently active setting.
	*/
	private Setting currentSetting;
	
	/**
	* Creates a new SettingController. If a profile name is stored in the shared preferences the corresponding 
	* setting is loaded and stored in currentSetting.
	*/
	public SettingController() {
		
	}
	
	/**
	 * Replaces the current setting with newSetting. newSetting gets stored in the database.
	 * @param newSetting The setting used to replace the currently active setting.
	 */
	public void editCurrentSetting(Setting newSetting) {
		
	}
	
	/**
	 * Loads the setting which belongs to the user identified with profile name and sets it as the current profile.
	 * @param profileName The name of the user which settings are loaded.
	 */
	public void changeCurrentSetting(String profileName) {
		
	}
	
	/**
	 * Returns the current setting.
	 */
	public Setting getCurrentSetting() {
		return null;
	}
	
	/**
	 * Applies the current setting. For example the music volume is set to the value stored in currentSetting.
	 * This method gets called after a new setting is loaded.
	 */
	private void applySetting() {
		
	}
	
}
