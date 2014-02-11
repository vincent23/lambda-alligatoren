package de.croggle.data.persistence.manager;


import java.util.ArrayList;
import java.util.List;

import de.croggle.AlligatorApp;
import de.croggle.data.LocalizationBackend;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.TestLocalizationBackend;
import de.croggle.data.persistence.LevelProgress;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.Achievement;
import de.croggle.game.achievement.AlligatorsPlacedAchievement;
import de.croggle.game.achievement.AlligatorsPlacedPerLevelAchievement;
import de.croggle.game.achievement.TimeAchievement;
import de.croggle.game.profile.Profile;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.util.SparseIntArray;


public class PersistenceManagerTest extends InstrumentationTestCase {
	
	PersistenceManager persistenceManager;
	
	@Override
	public void setUp() {
		
		Context ctxt = getInstrumentation().getTargetContext();
		AlligatorApp app = new AlligatorApp(ctxt);
		LocalizationBackend be = new TestLocalizationBackend();
		LocalizationHelper.setBackend(be);
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
		
		assertFalse(persistenceManager.isNameUsed("Hans"));
		persistenceManager.addProfile(profile);
		assertTrue(persistenceManager.isNameUsed("Hans"));
		
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
		
		assertFalse(persistenceManager.isNameUsed("Lea"));
		
		assertTrue(	persistenceManager.getProfile("Lea") == null);
		assertTrue(	persistenceManager.getSetting("Lea") == null);
		assertTrue(	persistenceManager.getStatistic("Lea") == null);
		

		assertTrue(persistenceManager.getProfile("Anne").equals(profile2));
		assertTrue(persistenceManager.getSetting("Anne").equals(setting));
		assertTrue(persistenceManager.getStatistic("Anne").equals(statistic));

		assertTrue(persistenceManager.isNameUsed("Anne"));

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
		assertTrue(persistenceManager.isNameUsed("Tim"));
		
		persistenceManager.deleteProfile("Tim");
		assertFalse(persistenceManager.isNameUsed("Tim"));
		
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
		LevelProgress levelProgress1 = new LevelProgress(1,false,"board1", 20);
		LevelProgress levelProgress2 = new LevelProgress(1,true, "board2", 30);
		persistenceManager.addProfile(profile);
		
		persistenceManager.saveLevelProgress("Anne", levelProgress1);
		assertTrue(persistenceManager.getLevelProgress("Anne", 1).equals(levelProgress1));
		
		persistenceManager.saveLevelProgress("Anne", levelProgress2);
		assertTrue(persistenceManager.getLevelProgress("Anne", 1).equals(levelProgress2));
	}
	
	public void testSaveAndLoadAchievements() {
		
		Profile profile = new Profile("Anne", "test");
		persistenceManager.addProfile(profile);
		
		Achievement achievement1 = new TimeAchievement();
		achievement1.setId(1);
		achievement1.setIndex(3);
		
		Achievement achievement2 = new AlligatorsPlacedAchievement();
		achievement2.setId(2);
		achievement2.setIndex(6);
		
		Achievement achievement3 = new AlligatorsPlacedPerLevelAchievement();
		achievement3.setId(10);
		achievement3.setIndex(9);
		
		List<Achievement> achievements = new ArrayList<Achievement>();
		achievements.add(achievement1);
		achievements.add(achievement2);
		achievements.add(achievement3);
		persistenceManager.saveUnlockedAchievements("Anne", achievements);
		
		SparseIntArray sia = persistenceManager.getAllUnlockedAchievements("Anne");
		assertTrue(sia.size() == 3);
		
		assertTrue(sia.get(1) == achievement1.getIndex());
		assertTrue(sia.get(2) == achievement2.getIndex());
		assertTrue(sia.get(10) == achievement3.getIndex());
	}
	

}
