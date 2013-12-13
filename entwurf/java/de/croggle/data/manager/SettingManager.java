package de.croggle.data.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import de.croggle.data.Setting;

/**
 * @navassoc 1 - * de.croggle.data.Setting
 */

public class SettingManager extends SQLiteOpenHelper {

	public static final String KEY_PROFILE_NAME = "profileName";
	public static final String KEY_VOLUME_MUSIC = "volumeMusic";
	public static final String KEY_VOLUME_EFFECTS = "volumeEffects";
	public static final String KEY_ZOOM_ENABLED = "zoomEnabled";
	public static final String KEY_COLORBLIND_ENABLED = "colorblindEnabled";

	public static final String DATABASE_NAME = "SettingDB";
	public static final int DATABASE_VERSION = 1;

	private static final String CREATE_DATABASE = "null";

	public SettingManager(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public boolean addSetting(String profileName, Setting setting) {
		/* TODO */
		return false;
	}

	public long getSetting(String profileName) {
		/* TODO */
		return 0;
	}

	/**
		 * 
		 */
	public boolean updateSetting(String profileName, Setting setting) {
		/* TODO */
		return false;
	}

	public boolean deleteSetting(String profileName) {
		/* TODO */
		return false;
	}

}
