package de.croggle.data.persistence.manager;

import java.util.List;

import de.croggle.data.persistence.LevelProgress;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.profile.Profile;

/**
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.ProfileManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.SettingManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.StatisticManager
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.LevelProgressManager
 */
public class PersistenceManager {

	private ProfileManager profileManager;
	private SettingManager settingManager;
	private StatisticManager statisticManager;
	private LevelProgressManager levelProgressManager;

	
	public PersistenceManager() {

	}

	public void saveProfile(Profile profile) {

	}

	public Profile loadProfile(String profileName) {
		return null;
	}

	public List<Profile> getAllProfiles() {
		return null;
	}

	public void deleteProfile(String profileName) {

	}

	public Setting getSettings(String profileName) {
		return null;
	}

	public void editSetting(String profileName, Setting setting) {

	}

	public Statistic getStatistic(String profileName) {
		return null;
	}

	public void editStatistic(String profileName, Statistic statistic) {

	}

	public void saveLevelProgress(String profileName, LevelProgress levelProgress) {

	}

	public void getLevelProgress(String profileName, int levelID) {

	}

}
