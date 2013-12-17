package de.croggle.controller;

import java.util.List;

import de.croggle.model.Achievement;
import de.croggle.data.Statistic;

/**
 * @navassoc 1 - * de.croggle.model.Achievement
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
}
