package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.TestLocalizationBackend;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.*;

public class AlligatorsEatenAchievementTest extends TestCase {
	

	public void testInitialize() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_alligators_eaten", " alligators eaten");
		backend.putString("achievement_alligators_eaten_final", " eaten");
		Achievement testAchievement = new AlligatorsEatenAchievement();
		testAchievement.initialize();
		assertTrue(testAchievement.getId() == -1);
		int[] testStages = { 5, 20, 50, 100, 175, 250,
				500, 750, 1250, 2500 };
		for (int i = 0; i < 10; i++) {
			assertTrue(testAchievement.getStage(i) == testStages[i]);
			assertTrue(testAchievement.getDescription(i).endsWith("eaten"));
		}
	} 
	
	public void testIndexstuff() {
		Achievement testAchievement = new AlligatorsEatenAchievement();
		int[] testStages = { 5, 20, 50, 100, 175, 250,
				500, 750, 1250, 2500 };
		testAchievement.setStages(testStages);
		Statistic testStatistic = new Statistic();
		testStatistic.setAlligatorsEaten(2700);
	//	System.out.println(testAchievement.requirementsMet(testStatistic, testStatistic));
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 10);
		testStatistic.setAlligatorsEaten(31);
		testAchievement.setIndex(0);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 2);
		testStatistic.setAlligatorsEaten(4);
		testAchievement.setIndex(0);
		assertTrue(testAchievement.requirementsMet(testStatistic, testStatistic) == 0);
		
		
	}

}