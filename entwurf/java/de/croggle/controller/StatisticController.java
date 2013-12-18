package de.croggle.controller;

import de.croggle.data.persistence.Statistic;

/**
 *
 */
public class StatisticController implements StatisticsDeltaProcessor {

	private String currentProfileName;
	private Statistic statistic;

	/**
	 * Constructor
	 */
	public StatisticController() {
	}

	public void changeCurrentStatistic(Statistic statistic) {
	}

	public Statistic getCurrentStatistic() {
		return null;
	}

	public Statistic getStatistic(String profileName) {
		return null;
	}

	@Override
	public void processDelta(Statistic statisticsDelta) {
		// TODO Auto-generated method stub

	}

}
