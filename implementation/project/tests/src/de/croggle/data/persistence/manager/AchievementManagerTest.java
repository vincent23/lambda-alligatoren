package de.croggle.data.persistence.manager;



import android.test.AndroidTestCase;
import android.util.SparseIntArray;
import de.croggle.game.achievement.Achievement;
import de.croggle.game.achievement.AlligatorsEatenAchievement;
import de.croggle.game.achievement.AlligatorsEatenPerLevelAchievement;
import de.croggle.game.achievement.AlligatorsPlacedAchievement;
import de.croggle.game.achievement.AlligatorsPlacedPerLevelAchievement;
import de.croggle.game.achievement.HintPerLevelAchievement;
import de.croggle.game.achievement.TimeAchievement;
import de.croggle.game.profile.Profile;


public class AchievementManagerTest extends  AndroidTestCase {

	private AchievementManager achievementManager;
	private ProfileManager profileManager;


	@Override
	public void setUp() {

		achievementManager = new AchievementManager(getContext());
		profileManager = new ProfileManager(getContext());
			
		profileManager.open();
		insertProfiles();
		
		achievementManager.open();
		
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
		
		achievementManager.clearTable();
		profileManager.clearTable();
		achievementManager.close();
		profileManager.close();
	} 
	
	public void testInsertUnlockedAchievement() {
		
		Achievement achievement1 = new AlligatorsEatenAchievement();
		achievement1.setId(1);
		achievement1.setIndex(0);
		
		Achievement achievement2 = new HintPerLevelAchievement();
		achievement2.setId(2);
		achievement2.setIndex(5);
		
		assertTrue(0 == achievementManager.getRowCount());
		
		achievementManager.addUnlockedAchievement("Max", achievement1);
		achievementManager.addUnlockedAchievement("Max", achievement2);
		
		assertTrue(2 == achievementManager.getRowCount());
		
		
	}
	
	public void testFetchUnlockedAchievements() {

		Achievement achievement1 = new TimeAchievement();
		achievement1.setId(1);
		achievement1.setIndex(0);
		
		Achievement achievement2 = new AlligatorsPlacedAchievement();
		achievement2.setId(2);
		achievement2.setIndex(5);
		
		Achievement achievement3 = new AlligatorsPlacedPerLevelAchievement();
		achievement3.setId(10);
		achievement3.setIndex(100);
		
		achievementManager.addUnlockedAchievement("Anna", achievement1);
		achievementManager.addUnlockedAchievement("Anna", achievement2);
		achievementManager.addUnlockedAchievement("Anna", achievement3);
		
		achievementManager.addUnlockedAchievement("Max", achievement1);
		achievementManager.addUnlockedAchievement("Max", achievement2);
		
		SparseIntArray unlockedAchievements = achievementManager.getUnlockedAchievements("Anna");
		
		assertTrue(3 == unlockedAchievements.size());
		assertTrue(unlockedAchievements.get(1) == achievement1.getIndex());
		assertTrue(unlockedAchievements.get(2) == achievement2.getIndex());
		assertTrue(unlockedAchievements.get(10) == achievement3.getIndex());
		
	}
	
	public void testEditAchievement() {
		
		Achievement achievement1 = new AlligatorsEatenPerLevelAchievement();
		achievement1.setId(1);
		achievement1.setIndex(5);
		
		Achievement achievement2 = new AlligatorsPlacedPerLevelAchievement();
		achievement2.setId(10);
		achievement2.setIndex(100);
		
		achievementManager.addUnlockedAchievement("Tim", achievement1);
		achievementManager.addUnlockedAchievement("Tim", achievement2);
		
		achievement1.setIndex(42);
		
		achievementManager.updateUnlockedAchievement("Tim", achievement1);
		
		SparseIntArray unlockedAchievements = achievementManager.getUnlockedAchievements("Tim");
		
		assertTrue(unlockedAchievements.get(1) == achievement1.getIndex());
		
	}
	
	public void deleteUnlockedAchievements() {
		
		Achievement achievement1 = new TimeAchievement();
		achievement1.setId(1);
		achievement1.setIndex(0);
		
		Achievement achievement2 = new AlligatorsPlacedAchievement();
		achievement2.setId(2);
		achievement2.setIndex(5);
		
		Achievement achievement3 = new AlligatorsPlacedPerLevelAchievement();
		achievement3.setId(10);
		achievement3.setIndex(100);
		
		achievementManager.addUnlockedAchievement("Anna", achievement1);
		achievementManager.addUnlockedAchievement("Anna", achievement2);
		achievementManager.addUnlockedAchievement("Anna", achievement3);
		
		SparseIntArray unlockedAchivements = achievementManager.getUnlockedAchievements("Anna");
		
		assertTrue(unlockedAchivements.size() == 3);
		
		profileManager.deleteProfile("Anna");
		
		unlockedAchivements = achievementManager.getUnlockedAchievements("Anna");
		
		assertTrue(unlockedAchivements.size() == 0);
		
	}

}
