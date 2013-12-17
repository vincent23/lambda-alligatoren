package de.croggle.controller;

import java.util.List;

import de.croggle.model.Achievement;

/**
 * @navassoc 1 - * de.croggle.model.Achievement
 */
public class AchievementController {

	List<Achievement> achievements;

	public AchievementController() {

	}

    /**
     * Recieves StatisticsDelta from the just finished Level and processes it.
     */
	public Achievement processStatisticsDelta(StatisticsDelta statisticsDelta) {
		return null;
	}
}
