package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.*;
import de.croggle.test.TestLocalizationBackend;

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
		int[] testStages = { 0,  10, 25, 50, 100, 200, 300, 500, 750, 1000, 2000 };
		for (int i = 1; i < 11; i++) {
			assertTrue(testAchievement.getStage(i) == testStages[i]);
			assertTrue(testAchievement.getDescription(i).endsWith("placed"));
			if (i < 10) {
				assertTrue(testAchievement.getEmblemPathAchieved(i).equals("emblems/alligatorsPlaced/0" + i + "a"));
				assertTrue(testAchievement.getEmblemPathNotAchieved(i).equals("emblems/alligatorsPlaced/0" + i + "n"));
			}
			else {
				assertTrue(testAchievement.getEmblemPathAchieved(i).equals("emblems/alligatorsPlaced/" + i + "a"));
				assertTrue(testAchievement.getEmblemPathNotAchieved(i).equals("emblems/alligatorsPlaced/" + i + "n"));
			}
		}
	}

	public void testRequirementsMet() {
		Achievement testAchievement = new AlligatorsPlacedAchievement();
		int[] testStages = { 0,  10, 25, 50, 100, 200, 300, 500, 750, 1000, 2000 };
		testAchievement.setStages(testStages);
		Statistic testStatistic = new Statistic();
		testStatistic.setAlligatorsPlaced(3000);
		assertTrue(testAchievement
				.requirementsMet(testStatistic, testStatistic) == 10);
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
