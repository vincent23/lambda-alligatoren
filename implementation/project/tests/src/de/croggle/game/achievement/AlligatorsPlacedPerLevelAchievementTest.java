package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.*;
import de.croggle.test.TestLocalizationBackend;

public class AlligatorsPlacedPerLevelAchievementTest extends TestCase {
	
	public void testInitialize() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_alligators_placed_per_level", " placed");
		backend.putString("achievement_alligators_placed_per_level_final", "placed");
		Achievement testAchievement = new AlligatorsPlacedPerLevelAchievement();
		testAchievement.initialize();
		int[] testStages = { 0, 2, 5, 10, 15, 25};
		for (int i = 1; i < 6; i++) {
			assertTrue(testAchievement.getStage(i) == testStages[i]);
			assertTrue(testAchievement.getDescription(i).endsWith("placed"));
			assertTrue(testAchievement.getEmblemPathAchieved(i).equals("emblems/alligatorsPlacedPerLevel/0" + i + "a"));
			assertTrue(testAchievement.getEmblemPathNotAchieved(i).equals("emblems/alligatorsPlacedPerLevel/0" + i + "n"));
		}
	} 
	
	public void testRequirementsMet() {
		Achievement testAchievement = new AlligatorsPlacedPerLevelAchievement();
		int[] testStages = {0, 2, 5, 10, 15, 25};
		testAchievement.setStages(testStages);
		Statistic testStatistic = new Statistic();
		testStatistic.setAlligatorsPlaced(30);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 5);
		testStatistic.setAlligatorsPlaced(5);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 2);
		testStatistic.setAlligatorsPlaced(0);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 0);
		
		
	}
}
