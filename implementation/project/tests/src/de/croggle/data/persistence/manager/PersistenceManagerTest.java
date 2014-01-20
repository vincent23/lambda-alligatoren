package de.croggle.data.persistence.manager;


import java.util.List;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.LevelProgress;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.profile.Profile;

import android.test.AndroidTestCase;


public class PersistenceManagerTest extends AndroidTestCase {
	
	PersistenceManager persistenceManager;
	
	@Override
	public void setUp() {
		AlligatorApp app = new AlligatorApp(getContext());
		persistenceManager = new PersistenceManager(app);
	}
	
	
	@Override
	public void tearDown() {
		persistenceManager.clearTables();
	}
	
	public void testAddProfile() {
		Profile profile = new Profile("Hans", "assets/path1");
		Setting setting = new Setting(1,2,true, false);
		Statistic statistic = new Statistic(1,1,2,3,5,8,13,21,34,55);
		profile.setSetting(setting);
		profile.setStatistic(statistic);
		
		assertTrue(persistenceManager.isNameValid("Hans"));
		persistenceManager.addProfile(profile);
		assertFalse(persistenceManager.isNameValid("Hans"));
		
		assertTrue(profile.equals(persistenceManager.getProfile("Hans")));
		assertTrue(profile.getSetting().equals(persistenceManager.getSetting("Hans")));
		assertTrue(profile.getStatistic().equals(persistenceManager.getStatistic("Hans")));
	}
	
	public void testEditProfile() {
		Profile profile1 = new Profile("Lea", "assets/path3");
		Setting setting = new Setting(1,2,true, false);
		Statistic statistic = new Statistic(1,1,2,3,5,8,13,21,34,55);
		profile1.setSetting(setting);
		profile1.setStatistic(statistic);
		
		persistenceManager.addProfile(profile1);
		
		Profile profile2 = new Profile("Anne", "assets/path56");
		
		persistenceManager.editProfile("Lea", profile2);
		
		assertTrue(persistenceManager.isNameValid("Lea"));
		
		assertTrue(persistenceManager.getProfile("Lea") == null);
		assertTrue(persistenceManager.getSetting("Lea") == null);
		assertTrue(persistenceManager.getStatistic("Lea") == null);
		
		assertTrue(persistenceManager.getProfile("Anne").equals(profile2));
		assertTrue(persistenceManager.getSetting("Anne").equals(setting));
		assertTrue(persistenceManager.getStatistic("Anne").equals(statistic));

		assertFalse(persistenceManager.isNameValid("Anne"));

	}
	
	public void testGetAllProfiles() {
		Profile profile1 = new Profile("Max", "assets/path1");
		Profile profile2 = new Profile("Tim", "assets/path2");
		Profile profile3 = new Profile("Tom", "assets/path3");
		
		List<Profile> profileList = persistenceManager.getAllProfiles();
		assertTrue(profileList.size() == 0);
		
		persistenceManager.addProfile(profile1);
		persistenceManager.addProfile(profile2);
		
		profileList = persistenceManager.getAllProfiles();
		assertTrue(profileList.size() == 2);
		
		persistenceManager.addProfile(profile3);
		
		profileList = persistenceManager.getAllProfiles();
		assertTrue(profileList.size() == 3);
		
		persistenceManager.deleteProfile("Tom");
		
		profileList = persistenceManager.getAllProfiles();
		assertTrue(profileList.size() == 2);
		
		for(Profile profile : profileList) {
			assertTrue(profile.equals(profile1) || profile.equals(profile2));
		}
	}
	
	public void testAddDublicate() {
		Profile profile1 = new Profile("Tim", "assets/path1");
		Profile profile2 = new Profile("Tim", "assets/path2");
		persistenceManager.addProfile(profile1);
		try {
			persistenceManager.addProfile(profile2);
			fail( "Missing exception" );
		} catch (IllegalArgumentException e) {
			assertTrue(persistenceManager.getProfile("Tim").equals(profile1));
		}


	}
	
	public void testDeleteProfile() {
		Profile profile = new Profile("Tim", "test");
	
		persistenceManager.addProfile(profile);
		assertFalse(persistenceManager.isNameValid("Tim"));
		
		persistenceManager.deleteProfile("Tim");
		assertTrue(persistenceManager.isNameValid("Tim"));
		assertTrue(persistenceManager.getProfile("Tim") == null);
		assertTrue(persistenceManager.getSetting("Tim") == null);
		assertTrue(persistenceManager.getStatistic("Tim") == null);
		
	}
	
	public void testEditStatistic() {
		Profile profile = new Profile("Tom", "test");
		Statistic statistic1 = new Statistic(1,2,3,4,5,6,7,8,9,10);
		Statistic statistic2 = new Statistic(7,7,7,7,7,7,7,7,7,7);
		profile.setStatistic(statistic1);
		
		persistenceManager.addProfile(profile);
		assertTrue(persistenceManager.getStatistic("Tom").equals(statistic1));
		
		persistenceManager.editStatistic("Tom", statistic2);
		assertTrue(persistenceManager.getStatistic("Tom").equals(statistic2));
	}
	
	public void testEditSetting() {
		Profile profile = new Profile("Tom", "test");
		Setting setting1 = new Setting(1,2,false,true);
		Setting setting2 = new Setting(5,6,true,false);
		profile.setSetting(setting1);
		
		persistenceManager.addProfile(profile);
		assertTrue(persistenceManager.getSetting("Tom").equals(setting1));
		
		persistenceManager.editSetting("Tom", setting2);
		assertTrue(persistenceManager.getSetting("Tom").equals(setting2));
	}
	
	public void testEditLevelProgress() {
		Profile profile = new Profile("Anne", "test");
		LevelProgress levelProgress1 = new LevelProgress(1,false,"board1", 0, 0, 20);
		LevelProgress levelProgress2 = new LevelProgress(1,true, "board2", 1, 1, 30);
		persistenceManager.addProfile(profile);
		
		persistenceManager.saveLevelProgress("Anne", levelProgress1);
		assertTrue(persistenceManager.getLevelProgress("Anne", 1).equals(levelProgress1));
		
		persistenceManager.saveLevelProgress("Anne", levelProgress2);
		assertTrue(persistenceManager.getLevelProgress("Anne", 1).equals(levelProgress2));
	}
	

}
