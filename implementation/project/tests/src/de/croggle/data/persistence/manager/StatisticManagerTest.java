package de.croggle.data.persistence.manager;




import de.croggle.data.persistence.Statistic;
import de.croggle.game.profile.Profile;

import android.test.AndroidTestCase;



public class StatisticManagerTest extends AndroidTestCase {

	private StatisticManager statisticManager;
	private ProfileManager profileManager;


	@Override
	public void setUp() {

		statisticManager = new StatisticManager(getContext());
		profileManager = new ProfileManager(getContext());
			
		profileManager.open();
		insertProfiles();
		
		statisticManager.open();
		
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
		
		statisticManager.clearTable();
		profileManager.clearTable();
		statisticManager.close();
		profileManager.close();
	} 

	public void testInsertStatistic() {

		Statistic statistic1 = new Statistic();
		Statistic statistic2 = new Statistic(7, 124, 3464, 3, 123, 6, 5, 2, 1, 8);
		Statistic statistic3 = new Statistic(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		assertTrue(0 == statisticManager.getRowCount());
	
		statisticManager.addStatistic("Tim", statistic1);
		statisticManager.addStatistic("Anna", statistic2);

		assertTrue(2 == statisticManager.getRowCount());

		statisticManager.addStatistic("Max", statistic3);
	
		assertTrue(3 == statisticManager.getRowCount());
	} 

	public void testFetchStatistic() {

		Statistic statistic1 = new Statistic();
		Statistic statistic2 = new Statistic(7, 124, 3464, 3, 123, 6, 5, 2, 1, 8);

		statisticManager.addStatistic("Tim", statistic1);
		statisticManager.addStatistic("Anna", statistic2);

		Statistic fetch = statisticManager.getStatistic("Anna");
	
		assertTrue(fetch.equals(statistic2));

	}
	
	public void testDeleteStatistic() {
		
		Statistic statistic1 = new Statistic();
		Statistic statistic2 = new Statistic(7, 124, 3464, 3, 123, 6, 5, 2, 1, 8);

		statisticManager.addStatistic("Max", statistic1);
		statisticManager.addStatistic("Anna", statistic2);
			
		assertTrue(2 == statisticManager.getRowCount());
		
		
		profileManager.deleteProfile("Max");
		
		
		assertTrue(1 == statisticManager.getRowCount());
		assertTrue(null == statisticManager.getStatistic("Max"));
		
		
		
		}


	public void testEditStatistic() {
		
		Statistic statistic1 = new Statistic();
		Statistic statistic2 = new Statistic(7, 124, 3464, 3, 123, 6, 5, 2, 1, 8);

		statisticManager.addStatistic("Max", statistic1);
		statisticManager.editStatistic("Max", statistic2);
		
		Statistic fetch = statisticManager.getStatistic("Max");
		assertTrue(fetch.equals(statistic2));
		
		Profile profile = new Profile("Suse" , "assets/picture1");
		profileManager.editProfile("Max", profile);

		fetch = statisticManager.getStatistic("Suse");
		assertTrue(fetch.equals(statistic2));
		
		fetch = statisticManager.getStatistic("Max");
		assertTrue(fetch == null);
		
		profileManager.deleteProfile("Suse");
		fetch = statisticManager.getStatistic("Suse");
		assertTrue(fetch == null);
		
		
		
	}

}
