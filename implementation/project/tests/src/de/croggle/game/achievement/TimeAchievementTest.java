package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.TestLocalizationBackend;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.*;

public class TimeAchievementTest extends TestCase {
	
	public void testInitialize() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_minutes_played", " minutes played");
		backend.putString("achievement_hours_played", " hours played");
		backend.putString("achievement_time_final", "played");
		Achievement testAchievement = new TimeAchievement();
		testAchievement.initialize();
		assertTrue(testAchievement.getId() == -1);
		int[] testStages = { 5 * 60, 10 * 60, 20 * 60, 45 * 60, 60 * 60, 120 * 60,
				180 * 60, 300 * 60, 600 * 60, 6000 * 60 };
		for (int i = 0; i < 10; i++) {
			assertTrue(testAchievement.getStage(i) == testStages[i]);
			assertTrue(testAchievement.getDescription(i).endsWith("yed"));
		}
	} 
	
	public void testIndexstuff() {
		Achievement testAchievement = new TimeAchievement();
		int[] testStages = { 5 * 60, 10 * 60, 20 * 60, 45 * 60, 60 * 60, 120 * 60,
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
