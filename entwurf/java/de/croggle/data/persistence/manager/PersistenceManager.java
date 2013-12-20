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
 * This class provides methods for storing and loading profile specific data. 
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
	
	private AlligatorApp game;


	/**
	 * Creates a new PersistanceManager and initializes the different managers.
	 * @param game the backreference to the central game object
	 */
	public PersistenceManager(AlligatorApp game) {

	}

	/**
	 * Stores a new profile with the default settings and statistics.
	 * @param profile The profile to be stored.
	 */
	public void addProfile(Profile profile) {

	}

	/**
	 * Returns the profile with the name profileName.
	 * @param profileName The name of the profile which is to be loaded.
	 * @return The loaded profile is returned.
	 */
	public Profile loadProfile(String profileName) {
		return null;
	}
	
	/**
	 * Overwrites the profile indexed by profileName with the values stored in profile. Every reference to the profile name gets updated.
	 * @param profileName The string to identify the profile which is to be edited.
	 * @param profile Contains the new values for the profile.
	 */
	public void editProfile(String profileName, Profile profile) {
		
	}

	/**
	 * Returns all stored profiles.
	 * @return profile A list of all stored profiles is returned.
	 */
	public List<Profile> getAllProfiles() {
		return null;
	}

	/**
	 * Deletes the profile with the name profileName (all entries referenced by profileName are deleted). 
	 * @param profileName The name of the profile to be deleted.
	 */
	public void deleteProfile(String profileName) {
		
	}

	/**
	 * Returns the setting of the profile with the name profileName.
	 * @param profileName The name of the profile to which the setting belongs.
	 * @return The found setting is returned, if no setting is found null is returned.
	 */
	public Setting getSetting(String profileName) {
		return null;
	}

	/**
	 * Overwrites the setting of the profile with the name profileName with the values stored in newSetting.
	 * @param profileName The name of the profile to which the setting belongs.
	 * @param newSetting Contains the new values used to overwrite the old setting.
	 */
	public void editSetting(String profileName, Setting newSetting) {

	}

	/**
	 * Returns the statistic of the profile with the name profileName.
	 * @param profileName The name of the profile to which the statistic belongs.
	 * @return The found statistic is returned, if no statistic is found null is returned.
	 */
	public Statistic getStatistic(String profileName) {
		return null;
	}

	/**
	 * Overwrites the statistic of a specific profile identified by profileName with the values stored in newStatistic.
	 * @param profileName The name of the profile to which the statistic belongs.
	 * @param newStatistic Contains the new values used to overwrite the old statistic.
	 */
	public void editStatistic(String profileName, Statistic newStatistic) {

	}

	/**
	 * Saves a levelProgress for a specific profile identified by profileName. If there already is an entry for the profile which has the same level
	 * id as the level id of leveProgress, the old entry get overwritten.
	 * @param profileName The name of the profile to which the statistic belongs.
	 * @param levelProgress Contains the new values used to store the levelProgress or overwrite the old levelProgress.
	 */
	public void saveLevelProgress(String profileName, LevelProgress levelProgress) {

	}

	/**
	 * Returns the levelProgress which level ID matches levelID and which belongs to the profile with the name profileName. 
	 * @param profileName The name of the profile to which the levelProgress belongs.
	 * @param levelID The level ID of the levelProgress.
	 * @return The found level progress is returned, if no setting is found null is returned.
	 */
	public LevelProgress getLevelProgress(String profileName, int levelID) {
		return null;
	}
	
	/**
	 * Saves an unlocked achievement for a specific profile identified by profileName.
	 * @param profileName The name of the user that unlocked the achievement.
	 * @param achievement Contains the values to be stored.
	 */
	public void saveUnlockedAchievement(String profileName, Achievement achievement) {
		
	}
	
	/**
	 * Returns all achievements unlocked by the user identified with profileName.
	 * @param profileName The name of the user which unlocked achievements are sought after.
	 * @return A list containing all achievements unlocked by the user with the name profileName is returned.
	 */
	public List<Achievement> getAllUnlockedAchievements(String profileName) {
		return null;
	}

}

