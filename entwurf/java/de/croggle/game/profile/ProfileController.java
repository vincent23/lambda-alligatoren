package de.croggle.game.profile;


import java.util.List;

import android.content.SharedPreferences;
import de.croggle.game.profile.Profile;
import de.croggle.game.profile.ProfileOverflowException;
import de.croggle.AlligatorApp;

/**
 * A controller made to encapsulate the management of profiles.
 * There is always one of six possible profiles active.
 */
public class ProfileController {
	
	/**
	* The currently active profile.
	*/
	private Profile currentProfile;
	
	/**
	 * The backreference to the central game object.
	 */
	private AlligatorApp game;
	
	/**
	* Creates a new profile controller. On initialization the active profile is set to null.
	* @param game the backreference to the central game object
	*/
	public ProfileController(AlligatorApp game) {
		
	}
	
	/**
	* Sets the profile identified by the given profile name as the active profile.
	* The profile that was active before needs to be entirely saved before calling this method.
	* @param newProfileName the string identifying the new profile
	* @throws IllegalArgumentException when there is no saved profile identified by the given profile name
	*/
	public void changeCurrentProfile(String newProfileName) throws IllegalArgumentException {
		
	}
	
	/**
	* Creates a new profile with the given attributes and sets it as the active profile. Also writes it to the database.
	* @param name the unique name of the owner of the new profile
	* @param picturePath the picture path to the picture associated with the new profile
	* @throws IllegalArgumentException if there already is a profile identified by the given name
	* @throws ProfileOverflowException if there already are six profiles registered
	*/
	public void createNewProfile(String name, String picturePath) throws IllegalArgumentException, ProfileOverflowException {
		
	}
	
	/**
	* Replaces the active profile entirely by the given new one.
	* There must be an active profile set (not null).
	* @param profile the profile which should replace the active profile
	* @throws IllegalArgumentException when the given profile is null or its name already identifies another profile
	*/
	public void editCurrentProfile(Profile profile) throws IllegalArgumentException {
		
	}
	
	/**
	* Completely removes the currently active profile (also from the database). After deletion, there is no active profile.
	*/
	public void deleteCurrentProfile() {
		
	}
	
	/**
	 * Gets a list of all profiles.
	 *
	* @return the list of all saved profiles
	*/
	public List<Profile> getAllProfiles() {
		return null;
	}
	
	/**
	* Tests and returns if a string supposed to be a new profile's identifier is valid, meaning if it contains at least one character and is not already identifier of another profile.
	* @param newUserName The string to be tested
	* @return true, if new username is a valid profile name, false otherwise
	*/
	public boolean isValidUserName(String newUserName){
		return false;
	} 
	
	/**
	 * Stores the name of the currently active user in shared preferences.
	 */
	private void saveProfileName() {
		
	}
}
