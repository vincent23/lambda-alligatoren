package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.TestLocalizationBackend;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.*;

public class AlligatorsEatenPerLevelAchievementTest extends TestCase {

	public void testInitialize() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_alligators_eaten_per_level", " one level");
		backend.putString("achievement_alligators_eaten_per_level_final", "one level");
		Achievement testAchievement = new AlligatorsEatenPerLevelAchievement();
		testAchievement.initialize();
		assertTrue(testAchievement.getId() == -1);
		int[] testStages = { 1, 2, 5, 10, 20};
		for (int i = 0; i < 4; i++) {
			assertTrue(testAchievement.getStage(i) == testStages[i]);
			assertTrue(testAchievement.getDescription(i).endsWith("level"));
		}
	}

	public void testIndexStuff() {
		Achievement testAchievement = new AlligatorsEatenPerLevelAchievement();
		int[] testStages = { 1, 2, 5, 10, 20};
		testAchievement.setStages(testStages);
		Statistic testStatistic = new Statistic();
		testStatistic.setAlligatorsEaten(30);
		System.out.println(testAchievement
				.requirementsMet(testStatistic, testStatistic));
		assertTrue(testAchievement
				.requirementsMet(testStatistic, testStatistic) == 5);
		testStatistic.setAlligatorsEaten(4);
		assertTrue(testAchievement
				.requirementsMet(testStatistic, testStatistic) == 2);
		testStatistic.setAlligatorsEaten(0);
		assertTrue(testAchievement
				.requirementsMet(testStatistic, testStatistic) == 0);

	}

}
