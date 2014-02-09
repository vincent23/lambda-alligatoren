package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.TestLocalizationBackend;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.*;

public class HintPerLevelAchievementTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testInitialize() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_hints_used_per_level", " used");
		Achievement testAchievement = new HintPerLevelAchievement();
		testAchievement.initialize();
		int[] testStages = { 1, 0 };
		assertTrue(testAchievement.getStage(0) == testStages[0]);
		assertTrue(testAchievement.getDescription(1).endsWith("sed"));
		assertTrue(testAchievement.getEmblemPathAchieved(1).equals("emblems/hintsUsedPerLevel/01a"));
		assertTrue(testAchievement.getEmblemPathNotAchieved(1).equals("emblems/hintsUsedPerLevel/01n"));
	}

	public void testRequirementsMet() {
		Achievement testAchievement = new HintPerLevelAchievement();
		testAchievement.setIndex(0);
		int[] testStages = { 1, 0 };
		testAchievement.setStages(testStages);
		Statistic testStatistic = new Statistic();
		testStatistic.setUsedHints(1);
		assertTrue(testAchievement
				.requirementsMet(testStatistic, testStatistic) == 0);
		testStatistic.setUsedHints(0);
		assertTrue(testAchievement
				.requirementsMet(testStatistic, testStatistic) == 1);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
