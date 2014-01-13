package de.croggle.data.persistence.manager;



import de.croggle.data.persistence.Setting;
import de.croggle.game.profile.Profile;

import android.test.AndroidTestCase;


public class SettingManagerTest extends AndroidTestCase {

	private SettingManager settingManager;
	private ProfileManager profileManager;
	
	@Override
	public void setUp() {
		settingManager = new SettingManager(getContext());
		profileManager = new ProfileManager(getContext());
		
		profileManager.open();
		insertProfiles();
		
		settingManager.open();
		
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
		settingManager.clearTable();
		profileManager.clearTable();
		settingManager.close();
		profileManager.close();
	} 

	public void testInsertSettings() {

		Setting setting1 = new Setting(0.5f, 0.5f, true, false);
		Setting setting2 = new Setting(1f, 0.5f, true, true);
		Setting setting3 = new Setting(0f, 0.5f, false, false);
		
		assertTrue(0 == settingManager.getRowCount());
	
		settingManager.addSetting("Tim", setting1);
		settingManager.addSetting("Anna", setting2);

		assertTrue(2 == settingManager.getRowCount());

		settingManager.addSetting("Max", setting3);
	
		assertTrue(3 == settingManager.getRowCount());
	} 

	public void testFetchSetting() {

		Setting setting1 = new Setting(0.5f, 0.5f, true, false);
		Setting setting2 = new Setting(1f, 0.5f, true, true);

		settingManager.addSetting("Tim", setting1);
		settingManager.addSetting("Anna", setting2);

		Setting fetch = settingManager.getSetting("Anna");

		assertTrue(fetch.getVolumeEffects() == setting2.getVolumeEffects()
				&& fetch.getVolumeMusic() == fetch.getVolumeMusic()
				&& fetch.isColorblindEnabled() == fetch.isColorblindEnabled()
				&& fetch.isZoomEnabled() == fetch.isZoomEnabled());

	}
	
	public void testDeleteSetting() {
		
		Setting setting1 = new Setting(0.5f, 0.5f, true, false);
		Setting setting2 = new Setting(1f, 0.5f, true, true);

		settingManager.addSetting("Tim", setting1);
		settingManager.addSetting("Anna", setting2);
			
		assertTrue(2 == settingManager.getRowCount());
		
		profileManager.deleteProfile("Tim");
		
		assertTrue(1 == settingManager.getRowCount());
		assertTrue(null == settingManager.getSetting("Tim"));
		
		
		
		}


	public void testEditSetting() {
		
		Setting setting1 = new Setting(0.5f, 0.5f, true, false);
		Setting setting2 = new Setting(1f, 0.5f, true, true);

		settingManager.addSetting("Tim", setting1);
			
		settingManager.editSetting("Tim", setting2);
		
		Setting fetch = settingManager.getSetting("Tim");
		
		assertTrue(fetch.getVolumeEffects() == setting2.getVolumeEffects() 
				&& fetch.getVolumeMusic() == setting2.getVolumeMusic()
				&& fetch.isColorblindEnabled() == setting2.isColorblindEnabled()
				&& fetch.isZoomEnabled() == fetch.isZoomEnabled());
		
		Profile profile = new Profile("Suse" , "assets/picture1");
		profileManager.editProfile("Tim", profile);
		
		fetch = settingManager.getSetting("Suse");
		
		assertTrue(fetch != null);
		
		fetch = settingManager.getSetting("Tim");
		
		assertTrue(fetch == null);
		
		
		
	}

}
