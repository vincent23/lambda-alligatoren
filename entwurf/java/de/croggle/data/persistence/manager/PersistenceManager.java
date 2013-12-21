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
	
	/**
	 * The reference to the central game object.
	 */
	private AlligatorApp game;


	/**
	 * Creates a new PersistanceManager and initializes the different managers.
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
	 * Returns the profile with the name profileName.
	 * @param profileName the name of the profile which is to be loaded
	 * @return the profile which was loaded
	 */
	public Profile loadProfile(String profileName) {
		return null;
	}
	
	/**
	 * Overwrites the profile indexed by profileName with the values stored in profile. Every reference to the profile name gets updated.
	 * @param profileName the string to identify the profile which is to be edited
	 * @param profile contains the the values used to overwrite the old profile
	 */
	public void editProfile(String profileName, Profile profile) {
		
	}

	/**
	 * Returns all stored profiles.
	 * @return profile a list of all stored profiles
	 */
	public List<Profile> getAllProfiles() {
		return null;
	}

	/**
	 * Deletes the profile with the name profileName (all entries referenced by profileName are deleted). 
	 * @param profileName the name of the profile to be deleted
	 */
	public void deleteProfile(String profileName) {
		
	}

	/**
	 * Returns the setting of the profile with the name profileName.
	 * @param profileName the name of the profile to which the setting belongs.
	 * @return the found setting, if no setting is found null is returned
	 */
	public Setting getSetting(String profileName) {
		return null;
	}

	/**
	 * Overwrites the setting of the profile with the name profileName with the values stored in newSetting.
	 * @param profileName the name of the profile to which the setting belongs
	 * @param newSetting contains the new values used to overwrite the old setting
	 */
	public void editSetting(String profileName, Setting newSetting) {

	}

	/**
	 * Returns the statistic of the profile with the name profileName.
	 * @param profileName the name of the profile to which the statistic belongs
	 * @return the found statistic is returned, if no statistic is found null is returned
	 */
	public Statistic getStatistic(String profileName) {
		return null;
	}

	/**
	 * Overwrites the statistic of a specific profile identified by profileName with the values stored in newStatistic.
	 * @param profileName the name of the profile to which the statistic belongs
	 * @param newStatistic contains the new values used to overwrite the old statistic
	 */
	public void editStatistic(String profileName, Statistic newStatistic) {

	}

	/**
	 * Saves a levelProgress for a specific profile identified by profileName. If there already is an entry for the profile which has the same level
	 * id as the level id of leveProgress, the old entry get overwritten.
	 * @param profileName the name of the profile to which the statistic belongs
	 * @param levelProgress contains the new values used to store the levelProgress or overwrite the old levelProgress
	 */
	public void saveLevelProgress(String profileName, LevelProgress levelProgress) {

	}

	/**
	 * Returns the levelProgress which level ID matches levelID and which belongs to the profile with the name profileName. 
	 * @param profileName the name of the profile to which the levelProgress belongs.
	 * @param levelID the level ID of the levelProgress
	 * @return he found level progress, if no level progress is found null is returned
	 */
	public LevelProgress getLevelProgress(String profileName, int levelID) {
		return null;
	}
	
	/**
	 * Saves an unlocked achievement for a specific profile identified by profileName.
	 * @param profileName the name of the user that unlocked the achievement
	 * @param achievement contains the values to be stored
	 */
	public void saveUnlockedAchievement(String profileName, Achievement achievement) {
		
	}
	
	/**
	 * Returns all achievements unlocked by the user identified with profileName.
	 * @param profileName the name of the profile whose unlocked achievements are searched for
	 * @return a list containing all achievements unlocked by the user with the name profileName is returned
	 */
	public List<Achievement> getAllUnlockedAchievements(String profileName) {
		return null;
	}

}

