package de.croggle.data.persistence.manager;



import java.util.List;

import de.croggle.game.profile.Profile;
import android.test.AndroidTestCase;



public class ProfileManagerTest extends AndroidTestCase {
	
	
	private ProfileManager profileManager;
	

	@Override
	public void setUp() {
		getContext().deleteDatabase("persistenceDB");
		profileManager = new ProfileManager(getContext());
		profileManager.open();
	}
	
	@Override
	public void tearDown() {
		profileManager.clearTable();
		profileManager.close();
	}
	
	
	public void testInsertProfiles() {
		
		Profile profile1 = new Profile("Max" , "assets/picture1");
		Profile profile2 = new Profile("Anna" , "assets/picture2");
		Profile profile3 = new Profile("Tim" , "assets/picture1");
		
		profileManager.addProfile(profile1);
		
		assertTrue(1 == profileManager.getRowCount());
		
		profileManager.addProfile(profile2);
		profileManager.addProfile(profile3);
		
		assertTrue(3 == profileManager.getRowCount());
	} 
	
public void testFetchProfile() {
		
		Profile profile1 = new Profile("Max" , "assets/picture1");
		Profile profile2 = new Profile("Anna" , "assets/picture2");
		Profile profile3 = new Profile("Tim" , "assets/picture1");
		
		profileManager.addProfile(profile1);
		profileManager.addProfile(profile2);
		profileManager.addProfile(profile3);
		
		Profile fetch = profileManager.getProfile("Anna");
		
		assertTrue(fetch.getName().equals(profile2.getName()) && fetch.getPicturePath().equals(profile2.getPicturePath()));
		
		
	} 


public void testDeleteProfile() {
	
	Profile profile1 = new Profile("Max" , "assets/picture1");
	Profile profile2 = new Profile("Tim" , "assets/picture2");
	
	profileManager.addProfile(profile1);
	profileManager.addProfile(profile2);
		
	assertTrue(2 == profileManager.getRowCount());
	
	profileManager.deleteProfile("Tim");
	
	assertTrue(1 == profileManager.getRowCount());
	assertTrue(null == profileManager.getProfile("Tim"));
	
	
	
	}


public void testEditProfile() {
	
	Profile profile1 = new Profile("Max" , "assets/picture1");
	Profile profile2 = new Profile("Tim" , "assets/picture2");
	
	profileManager.addProfile(profile1);
	
	
	profileManager.editProfile("Max", profile2);
	
	Profile fetch = profileManager.getProfile("Tim");
	
	assertTrue(fetch.equals(profile2));
	
	
	
}

public void testGetAllProfiles() {
	
	Profile profile1 = new Profile("Max" , "assets/picture1");
	Profile profile2 = new Profile("Anna" , "assets/picture2");
	Profile profile3 = new Profile("Tim" , "assets/picture1");
	
	profileManager.addProfile(profile1);
	profileManager.addProfile(profile2);
	profileManager.addProfile(profile3);
	
	List<Profile> profileList = profileManager.getAllProfiles();
	
	assertTrue(profileList.size() == profileManager.getRowCount());
	
	
}

}
