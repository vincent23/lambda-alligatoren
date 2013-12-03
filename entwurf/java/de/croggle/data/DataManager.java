package de.croggle.data;

public class DataManager {

	/**
	 * Used to manage profiles.
	 */
	private ProfileController pc;
	
	/**
	 * The currently logged in profile.
	 */
	private Profile currentProfile;
	
	/**
	 * The settings of the currently logged in profile.
	 */
	private Settings settings;
	
	/**
	 * The user data of the currently logged in profile.
	 */
	private UserData userData;
	
	/**
	 * Changes the current profile to the param profile. Setting and user data of the new profile will
	 * be stored in the corresponding attributes.
	 * @param profile
	 * @return
	 */
	
	public boolean changeCurrentProfile(Profile profile) {
		return false;
	}
	
	
	public Profile getCurrentProfile() {
		return currentProfile;
	}
	
	public void modifyCurrentProfile() {
		
	}
	
	public void deleteCurrentProfile() {
		
	}
	
	public void saveCurrentProfile() {
		
	}
	
	public boolean createNewProfile(Profile profile) {
		return false;
	}
	
	
	
	public Settings getSettings() {
		return null;
	}
	
	public void changeSettings(Settings settings) {
		
	}
	
	public void saveSettings() {
		
	}
	
	public void deleteSettings() {
		
	}
	
	public Settings getUserData() {
		return null;
	}
	
	public Settings getUserData(Profile profile) {
		return null;
	}
	
	public void changeUserData(Settings settings) {
		
	}
	
	public void saveUserData() {
		
	}
	
	public void deleteUserData() {
		
	}
}
