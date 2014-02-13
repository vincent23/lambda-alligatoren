package de.croggle.game.profile;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.manager.PersistenceManager;

/**
 * A controller made to encapsulate the management of profiles. There is always
 * one of six possible profiles active.
 */
public class ProfileController {

	/**
	 * The currently active profile.
	 */
	private Profile currentProfile;

	/**
	 * The backreference to the central game object.
	 */
	private final AlligatorApp game;

	private final List<ProfileChangeListener> listeners = new ArrayList<ProfileChangeListener>();

	/**
	 * Defines the max. amount of profiles that can be created.
	 */
	public static final int MAX_PROFILE_NUMBER = 6;

	/**
	 * Creates a new profile controller. On initialization the active profile is
	 * set to null.
	 * 
	 * @param game
	 *            the backreference to the central game object
	 */
	public ProfileController(AlligatorApp game) {
		this.game = game;

	}

	/**
	 * Loads the name of the last active profile and initializes all controllers
	 * that depend on that name.
	 */
	public void loadLastActiveProfile() {
		Preferences prefs = Gdx.app.getPreferences("Profile Preferences");
		String profileName = prefs.getString("activeProfile", null);
		if (profileName != null
				&& game.getPersistenceManager().isNameUsed(profileName)) {
			changeCurrentProfile(profileName);
		}
	}

	/**
	 * 
	 * Sets the profile identified by the given profile name as the active
	 * profile. The profile that was active before needs to be entirely saved
	 * before calling this method.
	 * 
	 * @param profileName
	 *            the string identifying the new profile
	 * @throws IllegalArgumentException
	 *             when there is no saved profile identified by the given
	 *             profile name
	 */
	public void changeCurrentProfile(String profileName)
			throws IllegalArgumentException {

		PersistenceManager pm = game.getPersistenceManager();
		if (!pm.isNameUsed(profileName)) {
			throw new IllegalArgumentException();
		} else {
			currentProfile = pm.getProfile(profileName);
			saveProfileName();
			updateControllers(profileName);
			updateListeners();
		}
	}

	/**
	 * Creates a new profile with the given attributes and sets it as the active
	 * profile. Also writes it to the database.
	 * 
	 * @param name
	 *            the unique name of the owner of the new profile
	 * @param picturePath
	 *            the picture path to the picture associated with the new
	 *            profile
	 * @throws IllegalArgumentException
	 *             if there already is a profile identified by the given name
	 * @throws ProfileOverflowException
	 *             if there already are six profiles registered
	 */
	public void createNewProfile(String name, String picturePath)
			throws IllegalArgumentException, ProfileOverflowException {
		if (!isValidUserName(name)) {
			throw new IllegalArgumentException();
		} else if (game.getPersistenceManager().getAllProfiles().size() > MAX_PROFILE_NUMBER) {
			throw new ProfileOverflowException();
		} else {
			Profile newProfile = new Profile(name, picturePath);
			game.getPersistenceManager().addProfile(newProfile);
			changeCurrentProfile(newProfile.getName());
		}
	}

	/**
	 * Replaces the active profile entirely by the given new one. There must be
	 * an active profile set (not null).
	 * 
	 * @param name
	 *            the new unique name of the owner of the profile
	 * @param picturePath
	 *            the new picture path to the picture associated with the
	 *            profile
	 * @throws IllegalArgumentException
	 *             when the given profile is null or its name already identifies
	 *             another profile
	 */
	public void editCurrentProfile(String name, String picturePath)
			throws IllegalArgumentException {
		if (!name.equals(getCurrentProfileName())
				&& game.getPersistenceManager().isNameUsed(name)) {
			throw new IllegalArgumentException();
		} else if (!name.equals(getCurrentProfileName())
				|| !(currentProfile != null && picturePath
						.equals(currentProfile.getPicturePath()))) {
			Profile profile = new Profile(name, picturePath);
			game.getPersistenceManager().editProfile(currentProfile.getName(),
					profile);
			changeCurrentProfile(name);

		}

	}

	/**
	 * Completely removes the currently active profile (also from the database).
	 * After deletion, there is no active profile.
	 */
	public void deleteCurrentProfile() {
		game.getPersistenceManager().deleteProfile(currentProfile.getName());
		Preferences prefs = Gdx.app.getPreferences("Profile Preferences");
		prefs.remove("activeProfile");
		prefs.flush();
		currentProfile = null;
		updateControllers("");
		updateListeners();
	}

	/**
	 * Gets a list of all profiles.
	 * 
	 * @return the list of all saved profiles
	 */
	public List<Profile> getAllProfiles() {
		return game.getPersistenceManager().getAllProfiles();
	}

	/**
	 * Tests and returns if a string supposed to be a new profile's identifier
	 * is valid, meaning if it contains at least one character and is not
	 * already identifier of another profile.
	 * 
	 * @param profileName
	 *            The string to be tested
	 * @return true, if new username is a valid profile name, false otherwise
	 */
	public boolean isValidUserName(String profileName) {
		return (profileName.length() > 0)
				&& !game.getPersistenceManager().isNameUsed(profileName);
	}

	/**
	 * Get the name of the currently active user.
	 * 
	 * @return The name of the currently active user.
	 */

	public String getCurrentProfileName() {
		if (currentProfile == null) {
			return "";
		}
		return currentProfile.getName();
	}

	/**
	 * Get the currently active profile.
	 * 
	 * @return the currently active profile
	 */
	public Profile getCurrentProfile() {
		return currentProfile;
	}

	/**
	 * Stores the name of the currently active user in shared preferences.
	 */
	private void saveProfileName() {
		Preferences prefs = Gdx.app.getPreferences("Profile Preferences");
		prefs.putString("activeProfile", currentProfile.getName());
		prefs.flush();

	}

	/**
	 * Deletes all stored profiles and their settings, statistics, unlocked
	 * achievements and level progresses.
	 */
	public void deleteAllProfiles() {
		game.getPersistenceManager().clearTables();
		Preferences prefs = Gdx.app.getPreferences("Profile Preferences");
		prefs.remove("activeProfile");
		prefs.flush();
	}

	/**
	 * Add a listener to whom the updated current profile is passed after it was
	 * changed.
	 * 
	 * @param listener
	 *            the listener that receives the updated profile
	 */
	public void addProfileChangeListener(ProfileChangeListener listener) {
		listeners.add(listener);
	}

	private void updateListeners() {
		for (ProfileChangeListener listener : listeners) {
			listener.onProfileChange(currentProfile);
		}
	}

	private void updateControllers(String profileName) {
		game.getSettingController().changeCurrentSetting(profileName);
		game.getStatisticController().changeCurrentStatistic(profileName);
		game.getAchievementController().changeUnlockedAchievements(profileName);
	}
}
