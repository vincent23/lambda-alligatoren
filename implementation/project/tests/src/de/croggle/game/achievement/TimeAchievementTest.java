package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.*;

public class TimeAchievementTest extends TestCase {
	
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*public void testInitialize() {
		Achievement testAchievement = new TimeAchievement();
		testAchievement.initialize();
		assertTrue(testAchievement.getId() == -1);
		int[] testStages = { 5 * 60, 10 * 60, 20 * 60, 45 * 60, 60 * 60, 120 * 60,
				180 * 60, 300 * 60, 600 * 60, 6000 * 60 };
		for (int i = 0; i < 10; i++) {
			assertTrue(testAchievement.getStage(i) == testStages[i]);
			//assertTrue(testAchievement.getDescription(i).endsWith(" played"));
		}
	} */
	
	public void testIndexstuff() {
		Achievement testAchievement = new TimeAchievement();
		int[] testStages = { 5 * 60, 10 * 60, 20 * 60, 45 * 60, 60 * 60, 120 * 60,
				180 * 60, 300 * 60, 600 * 60, 6000 * 60 };
		testAchievement.setStages(testStages);
		Statistic testStatistic = new Statistic();
		testStatistic.setPlaytime(604);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 2);
		testStatistic.setPlaytime(15);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 0);
		
		
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
