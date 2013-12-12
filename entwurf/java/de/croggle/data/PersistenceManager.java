package de.croggle.data;

import java.util.List;

import de.croggle.data.model.LevelProgress;
import de.croggle.data.model.Profile;
import de.croggle.data.model.Setting;
import de.croggle.data.model.Statistic;

import android.content.SharedPreferences;

/**
 * @navassoc 1 - 1 de.croggle.data.ProfileManager
 * @navassoc 1 - 1 de.croggle.data.LevelProgressManager
 * @navassoc 1 - 1 de.croggle.data.SettingManager
 * @navassoc 1 - 1 de.croggle.data.StatisticManager
 */
public class PersistenceManager {
	
	private ProfileManager profileManager;
	private SettingManager settingManager;
	private StatisticManager statisticManager;
	private LevelProgressManager levelProgressManager;
	
	String currentProfileName;
	
	
	public PersistenceManager() {
		
	}
	
	public void saveProfile(Profile profile) {
		
	}
	
	public Profile loadProfile (String profileName) {
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

