package de.croggle.data.persistence.manager;

import java.util.List;

import de.croggle.data.persistence.LevelProgress;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.Achievement;
import de.croggle.game.profile.Profile;
import de.croggle.AlligatorApp;

/**
 * 
 * This class provides methods for storing and loading profile-specific data.
 * 
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.ProfileManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.SettingManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.StatisticManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.LevelProgressManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.AchievementManager
 *
 */
public class PersistenceManager {

	/**
	 * The profileManager is used to save and load Profiles.
	 */
	private ProfileManager profileManager;
	
	/**
	 * The settingManager is used to save and load Settings.
	 */
	private SettingManager settingManager;
	
	/**
	 * The statisticManager is used to save and load Statistics.
	 */
	private StatisticManager statisticManager;
	
	/**
	 * The levelProgressManager is used to save and load LevelProgresses.
	 */
	private LevelProgressManager levelProgressManager;
	
	/**
	 * The AchievementManager is used to save and load unlocked Achievements.
	 */	
	private AchievementManager achievementManager;
	
	/**
	 * The reference to the central game object.
	 */
	private AlligatorApp game;


	/**
	 * Creates a new PersistenceManager and initializes the different managers.
	 * @param game the backwards reference to the central game object
	 */
	public PersistenceManager(AlligatorApp game) {

	}

	/**
	 * Stores a new profile with the default settings and statistics.
	 * @param profile the profile to be stored
	 */
	public void addProfile(Profile profile) {

	}

	/**
	 * Returns the profile with the given profile name.
	 * @param profileName the name of the profile which is to be loaded
	 * @return the profile which has been loaded, null if there is no profile with this name
	 */
	public Profile loadProfile(String profileName) {
		return null;
	}
	
	/**
	 * Overwrites the profile identified by the given name with the values of the new profile. Every reference to the profile name is updated.
	 * @param profileName the string to identify the profile which is to be edited
	 * @param profile contains the values used for overwriting the old profile
	 */
	public void editProfile(String profileName, Profile profile) {
		
	}

	/**
	 * Returns all stored profiles.
	 * @return a list of all stored profiles
	 */
	public List<Profile> getAllProfiles() {
		return null;
	}

	/**
	 * Deletes the profile with the given name (all entries referenced by it are also deleted).
	 * @param profileName the name of the profile to be deleted
	 */
	public void deleteProfile(String profileName) {
		
	}

	/**
	 * Returns the setting of the profile with the given profile name.
	 * @param profileName the name of the profile to which the setting belongs
	 * @return the found setting, null if no setting is found
	 */
	public Setting getSetting(String profileName) {
		return null;
	}

	/**
	 * Overwrites the setting of the profile identified by the given name with the values of the new setting.
	 * @param profileName the name of the profile to which the setting belongs
	 * @param newSetting contains the new values used for overwriting the old setting
	 */
	public void editSetting(String profileName, Setting newSetting) {

	}

	/**
	 * Returns the statistic of the profile with the givne name.
	 * @param profileName the name of the profile to which the statistic belongs
	 * @return the found statistic, null if no statistic is found
	 */
	public Statistic getStatistic(String profileName) {
		return null;
	}

	/**
	 * Overwrites the statistic of a specific profile identified by the given profile name with the new statistic.
	 * @param profileName the name of the profile to which the statistic belongs
	 * @param newStatistic contains the new values used for overwriting the old statistic
	 */
	public void editStatistic(String profileName, Statistic newStatistic) {

	}

	/**
	 * Saves a level progress for a specific profile identified by the given profile name. If there already is an entry for the profile which has the same level
	 * id as the level id of the level progress, the old entry gets overwritten.
	 * @param profileName the name of the profile to which the statistic belongs
	 * @param levelProgress contains the new values used for storing the level progress or overwrite the old level progress
	 */
	public void saveLevelProgress(String profileName, LevelProgress levelProgress) {

	}

	/**
	 * Returns the level progress whose level ID matches the given level id and which belongs to the profile with the given profile name.
	 * @param profileName the name of the profile to which the levelProgress belongs
	 * @param levelID the level ID of the level progress
	 * @return the found level progress, null if no level progress is found
	 */
	public LevelProgress getLevelProgress(String profileName, int levelID) {
		return null;
	}
	
	/**
	 * Saves an unlocked achievement for a specific profile identified by the given profile name.
	 * @param profileName the name of the user that unlocked the achievement
	 * @param achievement contains the values to be stored
	 */
	public void saveUnlockedAchievement(String profileName, Achievement achievement) {
		
	}
	
	/**
	 * Returns all achievements unlocked by the user identified by the given profile name.
	 * @param profileName the name of the profile whose unlocked achievements are searched for
	 * @return a list containing all achievements unlocked by the user
	 */
	public List<Achievement> getAllUnlockedAchievements(String profileName) {
		return null;
	}

}

