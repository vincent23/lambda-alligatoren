package de.croggle.data;

import de.croggle.data.model.Profile;
import de.croggle.data.model.UserData;
import de.croggle.data.model.Settings;

import java.util.List;


import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;


import com.badlogic.gdx.Preferences;

/**
 * 
 * @navassoc 1 Manages * de.croggle.data.model.Profile
 * @navassoc 1 Manages * de.croggle.data.model.UserData
 * @navassoc 1 Manages * de.croggle.data.model.Settings
 * 
 * 
 *
 */
public class DataManager {

	public static final String KEY_ROWID = "id";
	public static final String KEY_PROFILE= "profile";
	public static final String KEY_SETTINGS = "settings";
	public static final String KEY_USERDATA = "userdata";
	public static final String CREATE_DATABASE = "null";
	
	public static final String DATABASE_NAME = "profiles.db";
	public static final int DATABASE_VERSION = 1;
	
	private Database dbHandler;
	
	/**
	 * Used to store settings
	 */
	private Preferences preferences;
	
	
	public DataManager() {
		
	}
	
	public boolean changeCurrentProfile(int user_id) {
		return false;
	}
	
	
	public Profile getCurrentProfile() {
		return null;
	}
	
	public void modifyCurrentProfile() {
		
	}
	
	public void deleteCurrentProfile() {
		
	}
	
	public List<Profile> getAllProfiles() {
		
	}
	
	public void saveCurrentProfile() {
		
	}
	
	public boolean createNewProfile(Profile profile) {
		return false;
	}
	
	
	
	public Settings getCurrentSettings() {
		return null;
	}
	
	public void changeCurrentSettings(Settings settings) {
		
	}
	
	public void saveCurrentSettings() {
		
	}
	
		
	public UserData getCurrentUserData() {
		return null;
	}
	
	public UserData getUserData(int user_id) {
		return null;
	}
	
	
	public void changeCurrentUserData(UserData data) {
		
	}
	
	public void saveUserData() {
		
	}
	
}

