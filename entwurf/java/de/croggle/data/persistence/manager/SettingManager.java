package de.croggle.data.persistence.manager;


import de.croggle.data.persistence.Setting;
import android.content.Context;


/**
 * This class is responsible for managing the sqlite-table that stores the settings of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.data.persistence.Setting
 */
public class SettingManager extends TableManager {

	/**
	 * Name for the column that stores the profile names. Those names are used as the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";
	
	/**
	 * Name for the column that stores the volume of the music.
	 */
	static final String KEY_VOLUME_MUSIC = "volumeMusic";
	
	/**
	 * Name for the column that stores the volume of the effects.
	 */
	static final String KEY_VOLUME_EFFECTS = "volumeEffects";
	
	/**
	 * Name for the column that stores the information whether zoom is enabled or not.
	 */
	static final String KEY_ZOOM_ENABLED = "zoomEnabled";
	
	/**
	 * Name for the column that stores the information whether the colorblind mode is enabled or not.
	 */
	static final String KEY_COLORBLIND_ENABLED = "colorblindEnabled";

	/**
	 * The name of the table.
	 */
	static final String TABLE_NAME = "SettingTable";
	
	
	/**
	 * The string used to create the setting table via a sql query.
	 */
	static final String CREATE_TABLE = "null";
	
	/**
	 * Creates a new SettingManager used to manage the setting table.
	 * @param context
	 */
	SettingManager(Context context) {
		super(context);
	
	}
	
	/**
	 * Adds a new setting to the table, the parameter profileName is used as the primary key.
	 * @param profileName This string is used as the primary key for the setting.
	 * @param setting Contains the values to be stored in the table.
	 */
	void addSetting(String profileName, Setting setting) {
		
	}

	/**
	 * Searches the table for a setting which key matches profileName.
	 * @param profileName The key for the sought setting.
	 * @return Returns the found setting, if no setting is found, null is returned.
	 */
	Setting getSetting(String profileName) {
		/* TODO */
		return null;
	}

	/**
	 * Searches the table for a setting which key matches profileName and overwrites its values with the values stored in the parameter setting.
	 * @param profileName The key for the sought setting.
	 * @param setting The setting which values are used to overwrite the old setting.
	 */
	void updateSetting(String profileName, Setting setting) {
		
	}

	/**
	 * Deletes the table entry which key matches profileName.
	 * @param profileName The key of the entry which is to be deleted.
	 */
	void deleteSetting(String profileName) {
		
	}

}
