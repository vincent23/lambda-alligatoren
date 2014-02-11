package de.croggle.data.persistence.manager;




import de.croggle.data.persistence.LevelProgress;
import de.croggle.game.profile.Profile;

import android.test.AndroidTestCase;



public class LevelProgressManagerTest extends AndroidTestCase {

	private LevelProgressManager levelProgressManager;
	private ProfileManager profileManager;


	@Override
	public void setUp() {

		levelProgressManager = new LevelProgressManager(getContext());
		profileManager = new ProfileManager(getContext());
			
		profileManager.open();
		insertProfiles();
		
		levelProgressManager.open();
		levelProgressManager.clearTable();
		
	}
	
	private void insertProfiles() {
		
		Profile profile1 = new Profile("Max" , "assets/picture1");
		Profile profile2 = new Profile("Anna" , "assets/picture2");
		Profile profile3 = new Profile("Tim" , "assets/picture1");
		
		profileManager.addProfile(profile1);
		profileManager.addProfile(profile2);
		profileManager.addProfile(profile3);
	}


	
	@Override
	public void tearDown() {
		
		levelProgressManager.clearTable();
		profileManager.clearTable();
		levelProgressManager.close();
		profileManager.close();
	} 

	public void testInsertLevelProgress() {

		LevelProgress levelProgress1 = new LevelProgress(0, true, "board1", 5);
		LevelProgress levelProgress2 = new LevelProgress(1, false, "", 0);
		LevelProgress levelProgress3 = new LevelProgress(2, false, "boardXYZ", 125);
		
		assertTrue(0 == levelProgressManager.getRowCount());
	
		levelProgressManager.addLevelProgress("Tim", levelProgress1);
		levelProgressManager.addLevelProgress("Anna", levelProgress2);

		assertTrue(2 == levelProgressManager.getRowCount());

		levelProgressManager.addLevelProgress("Tim", levelProgress3);
	
		assertTrue(3 == levelProgressManager.getRowCount());
		
			
	} 


	public void testFetchLevelProgress() {

		LevelProgress levelProgress1 = new LevelProgress(0, true, "board1", 5);
		LevelProgress levelProgress2 = new LevelProgress(1, false, "board2", 3);
		LevelProgress levelProgress3 = new LevelProgress(2, false, "board4", 125);
		LevelProgress levelProgress4 = new LevelProgress(0, false, "adfadf", 0);

		levelProgressManager.addLevelProgress("Tim", levelProgress1);
		levelProgressManager.addLevelProgress("Tim", levelProgress2);
		levelProgressManager.addLevelProgress("Tim", levelProgress3);
		levelProgressManager.addLevelProgress("Anna", levelProgress4);

		LevelProgress fetch = levelProgressManager.getLevelProgress("Anna", 0);

		assertTrue(fetch.equals(levelProgress4));
		
		fetch = levelProgressManager.getLevelProgress("Tim", 0);
		assertTrue(fetch.equals(levelProgress1));
		
		fetch = levelProgressManager.getLevelProgress("Tim", 1);
		assertTrue(fetch.equals(levelProgress2));
		
		fetch = levelProgressManager.getLevelProgress("Tim", 2);
		assertTrue(fetch.equals(levelProgress3));
		

	}
	
	
	public void testDeleteLevelProgress() {
		
		LevelProgress levelProgress1 = new LevelProgress(0, true, "board1", 5);
		LevelProgress levelProgress2 = new LevelProgress(1, false, "board2", 3);
		LevelProgress levelProgress3 = new LevelProgress(2, false, "board4", 125);
		
		levelProgressManager.addLevelProgress("Tim", levelProgress1);
		levelProgressManager.addLevelProgress("Tim", levelProgress2);
		levelProgressManager.addLevelProgress("Tim", levelProgress3);
			
		assertTrue(3 == levelProgressManager.getRowCount());
		
		
		profileManager.deleteProfile("Tim");
		
		
		assertTrue(0 == levelProgressManager.getRowCount());
		
		assertTrue(null == levelProgressManager.getLevelProgress("Max", 0));
		
		
		
		}


	public void testEditLevelProgress() {
		
		LevelProgress levelProgress1 = new LevelProgress(0, true, "board1", 5);
		LevelProgress levelProgress2 = new LevelProgress(0, false, "board2", 81);

		levelProgressManager.addLevelProgress("Max", levelProgress1);
		levelProgressManager.updateLevelProgress("Max", levelProgress2);
		
		LevelProgress fetch = levelProgressManager.getLevelProgress("Max", 0);
		assertTrue(fetch.equals(levelProgress2));
		
		Profile profile = new Profile("Anne" , "assets/picture1");
		profileManager.editProfile("Max", profile);

		fetch = levelProgressManager.getLevelProgress("Anne", 0);
		assertTrue(fetch.equals(levelProgress2));
		
		fetch = levelProgressManager.getLevelProgress("Max", 0);
		assertTrue(fetch == null);
		
		profileManager.deleteProfile("Suse");
		fetch = levelProgressManager.getLevelProgress("Suse", 0);
		assertTrue(fetch == null);
		
		
		
	}
}
