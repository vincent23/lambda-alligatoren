package de.croggle.game.achievement;

import de.croggle.data.LocalizationHelper;
import de.croggle.data.persistence.Statistic;
import de.croggle.test.TestLocalizationBackend;
import junit.framework.TestCase;

public class LevelAchievementTest extends TestCase {

	public void testInitialize() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_level_completed", " Levels completed");
		Achievement testAchievement = new LevelAchievement();
		testAchievement.initialize();
		assertTrue(testAchievement.getId() == 0);
		int[] testStages = { 0, 1, 2, 4, 6, 8, 10, 12 };
		for (int i = 1; i < 8; i++) {
			assertTrue(testAchievement.getStage(i) == testStages[i]);
			assertTrue(testAchievement.getDescription(i).endsWith("completed"));
			assertTrue(testAchievement.getEmblemPathAchieved(i).equals("emblems/levelCompleted/0" + i + "a"));
			assertTrue(testAchievement.getEmblemPathNotAchieved(i).equals("emblems/levelCompleted/0" + i + "n"));
		}
		
	} 
	
	public void testRequirementsMet() {
		Achievement testAchievement = new LevelAchievement();
		int[] testStages = { 0, 1, 2, 4, 6, 8, 10, 12 };
		testAchievement.setStages(testStages);
		Statistic testStatistic = new Statistic();
		testStatistic.setLevelsComplete(14);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 7);
		testStatistic.setLevelsComplete(2);
		testAchievement.setIndex(0);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 2);
		testStatistic.setLevelsComplete(0);
		testAchievement.setIndex(0);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 0);
		
		
	}
}
