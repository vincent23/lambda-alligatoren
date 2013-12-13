package de.croggle.data.manager;

import java.util.List;

import de.croggle.data.LevelProgress;
import de.croggle.data.Profile;
import de.croggle.data.Setting;
import de.croggle.data.Statistic;

/**
 * @navassoc 1 - 1 de.croggle.data.manager.ProfileManager
 * @navassoc 1 - 1 de.croggle.data.manager.LevelProgressManager
 * @navassoc 1 - 1 de.croggle.data.manager.SettingManager
 * @navassoc 1 - 1 de.croggle.data.manager.StatisticManager
 */
public class PersistenceManager {

	private ProfileManager profileManager;
	private SettingManager settingManager;
	private StatisticManager statisticManager;

	String currentProfileName;

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

	public void deleteCurrentProfile() {

	}

	public Setting getCurrentSettings() {
		return null;
	}

	public void editCurrentSetting(Setting setting) {

	}

	public Statistic getCurrentStatistic() {
		return null;
	}

	public Statistic getStatistic(String profileName) {
		return null;
	}

	public void editCurrentStatistic(Statistic statistic) {

	}

	public void saveLevelProgress(LevelProgress levelProgress) {

	}

	public void editLevelProgress(LevelProgress levelProgress) {

	}

}
