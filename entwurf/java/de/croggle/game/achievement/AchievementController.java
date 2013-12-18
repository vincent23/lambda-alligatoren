package de.croggle.game.achievement;

import java.util.List;

import de.croggle.controller.StatisticsDeltaProcessor;
import de.croggle.data.persistence.Statistic;

/**
 * @navassoc 1 - * de.croggle.game.achievement.Achievement
 * @navassoc 1 - * de.croggle.data.persistence.manager.AchievementManager
 */
public class AchievementController implements StatisticsDeltaProcessor{

	List<Achievement> achievements;

	public AchievementController() {

	}

    /**
     * Recieves statisticsDelta from the just finished Level and processes it.
     */
	public Achievement processStatisticsDelta(Statistic statisticsDelta) {
		return null;
	}

	@Override
	public void processDelta(Statistic statisticsDelta) {
		// TODO Auto-generated method stub
		
	}
}
