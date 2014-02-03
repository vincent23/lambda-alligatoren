package de.croggle.data.persistence.manager;

import java.util.List;

import android.content.Context;
import android.util.SparseIntArray;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.LevelProgress;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.Achievement;
import de.croggle.game.profile.Profile;

/**
 * 
 * This class provides methods for storing and loading profile-specific data.
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
	 * 
	 * @param game
	 *            the backwards reference to the central game object
	 */
	public PersistenceManager(AlligatorApp game) {
		Context context = game.getContext();
		profileManager = new ProfileManager(context);
		settingManager = new SettingManager(context);
		statisticManager = new StatisticManager(context);
		levelProgressManager = new LevelProgressManager(context);
		achievementManager = new AchievementManager(context);
		this.game = game;

	}

	/**
	 * Stores a new profile with the default settings and statistics.
	 * 
	 * @param profile
	 *            the profile to be stored
	 * 
	 * @throws IllegalArgumentException
	 *             when the given profile is null or its profile name contained
	 *             in profile already identifies another profile
	 */
	public void addProfile(Profile profile) throws IllegalArgumentException {
		if (profile == null) {
			throw new IllegalArgumentException();
		} else if (isNameUsed(profile.getName())) {
			throw new IllegalArgumentException(
					"There is already a profile with the name "
							+ profile.getName() + ".");
		} else {
			profileManager.open();
			profileManager.addProfile(profile);
			profileManager.close();

			settingManager.open();
			settingManager.addSetting(profile.getName(), profile.getSetting());
			settingManager.close();

			statisticManager.open();
			statisticManager.addStatistic(profile.getName(),
					profile.getStatistic());
			statisticManager.close();

			achievementManager.open();
			List<Achievement> achievements = game.getAchievementController()
					.getAvailableAchievements();
			for (Achievement achievement : achievements) {
				achievement.setIndex(0);
				achievementManager.addUnlockedAchievement(profile.getName(),
						achievement);
			}
			achievementManager.close();

		}
		
	}

	/**
	 * Returns the profile with the given profile name.
	 * 
	 * @param profileName
	 *            the name of the profile which is to be loaded
	 * @return the profile which has been loaded, null if there is no profile
	 *         with this name
	 */
	public Profile getProfile(String profileName) {
		profileManager.open();
		Profile profile = profileManager.getProfile(profileName);
		profileManager.close();
		return profile;
	}

	/**
	 * Overwrites the profile identified by the given name with the values of
	 * the new profile. Every reference to the profile name is updated.
	 * 
	 * @param profileName
	 *            the string to identify the profile which is to be edited
	 * @param profile
	 *            contains the values used for overwriting the old profile
	 * 
	 */

	public void editProfile(String profileName, Profile profile) {

		profileManager.open();
		profileManager.editProfile(profileName, profile);
		profileManager.close();

	}

	/**
	 * Returns all stored profiles.
	 * 
	 * @return a list of all stored profiles
	 */
	public List<Profile> getAllProfiles() {
		profileManager.open();
		List<Profile> profiles = profileManager.getAllProfiles();
		profileManager.close();
		return profiles;
	}

	/**
	 * Deletes the profile with the given name (all entries referenced by it are
	 * also deleted).
	 * 
	 * @param profileName
	 *            the name of the profile to be deleted
	 */
	public void deleteProfile(String profileName) {
		profileManager.open();
		profileManager.deleteProfile(profileName);
		profileManager.close();
	}

	/**
	 * Returns the setting of the profile with the given profile name.
	 * 
	 * @param profileName
	 *            the name of the profile to which the setting belongs
	 * @return the found setting, null if no setting is found
	 */
	public Setting getSetting(String profileName) {
		settingManager.open();
		Setting setting = settingManager.getSetting(profileName);
		settingManager.close();
		return setting;
	}

	/**
	 * Overwrites the setting of the profile identified by the given name with
	 * the values of the new setting.
	 * 
	 * @param profileName
	 *            the name of the profile to which the setting belongs
	 * @param newSetting
	 *            contains the new values used for overwriting the old setting
	 */
	public void editSetting(String profileName, Setting newSetting) {
		settingManager.open();
		settingManager.editSetting(profileName, newSetting);
		settingManager.close();
	}

	/**
	 * Returns the statistic of the profile with the givne name.
	 * 
	 * @param profileName
	 *            the name of the profile to which the statistic belongs
	 * @return the found statistic, null if no statistic is found
	 */
	public Statistic getStatistic(String profileName) {
		statisticManager.open();
		Statistic statistic = statisticManager.getStatistic(profileName);
		statisticManager.close();
		return statistic;
	}

	/**
	 * Overwrites the statistic of a specific profile identified by the given
	 * profile name with the new statistic.
	 * 
	 * @param profileName
	 *            the name of the profile to which the statistic belongs
	 * @param newStatistic
	 *            contains the new values used for overwriting the old statistic
	 */
	public void editStatistic(String profileName, Statistic newStatistic) {
		statisticManager.open();
		statisticManager.editStatistic(profileName, newStatistic);
		statisticManager.close();
	}

	/**
	 * Saves a level progress for a specific profile identified by the given
	 * profile name. If there already is an entry for the profile which has the
	 * same level id as the level id of the level progress, the old entry gets
	 * overwritten.
	 * 
	 * @param profileName
	 *            the name of the profile to which the statistic belongs
	 * @param levelProgress
	 *            contains the new values used for storing the level progress or
	 *            overwrite the old level progress
	 */
	public void saveLevelProgress(String profileName,
			LevelProgress levelProgress) {
		levelProgressManager.open();
		LevelProgress lp = levelProgressManager.getLevelProgress(profileName,
				levelProgress.getLevelId());
		if (lp == null) {
			levelProgressManager.addLevelProgress(profileName, levelProgress);
		} else {
			levelProgressManager
					.updateLevelProgress(profileName, levelProgress);
		}
		levelProgressManager.close();

	}

	/**
	 * Returns the level progress whose level ID matches the given level id and
	 * which belongs to the profile with the given profile name.
	 * 
	 * @param profileName
	 *            the name of the profile to which the levelProgress belongs
	 * @param levelID
	 *            the level ID of the level progress
	 * @return the found level progress, null if no level progress is found
	 */
	public LevelProgress getLevelProgress(String profileName, int levelID) {
		levelProgressManager.open();
		LevelProgress levelProgress = levelProgressManager.getLevelProgress(
				profileName, levelID);
		levelProgressManager.close();
		return levelProgress;
	}

	/**
	 * Saves an unlocked achievement for a specific profile identified by the
	 * given profile name.
	 * 
	 * @param profileName
	 *            the name of the user that unlocked the achievement
	 * @param achievements
	 *            a list containing the values to be stored
	 */
	public void saveUnlockedAchievements(String profileName,
			List<Achievement> achievements) {
		achievementManager.open();
		for (Achievement achievement : achievements) {
			achievementManager.addUnlockedAchievement(profileName, achievement);
		}
		achievementManager.close();

	}

	/**
	 * Returns all achievements unlocked by the user identified by the given
	 * profile name.
	 * 
	 * @param profileName
	 *            the name of the profile whose unlocked achievements are
	 *            searched for
	 * @return a sparseIntArray containing the ids and states of all
	 *         achievements unlocked by the user
	 */

	public SparseIntArray getAllUnlockedAchievements(String profileName) {
		achievementManager.open();
		SparseIntArray unlockedAchievements = achievementManager
				.getUnlockedAchievements(profileName);
		achievementManager.close();
		return unlockedAchievements;
	}

	/**
	 * Removes all entries form the tables.
	 */
	public void clearTables() {
		profileManager.open();
		profileManager.clearTable();
		profileManager.close();
	}

	/**
	 * Checks if there is already a stored profile with the name profileName.
	 * 
	 * @param profileName
	 *            the name that is checked
	 * @return true if there is already a profile with the name profileName,
	 *         else false is returned
	 */
	public boolean isNameUsed(String profileName) {
		profileManager.open();
		boolean isValid = profileManager.isNameUsed(profileName);
		profileManager.close();
		return isValid;
	}

}
