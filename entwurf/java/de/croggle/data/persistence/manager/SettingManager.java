package de.croggle.data.persistence.manager;


import de.croggle.data.persistence.Setting;
import android.content.Context;


/**
 * A concrete table manager which is responsible for managing the sqlite-table that stores the settings of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.data.persistence.Setting
 */
public class SettingManager extends TableManager {

	/**
	 * Name for the column that stores the profile names. The names are used as the primary key.
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
	 * Creates a new SettingManager which manages the setting table.
	 * @param context Used to access the database.
	 */
	SettingManager(Context context) {
		super(context);
	
	}
	
	/**
	 * Adds a new setting to the table, the parameter profileName identifies the profile to which the setting belongs.
	 * @param profileName the name of the profile which setting is added to the table
	 * @param setting contains the values to be stored in the table
	 */
	void addSetting(String profileName, Setting setting) {
		
	}

	/**
	 * Searches the table for a setting which belongs to the profile identified by profileName.
	 * @param profileName the name of the profile which setting is searched for
	 * @return the found setting, if no setting is found, null is returned
	 */
	Setting getSetting(String profileName) {
		
		return null;
	}

	/**
	 * Searches the table for a setting which belongs to the profile identified by profileName and overwrites its values with the values stored in the parameter setting.
	 * @param profileName the name of the profile which setting is updated
	 * @param setting the setting which values are used to overwrite the old setting
	 */
	void updateSetting(String profileName, Setting setting) {
		
	}

	/**
	 * Deletes the setting which belongs to the profile identified by profileName from the table.
	 * @param profileName the name of the profile which setting is deleted
	 */
	void deleteSetting(String profileName) {
		
	}

}
