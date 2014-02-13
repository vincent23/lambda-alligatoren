package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.*;
import de.croggle.test.TestLocalizationBackend;

public class TimeAchievementTest extends TestCase {
	
	public void testInitialize() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_minutes_played", " minutes played");
		backend.putString("achievement_hours_played", " hours played");
		backend.putString("achievement_time_final", "played");
		Achievement testAchievement = new TimeAchievement();
		testAchievement.initialize();
		int[] testStages = {0,  5 * 60, 10 * 60, 20 * 60, 45 * 60, 60 * 60, 120 * 60,
				180 * 60, 300 * 60, 600 * 60, 6000 * 60 };
		for (int i = 1; i < 11; i++) {
			assertTrue(testAchievement.getStage(i) == testStages[i]);
			assertTrue(testAchievement.getDescription(i).endsWith("yed"));
			if (i < 10) {
				assertTrue(testAchievement.getEmblemPathAchieved(i).equals("emblems/time/0" + i + "a"));
				assertTrue(testAchievement.getEmblemPathNotAchieved(i).equals("emblems/time/0" + i + "n"));
			}
			else {
				assertTrue(testAchievement.getEmblemPathAchieved(i).equals("emblems/time/" + i + "a"));
				assertTrue(testAchievement.getEmblemPathNotAchieved(i).equals("emblems/time/" + i + "n"));
			}
		}
	} 
	
	public void testRequirementsMet() {
		Achievement testAchievement = new TimeAchievement();
		int[] testStages = {0, 5 * 60, 10 * 60, 20 * 60, 45 * 60, 60 * 60, 120 * 60,
				180 * 60, 300 * 60, 600 * 60, 6000 * 60 };
		testAchievement.setStages(testStages);
		Statistic testStatistic = new Statistic();
		testStatistic.setPlaytime(6001*60);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 10);
		testStatistic.setPlaytime(604);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 2);
		testStatistic.setPlaytime(15);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 0);
		
		
	}

}
