package de.croggle.game.achievement;

import java.util.List;

import de.croggle.controller.StatisticsDeltaProcessor;
import de.croggle.data.persistence.Statistic;

/**
 * 
 * @navassoc 1 - * de.croggle.game.achievement.Achievement
 * @navassoc 1 - * de.croggle.data.persistence.manager.AchievementManager
 */
public class AchievementController implements StatisticsDeltaProcessor{

	List<Achievement> achievements;

	public AchievementController() {

	}

    /**
     * Recieves statisticsDelta from the just finished Level and processes it.
     * @param statisticsDelta changes within the statistic of an account,  which occured during the completion of a level.
     * @return List<Achievement> List of achieved achievements. Can be empty if no achievements were achieved.
     */
	public List<Achievement> processStatisticsDelta(Statistic statisticsDelta) {
		return null;
	}

	@Override
	public void processDelta(Statistic statisticsDelta) {
		// TODO Auto-generated method stub
		
	}
}
