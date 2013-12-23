package de.croggle.data.persistence.manager;

import java.util.List;

import android.content.Context;
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
	static final String CREATE_TABLE = "null";

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

	}

	/**
	 * Deletes the profile whose name matches the given profile name from the
	 * table.
	 * 
	 * @param profileName
	 *            the name of the user whose profile is to be deleted
	 */
	void deleteProfile(String profileName) {

	}

	/**
	 * Returns all profiles stored in the table.
	 * 
	 * @return the list of all profiles
	 */
	List<Profile> getAllProfiles() {
		return null;
	}

}
