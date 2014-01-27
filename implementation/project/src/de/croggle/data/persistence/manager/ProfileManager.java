package de.croggle.data.persistence.manager;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import de.croggle.game.profile.Profile;

/**
 * A concrete table manager which is responsible for managing the SQLite table
 * that stores the different profiles.
 */
public class ProfileManager extends TableManager {

	/**
	 * Name of the column that stores the profile names. The names are used as
	 * the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";

	/**
	 * Name of the column that stores the path to the profile pictures.
	 */
	static final String KEY_PICTUREPATH = "picturePath";

	/**
	 * The name of the table.
	 */
	static final String TABLE_NAME = "profileTable";

	/**
	 * The string used for creating the profile table via a sql query.
	 */
	static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
			+ KEY_PROFILE_NAME + " text not null primary key, "
			+ KEY_PICTUREPATH + " text not null" + ")";

	/**
	 * Creates a new ProfileManager which manages the profile table.
	 * 
	 * @param context
	 *            the context used for accessing the database
	 */
	ProfileManager(Context context) {
		super(context);

	}

	/**
	 * Adds a new profile to the table.
	 * 
	 * @param profile
	 *            contains the values to be stored in the table
	 */
	void addProfile(Profile profile) {
		ContentValues values = new ContentValues();

		values.put(KEY_PROFILE_NAME, profile.getName());
		values.put(KEY_PICTUREPATH, profile.getPicturePath());

		database.insert(TABLE_NAME, null, values);
	}

	/**
	 * Searches the table for a profile whose name matches the given profile
	 * name.
	 * 
	 * @param profileName
	 *            the name of the searched profile
	 * @return the found profile, null if no profile is found
	 */
	Profile getProfile(String profileName) {

		String selectQuery = "select * from " + TABLE_NAME + " where "
				+ KEY_PROFILE_NAME + " = " + "'" + profileName + "'";

		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			String name = cursor.getString(cursor
					.getColumnIndex(KEY_PROFILE_NAME));
			String path = cursor.getString(cursor
					.getColumnIndex(KEY_PICTUREPATH));
			return new Profile(name, path);
		}

		return null;

	}

	/**
	 * Searches the table for a profile whose name matches the given profile
	 * name and overwrites its values with the values of the new profile.
	 * 
	 * @param profileName
	 *            the name of the profile which is edited.
	 * @param profile
	 *            contains the values used for overwriting the old entry
	 */
	void editProfile(String profileName, Profile profile) {

		ContentValues values = new ContentValues();
		values.put(KEY_PROFILE_NAME, profile.getName());
		values.put(KEY_PICTUREPATH, profile.getPicturePath());

		database.update(TABLE_NAME, values, KEY_PROFILE_NAME + " = ?",
				new String[] { profileName });

	}

	/**
	 * Deletes the profile whose name matches the given profile name from the
	 * table.
	 * 
	 * @param profileName
	 *            the name of the user whose profile is to be deleted
	 */
	void deleteProfile(String profileName) {

		database.delete(TABLE_NAME, KEY_PROFILE_NAME + " = ?",
				new String[] { profileName });

	}

	/**
	 * Returns all profiles stored in the table.
	 * 
	 * @return the list of all profiles
	 */
	List<Profile> getAllProfiles() {

		List<Profile> profileList = new ArrayList<Profile>();

		String selectQuery = "SELECT  * FROM " + TABLE_NAME;
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(cursor
						.getColumnIndex(KEY_PROFILE_NAME));
				String path = cursor.getString(cursor
						.getColumnIndex(KEY_PICTUREPATH));
				profileList.add(new Profile(name, path));
			} while (cursor.moveToNext());
		}
		return profileList;
	}

	/**
	 * Checks if there is already a stored profile with the name profileName.
	 * 
	 * @param profileName
	 *            the name that is checked
	 * @return true if there is already a profile with the name profileName,
	 *         else false
	 */
	boolean isNameUsed(String profileName) {
		String selectQuery = "select * from " + TABLE_NAME + " where "
				+ KEY_PROFILE_NAME + " = " + "'" + profileName + "'";

		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			return true;
		}
		return false;
	}

	void clearTable() {
		database.execSQL("delete from " + TABLE_NAME);
	}

	@Override
	long getRowCount() {
		return DatabaseUtils.queryNumEntries(database, TABLE_NAME);

	}

}
