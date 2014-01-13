package de.croggle.game.achievement;

import java.util.List;

import de.croggle.data.LocalizationHelper;
import de.croggle.data.TestLocalizationBackend;
import de.croggle.data.persistence.Statistic;
import junit.framework.TestCase;

public class AchievementControllerTest extends TestCase {

	
	public void testInitialization() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_alligators_placed", " Alligators placed .");
		backend.putString("achievement_alligators_placed_final", "Alligators placed .");
		backend.putString("achievement_alligators_eaten", " alligators eaten .");
		backend.putString("achievement_alligators_eaten_final", " eaten .");
		backend.putString("achievement_alligators_eaten_per_level", " Alligators: one level .");
		backend.putString("achievement_alligators_eaten_per_level_final", "Alligators placed: one level .");
		backend.putString("achievement_alligators_placed_per_level", "  Allgators placed per Level .");
		backend.putString("achievement_alligators_placed_per_level_final", "Alligators placed per Level .");
		backend.putString("achievement_hints_used_per_level", "fewer than one hints used .");
		backend.putString("achievement_minutes_played", " minutes played .");
		backend.putString("achievement_hours_played", " hours played .");
		backend.putString("achievement_time_final", "played .");
		AchievementController achievementController = new AchievementController(null);
		
		assertTrue(achievementController.getAvailableAchievements().size() == 7);
		for( Achievement achievement: achievementController.getAvailableAchievements()) {
			assertTrue(achievement.getIndex() == 0);
		}
		Statistic statistic1 = new Statistic();
		Statistic statistic2  = new Statistic();
		statistic1.setAlligatorsEaten(0);
		statistic1.setAlligatorsPlaced(0);
		statistic1.setEggsHatched(0);
		statistic1.setEggsPlaced(0);
		statistic1.setLevelsComplete(0);
		statistic1.setPackagesComplete(0);
		statistic1.setPlaytime(0);
		statistic1.setUsedHints(1);
		
		statistic2.setAlligatorsEaten(0);
		statistic2.setAlligatorsPlaced(0);
		statistic2.setEggsHatched(0);
		statistic2.setEggsPlaced(0);
		statistic2.setLevelsComplete(0);
		statistic2.setPackagesComplete(0);
		statistic2.setPlaytime(0);
		statistic2.setUsedHints(1);
		
		List<Achievement> unlockedAchievement = achievementController.updateAchievements(statistic1, statistic2);
		for( Achievement achievement3 : unlockedAchievement) {
			
			System.out.println(achievement3.getIndex());
			assertTrue(achievement3.getDescription(achievement3.getIndex()).endsWith(" ."));
		}
		System.out.println(unlockedAchievement.size());
		assertTrue(unlockedAchievement.size() == 7);
		
		for ( Achievement achievement2 : achievementController.getLatestUnlockedAchievements()) {
			assertTrue(achievementController.getAvailableAchievements().contains(achievement2));
		}
	}


}
