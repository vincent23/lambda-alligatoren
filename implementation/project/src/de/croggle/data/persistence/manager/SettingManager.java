package de.croggle.data.persistence.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import de.croggle.data.persistence.Setting;

/**
 * A concrete table manager which is responsible for managing the SQLite table
 * that stores the settings of the different profiles.
 */
public class SettingManager extends TableManager {

	/**
	 * Name of the column that stores the profile names. The names are used as
	 * the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";

	/**
	 * Name of the column that stores the volume of the music.
	 */
	static final String KEY_VOLUME_MUSIC = "volumeMusic";

	/**
	 * Name of the column that stores the volume of the effects.
	 */
	static final String KEY_VOLUME_EFFECTS = "volumeEffects";

	/**
	 * Name of the column that stores the information whether zoom is enabled or
	 * not.
	 */
	static final String KEY_ZOOM_ENABLED = "zoomEnabled";

	/**
	 * Name of the column that stores the information whether the colorblind
	 * mode is enabled or not.
	 */
	static final String KEY_COLORBLIND_ENABLED = "colorblindEnabled";

	/**
	 * The name of the table.
	 */
	static final String TABLE_NAME = "SettingTable";

	/**
	 * The string used for creating the setting table via a sql query.
	 */
	static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
			+ KEY_PROFILE_NAME + " text not null, " + KEY_VOLUME_MUSIC
			+ " float, " + KEY_VOLUME_EFFECTS + " float, " + KEY_ZOOM_ENABLED
			+ " boolean, " + KEY_COLORBLIND_ENABLED + " boolean, "
			+ "FOREIGN KEY(" + KEY_PROFILE_NAME + ") REFERENCES "
			+ ProfileManager.TABLE_NAME + "(" + ProfileManager.KEY_PROFILE_NAME
			+ ") ON UPDATE CASCADE ON DELETE CASCADE )";

	/**
	 * Creates a new SettingManager which manages the setting table.
	 * 
	 * @param context
	 *            used for accessing the database
	 */
	SettingManager(Context context) {
		super(context);

	}

	/**
	 * Adds a new setting to the table.
	 * 
	 * @param profileName
	 *            the name of the profile whose setting is added to the table
	 * @param setting
	 *            contains the values to be stored in the table
	 */
	void addSetting(String profileName, Setting setting) {
		ContentValues values = new ContentValues();

		values.put(KEY_PROFILE_NAME, profileName);
		values.put(KEY_VOLUME_MUSIC, setting.getVolumeMusic());
		values.put(KEY_VOLUME_EFFECTS, setting.getVolumeEffects());
		values.put(KEY_ZOOM_ENABLED, setting.isZoomEnabled());
		values.put(KEY_COLORBLIND_ENABLED, setting.isColorblindEnabled());

		database.insert(TABLE_NAME, null, values);
	}

	/**
	 * Searches the table for a setting which belongs to the profile identified
	 * by the given profile name.
	 * 
	 * @param profileName
	 *            the name of the profile whose setting is searched for
	 * @return the found setting, null if no setting is found
	 */
	Setting getSetting(String profileName) {

		String selectQuery = "select * from " + TABLE_NAME + " where "
				+ KEY_PROFILE_NAME + " = " + "'" + profileName + "'";

		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			float volumeMusic = cursor.getFloat(cursor
					.getColumnIndex(KEY_VOLUME_MUSIC));
			float volumeEffects = cursor.getFloat(cursor
					.getColumnIndex(KEY_VOLUME_EFFECTS));
			boolean zoomEnabled = (cursor.getInt(cursor
					.getColumnIndex(KEY_ZOOM_ENABLED)) == 1) ? true : false;
			boolean colorblindEnabled = (cursor.getInt(cursor
					.getColumnIndex(KEY_COLORBLIND_ENABLED)) == 1) ? true
					: false;
			return new Setting(volumeMusic, volumeEffects, zoomEnabled,
					colorblindEnabled);
		}

		return null;
	}

	/**
	 * Searches the table for a setting which belongs to the profile identified
	 * by the given profile name and overwrites its values with the values of
	 * the new setting.
	 * 
	 * @param profileName
	 *            the name of the profile whose setting is edited
	 * @param setting
	 *            the setting whose values are used for overwriting the old
	 *            setting
	 */
	void editSetting(String profileName, Setting setting) {

		ContentValues values = new ContentValues();

		values.put(KEY_VOLUME_MUSIC, setting.getVolumeMusic());
		values.put(KEY_VOLUME_EFFECTS, setting.getVolumeEffects());
		values.put(KEY_ZOOM_ENABLED, setting.isZoomEnabled());
		values.put(KEY_COLORBLIND_ENABLED, setting.isColorblindEnabled());

		database.update(TABLE_NAME, values, KEY_PROFILE_NAME + " = ?",
				new String[] { profileName });
	}


	void clearTable() {
		database.execSQL("delete from " + TABLE_NAME);
	}

	@Override
	long getRowCount() {
		return DatabaseUtils.queryNumEntries(database, TABLE_NAME);

	}

}
