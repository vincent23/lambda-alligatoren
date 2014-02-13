package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.croggle.data.LocalizationHelper;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.*;
import de.croggle.test.TestLocalizationBackend;

public class AlligatorsEatenAchievementTest extends TestCase {
	

	public void testInitialize() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_alligators_eaten", " alligators eaten");
		backend.putString("achievement_alligators_eaten_final", " eaten");
		Achievement testAchievement = new AlligatorsEatenAchievement();
		testAchievement.initialize();
		assertTrue(testAchievement.getId() == 0);
		int[] testStages = {0, 5, 20, 50, 100, 175, 250,
				500, 750, 1250, 2500 };
		for (int i = 1; i < 11; i++) {
			assertTrue(testAchievement.getStage(i) == testStages[i]);
			assertTrue(testAchievement.getDescription(i).endsWith("eaten"));
			if (i < 10) {
				assertTrue(testAchievement.getEmblemPathAchieved(i).equals("emblems/alligatorsEaten/0" + i + "a"));
				assertTrue(testAchievement.getEmblemPathNotAchieved(i).equals("emblems/alligatorsEaten/0" + i + "n"));
			}
			else {
				assertTrue(testAchievement.getEmblemPathAchieved(i).equals("emblems/alligatorsEaten/" + i + "a"));
				assertTrue(testAchievement.getEmblemPathNotAchieved(i).equals("emblems/alligatorsEaten/" + i + "n"));
			}
		}
	} 
	
	public void testRequirementsMet() {
		Achievement testAchievement = new AlligatorsEatenAchievement();
		int[] testStages = {0, 5, 20, 50, 100, 175, 250,
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
