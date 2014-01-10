package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.TestLocalizationBackend;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.*;

public class AlligatorsPlacedAchievementTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testInitialize() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_alligators_placed", " placed");
		backend.putString("achievement_alligators_placed_final", "placed");
		Achievement testAchievement = new AlligatorsPlacedAchievement();
		testAchievement.initialize();
		assertTrue(testAchievement.getId() == -1);
		int[] testStages = { 10, 25, 50, 100, 200, 300, 500, 750, 1000, 2000 };
		for (int i = 0; i < 10; i++) {
			assertTrue(testAchievement.getStage(i) == testStages[i]);
			assertTrue(testAchievement.getDescription(i).endsWith("placed"));
		}
	}

	public void testIndexstuff() {
		Achievement testAchievement = new AlligatorsPlacedAchievement();
		int[] testStages = { 10, 25, 50, 100, 200, 300, 500, 750, 1000, 2000 };
		testAchievement.setStages(testStages);
		Statistic testStatistic = new Statistic();
		testStatistic.setAlligatorsPlaced(43);
		assertTrue(testAchievement
				.requirementsMet(testStatistic, testStatistic) == 2);
		testStatistic.setAlligatorsPlaced(0);
		assertTrue(testAchievement
				.requirementsMet(testStatistic, testStatistic) == 0);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
