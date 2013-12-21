package de.croggle.data.persistence.manager;


import java.util.List;

import de.croggle.game.profile.Profile;

import android.content.Context;


/**
 * A concrete table manager which is responsible for managing the sqlite-table that stores the different profiles.
 * 
 * @navassoc 1 - * de.croggle.game.profile.Profile
 */
public class ProfileManager extends TableManager {

	/**
	 * Name for the column that stores the profile names. The names are used as the primary key.
	 */
	static final String KEY_PROFILE_NAME = "profileName";
	
	/**
	 * Name for the column that stores the path to the profile pictures.
	 */
	static final String KEY_PICTUREPATH = "picturepath";

	/**
	 * The name of the table.
	 */
	static final String TABLE_NAME = "profileTable";
	
	
	/**
	 * The string used to create the profile table via a sql query.
	 */
	static final String CREATE_TABLE = "null";
	
	/**
	 * Creates a new ProfileManager which manages the profile table.
	 * @param context the context used to access the database
	 */
	ProfileManager(Context context) {
		super(context);
		
	}
	
	
	/**
	 * Adds a new profile to the table.
	 * @param profile contains the values to be stored in the table
	 */
	void addProfile(Profile profile) {
	
	}

	/**
	 * Searches the table for a profile which name matches profileName.
	 * @param profileName the name of the searched-for profile
	 * @return the found profile, if no profile is found, null is returned
	 */
	Profile getProfile(String profileName) {
		return null;
	}

	/**
	 * Searches the table for a profile which name matches profileName and overwrites its values with the values stored in profile.
	 * @param profileName the name of the profile which is edited.
	 * @param profile contains the values used to overwrite the old entry
	 */
	void editProfile(String profileName, Profile profile) {
		
	}

	/**
	 * Deletes the profile which name matches profileName from the table.
	 * @param profileName the name of the user whose profile is to be deleted
	 */
	void deleteProfile(String profileName) {
		
	}

	/**
	 * Returns all profiles stored in the table.
	 */
	List<Profile> getAllProfiles() {
		return null;
	}

}
