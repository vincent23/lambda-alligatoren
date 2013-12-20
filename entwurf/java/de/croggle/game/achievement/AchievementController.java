package de.croggle.game.achievement;

import java.util.List;

import de.croggle.controller.StatisticsDeltaProcessor;
import de.croggle.data.persistence.Statistic;
import de.croggle.AlligatorApp;

/**
 * Controller, responsible for the achievements and checking whether achievements have been achieved.
 * @navassoc 1 - * de.croggle.game.achievement.Achievement
 * @navassoc 1 - * de.croggle.data.persistence.manager.AchievementManager
 */
public class AchievementController implements StatisticsDeltaProcessor{

	private List<Achievement> achievements;
	private AlligatorApp game;

	/**
	*
	* @param game the backreference to the central game object
	*/
	public AchievementController(AlligatorApp game) {

	}

    /**
     * Recieves statisticsDelta from the just finished Level and processes it.
     * @param statisticsDelta changes within the statistic of an account,  which occured during the completion of a level.
     * @return List<Achievement> List of achieved achievements. Can be empty if no achievements were achieved.
     */
	public List<Achievement> processStatisticsDelta(Statistic statisticsDelta) {
		return null;
	}

    /**
     * initiates the achievements, thus the controller is aware, which achievements have been achieved and which achievements are still uncompleted.
     */
    public void initiateAchievements() {
    }

	@Override
	public void processDelta(Statistic statisticsDelta) {
		// TODO Auto-generated method stub
		
	}
}
