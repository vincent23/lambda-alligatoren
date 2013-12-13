package de.croggle.data.manager;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import de.croggle.data.Profile;

/**
 * @navassoc 1 - * de.croggle.data.manager.Profile
 */
public class ProfileManager extends SQLiteOpenHelper {

	public static final String KEY_PROFILE_NAME = "profileName";
	public static final String KEY_PICTUREPATH = "picturepath";

	public static final String DATABASE_NAME = "profileDB";
	public static final int DATABASE_VERSION = 1;

	private static final String CREATE_DATABASE = "null";

	public ProfileManager(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public boolean addProfile(Profile profile) {
		/* TODO */
		return false;
	}

	public long getProfile(String profileName) {
		/* TODO */
		return 0;
	}

	public boolean updateProfile(Profile profile) {
		/* TODO */
		return false;
	}

	public boolean deleteProfile(String profileName) {
		/* TODO */
		return false;
	}

	public List<Profile> getAllProfiles() {
		/* TODO */
		return null;
	}

}