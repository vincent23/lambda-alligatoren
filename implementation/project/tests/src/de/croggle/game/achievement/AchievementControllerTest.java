package de.croggle.game.achievement;

import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.persistence.Statistic;
import de.croggle.test.TestLocalizationBackend;
import de.croggle.util.SparseArray;

public class AchievementControllerTest extends TestCase {

	public void testUpdateAchievements() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_alligators_placed",
				" Alligators placed .");
		backend.putString("achievement_alligators_placed_final",
				"Alligators placed .");
		backend.putString("achievement_alligators_eaten", " alligators eaten .");
		backend.putString("achievement_alligators_eaten_final", " eaten .");
		backend.putString("achievement_alligators_eaten_per_level",
				" Alligators: one level .");
		backend.putString("achievement_alligators_eaten_per_level_final",
				"Alligators placed: one level .");
		backend.putString("achievement_alligators_placed_per_level",
				"  Allgators placed per Level .");
		backend.putString("achievement_alligators_placed_per_level_final",
				"Alligators placed per Level .");
		backend.putString("achievement_hints_used_per_level",
				"fewer than one hints used .");
		backend.putString("achievement_level_completed", " level completed .");
		backend.putString("achievement_minutes_played", " minutes played .");
		backend.putString("achievement_hours_played", " hours played .");
		backend.putString("achievement_time_final", "played .");
		AchievementController achievementController = new AchievementController(
				null);
		assertTrue(achievementController.getAvailableAchievements().size() == 7);
		for (Achievement achievement : achievementController
				.getAvailableAchievements()) {
			assertTrue(achievement.getIndex() == 0);
		}
		Statistic statistic1 = new Statistic();
		Statistic statistic2 = new Statistic();
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

		List<Achievement> unlockedAchievement = achievementController
				.updateAchievements(statistic1, statistic2);

		assertTrue(unlockedAchievement.isEmpty());
		for (Achievement achievement5 : achievementController
				.getAvailableAchievements())
			assertTrue(achievement5.getIndex() == 0); // no achievement has left
														// it's initiazation
														// stage yet.

		for (Achievement achievement2 : achievementController
				.getLatestUnlockedAchievements()) {
			assertTrue(achievementController.getAvailableAchievements()
					.contains(achievement2));
		}

		statistic1.setAlligatorsEaten(15);
		statistic2.setAlligatorsEaten(1);
		statistic1.setAlligatorsPlaced(15);
		statistic2.setAlligatorsPlaced(2);
		statistic1.setLevelsComplete(1);
		statistic1.setUsedHints(1);
		statistic2.setUsedHints(0);
		statistic1.setPlaytime(360);

		unlockedAchievement = achievementController.updateAchievements(
				statistic1, statistic2);

		assertTrue(unlockedAchievement.size() == 7);

		for (Achievement achievement3 : unlockedAchievement) {
			assertTrue(achievement3.getDescription(achievement3.getIndex())
					.endsWith(" ."));
		}

		for (Achievement achievement4 : unlockedAchievement) {
			assertTrue(achievement4.getIndex() == 1); // every achievement has
														// reached its first
														// stage
		}

		statistic1.setAlligatorsEaten(150000);
		statistic2.setAlligatorsEaten(100);
		statistic1.setAlligatorsPlaced(150000);
		statistic2.setAlligatorsPlaced(200);
		statistic1.setLevelsComplete(150);
		statistic1.setUsedHints(10);
		statistic2.setUsedHints(0);
		statistic1.setPlaytime(360000);

		unlockedAchievement = achievementController.updateAchievements(
				statistic1, statistic2);

		for (Achievement achievement6 : unlockedAchievement) {
			assertTrue(achievement6.getIndex() == achievement6
					.getNumberOfStages() - 1); // every achievement has reached
												// its final stage
		}
	}

	public void testConvert() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_alligators_placed",
				" Alligators placed .");
		backend.putString("achievement_alligators_placed_final",
				"Alligators placed .");
		backend.putString("achievement_alligators_eaten", " alligators eaten .");
		backend.putString("achievement_alligators_eaten_final", " eaten .");
		backend.putString("achievement_alligators_eaten_per_level",
				" Alligators: one level .");
		backend.putString("achievement_alligators_eaten_per_level_final",
				"Alligators placed: one level .");
		backend.putString("achievement_alligators_placed_per_level",
				"  Allgators placed per Level .");
		backend.putString("achievement_alligators_placed_per_level_final",
				"Alligators placed per Level .");
		backend.putString("achievement_hints_used_per_level",
				"fewer than one hints used .");
		backend.putString("achievement_level_completed", " level completed .");
		backend.putString("achievement_minutes_played", " minutes played .");
		backend.putString("achievement_hours_played", " hours played .");
		backend.putString("achievement_time_final", "played .");
		AchievementController achievementController = new AchievementController(
				null);
		SparseArray<Integer> testTupels = new SparseArray<Integer>();
		testTupels.put(0, 2);
		testTupels.put(1, 3);
		testTupels.put(2, 5);
		testTupels.put(3, 1);
		testTupels.put(4, 0);
		testTupels.put(5, 8);
		testTupels.put(6, 6);
		List<Achievement> converted = achievementController
				.convertInputFromDatabase(testTupels);
		assertTrue(converted.get(0).getIndex() == 2);
		assertTrue(converted.get(1).getIndex() == 3);
		assertTrue(converted.get(2).getIndex() == 5);
		assertTrue(converted.get(3).getIndex() == 1);
		assertTrue(converted.get(4).getIndex() == 0);
		assertTrue(converted.get(5).getIndex() == 8);
		assertTrue(converted.get(6).getIndex() == 6);
		assertTrue(converted.get(0).getStage(0) == 0);
	}

}
