package de.croggle.controller;


import java.util.List;

import android.content.SharedPreferences;
import de.croggle.game.profile.Profile;
import de.croggle.game.profile.ProfileOverflowException;

/**
 * A Controller made to encapsulate the management of profiles.
 * It is always one of six possible profiles active.
 */
public class ProfileController {
	
	/**
	* The currently active profile.
	*/
	private Profile currentProfile;
	
	/**
	* Creates a new ProfileController. On initialization the active profile is set to null.
	*/
	public ProfileController() {
		
	}
	
	/**
	* Sets the profile identified by the given profileName as the active profile. 
	* @param newProfileName the string identifying the new profile
	* @throws IllegalArgumentException When there is no saved profile identified by the given profileName.
	*/
	public void changeCurrentProfile(String newProfileName) throws IllegalArgumentException {
		
	}
	
	/**
	* Creates a new profile with the given attributes and sets it as the active profile. Also writes it to the database (???).
	* @param name the name of the owner of the new profile. Is identifier and must be unique.
	* @param the path to the picture associated with the new profile
	* @throws IllegalArgumentException If there is already a profile identified by the given name.
	* @throws ProfileOverflowException If there are already six profiles registered.
	*/
	public void createNewProfile(String name, String picturePath) throws IllegalArgumentException, ProfileOverflowException {
		
	}
	
	/**
	* Replaces the active profile entirely by the given new one.
	* There must be an active profile set (not null)
	* @param profile the profile which should replace the active profile
	* @throws IllegalArgumentException When the given profile is null or its name already identifies another profile.
	*/
	public void editCurrentProfile(Profile profile) throws IllegalArgumentException {
		
	}
	
	/**
	* Completely removes the currently active profile (also from the database). After deletion there is no active profile.
	*/
	public void deleteCurrentProfile() {
		
	}
	
	/**
	* @return the list of all saved profiles
	*/
	public List<Profile> getAllProfiles() {
		return null;
	}
	
	/**
	* Tests and returns if a string supposed to be a new profile's identifier is valid, means if it contains at least one character and is not already identifier of another profile.
	* @param newUserName the string to be tested
	* @return true, if newUserName would be a valid profile name, false else 
	*/
	public boolean isValidUserName(String newUserName){
		return false;
	} 
}
