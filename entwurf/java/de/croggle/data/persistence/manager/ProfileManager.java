package de.croggle.data.persistence.manager;


import java.util.List;

import de.croggle.game.profile.Profile;

import android.content.Context;


/**
 * This class is responsible for managing the sqlite-table that stores the different profiles.
 * 
 * @navassoc 1 - * de.croggle.game.profile.Profile
 */
public class ProfileManager extends TableManager {

	/**
	 * Name for the column that stores the profile names. Those names are used as the primary key.
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
	 * Creates a new ProfileManager used to manage the profile table.
	 * @param context
	 */
	ProfileManager(Context context) {
		super(context);
		
	}
	
	
	/**
	 * Adds a new statistic to the table, the profile name contained in profile is used as the primary key.
	 * @param profile Contains the values to be stored in the table.
	 */
	void addProfile(Profile profile) {
	
	}

	/**
	 * Searches the table for a profile which key matches profileName.
	 * @param profileName The key for the sought profile.
	 * @return Returns the found profile, if no profile is found, null is returned.
	 */
	Profile getProfile(String profileName) {
		/* TODO */
		return null;
	}

	/**
	 * Searches the table for a profile which key matches the profile name stored in profile and overwrites its values with the values stored in the parameter profile.
	 * @param profile Contains the values used to overwrite the old entry.
	 */
	void updateProfile(Profile profile) {
		
	}

	/**
	 * Deletes the table entry which key matches profileName.
	 * @param profileName The key of the entry which is to be deleted.
	 */
	void deleteProfile(String profileName) {
		
	}

	/**
	 * Returns all Profiles stored in the table.
	 */
	List<Profile> getAllProfiles() {
		/* TODO */
		return null;
	}

}