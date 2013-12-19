package de.croggle.data.persistence.manager;


import de.croggle.data.persistence.Setting;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * This class is responsible for managing the sqlite-table that stores the settings of the different profiles.
 * 
 * @navassoc 1 - * de.croggle.data.persistence.Setting
 */
public class SettingManager extends SQLiteOpenHelper {

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
	private static final String TABLE_NAME = "SettingTable";
	
	/**
	 * The version number of the table.
	 */
	private static final int TABLE_VERSION = 1;

	/**
	 * The string used to create the setting table via a sql query.
	 */
	private static final String CREATE_TABLE = "null";

	/**
	 * Creates an new SettingManager and the settings table if it does not already exists.
	 * @param context
	 */
	public SettingManager(Context context) {

		super(context, TABLE_NAME, null, TABLE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * Adds a new setting to the table, the parameter profileName is used as the primary key.
	 * @param profileName This string is used as the primary key for the setting.
	 * @param setting Contains the values to be stored in the table.
	 */
	public void addSetting(String profileName, Setting setting) {
		
	}

	/**
	 * Searches the table for a setting which key matches profileName.
	 * @param profileName The key for the sought setting.
	 * @return Returns the found setting, if no setting is found, null is returned.
	 */
	public Setting getSetting(String profileName) {
		/* TODO */
		return null;
	}

	/**
	 * Searches the table for a setting which key matches profileName and overwrites its values with the values stored in the parameter setting.
	 * @param profileName The key for the sought setting.
	 * @param setting The setting which values are used to overwrite the old setting.
	 */
	public void updateSetting(String profileName, Setting setting) {
		
	}

	/**
	 * Deletes the table entry which key matches profileName.
	 * @param profileName The key of the entry which is to be deleted.
	 */
	public void deleteSetting(String profileName) {
		
	}

}
