package de.croggle.data.persistence;

import java.util.ArrayList;
import java.util.List;

import de.croggle.AlligatorApp;

/**
 * Controller which handles the different settings currently applied.
 */
public class SettingController {

	/**
	 * The currently active setting.
	 */
	private Setting currentSetting = new Setting();

	/**
	 * The backreference to the central game object.
	 */
	private AlligatorApp game;
	
	private List<SettingChangeListener> listeners = new ArrayList<SettingChangeListener>();

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
		if (!profileName.equals("")) {
			game.getPersistenceManager().editSetting(profileName, newSetting);
		} 
		updateListeners();
	}

	/**
	 * Loads the setting which belongs to the user identified with the profile
	 * name and sets it as the current setting.
	 * 
	 * @param profileName
	 *            the name of the user whose settings are loaded
	 */
	public void changeCurrentSetting(String profileName) {
		Setting setting = game.getPersistenceManager().getSetting(profileName);
		if (setting == null) {
			currentSetting = new Setting();
		} else {
			currentSetting = setting;
		}
		updateListeners();
	}

	/**
	 * Returns the current setting.
	 * 
	 * @return the currently active settings
	 */
	public Setting getCurrentSetting() {
		return currentSetting;
	}
	
	/**
	 * Add a listener to whom the updated current setting is passed after it was changed.
	 * @param listener the listener that receives the updated setting 
	 */
	public void addSettingChangeListener(SettingChangeListener listener) {
		listeners.add(listener);
	}
	
	private void updateListeners() {
		for (SettingChangeListener listener : listeners) {
			listener.onSettingChange(currentSetting);
		}
		
	}

}
