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
		backend.putString("achievement_alligators_placed", " placed");
		backend.putString("achievement_alligators_placed_final", "placed");
		backend.putString("achievement_alligators_eaten", " alligators eaten");
		backend.putString("achievement_alligators_eaten_final", " eaten");
		backend.putString("achievement_alligators_eaten_per_level", " one level");
		backend.putString("achievement_alligators_eaten_per_level_final", "one level");
		backend.putString("achievement_alligators_placed_per_level", " placed");
		backend.putString("achievement_alligators_placed_per_level_final", "placed");
		backend.putString("achievement_hints_used_per_level", " used");
		backend.putString("achievement_minutes_played", " minutes played");
		backend.putString("achievement_hours_played", " hours played");
		backend.putString("achievement_time_final", "played");
		AchievementController achievementController = new AchievementController(null);
		
		assertTrue(achievementController.getAvailableAchievements().size() == 7);
		for( Achievement achievement: achievementController.getAvailableAchievements()) {
			assertTrue(achievement.getIndex() == 0);
		}
		Statistic statistic1 = new Statistic();
		Statistic statistic2  = new Statistic();
		statistic1.setAlligatorsEaten(100);
		statistic1.setAlligatorsPlaced(100);
		statistic1.setEggsHatched(100);
		statistic1.setEggsPlaced(100);
		statistic1.setLevelsComplete(100);
		statistic1.setPackagesComplete(100);
		statistic1.setPlaytime(100*60);
		statistic1.setUsedHints(0);
		
		statistic1.setAlligatorsEaten(10);
		statistic1.setAlligatorsPlaced(8);
		statistic1.setEggsHatched(4);
		statistic1.setEggsPlaced(3);
		statistic1.setLevelsComplete(4);
		statistic1.setPackagesComplete(0);
		statistic1.setPlaytime(2*60);
		statistic1.setUsedHints(0);
		
		List<Achievement> unlockedAchievement = achievementController.updateAchievements(statistic1, statistic2);
		System.out.println(unlockedAchievement.size());
		assertTrue(unlockedAchievement.size() == 7);
		
		for ( Achievement achievement2 : achievementController.getLatestUnlockedAchievements()) {
			assertTrue(achievementController.getAvailableAchievements().contains(achievement2));
		}
	}


}
