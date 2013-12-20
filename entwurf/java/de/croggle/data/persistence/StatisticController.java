package de.croggle.data.persistence;

import de.croggle.AlligatorApp;

/**
 *
 */
public class StatisticController implements StatisticsDeltaProcessor {

	private String currentProfileName;
	private Statistic statistic;
	private AlligatorApp game;

	/**
	 * 
	 * @param game the backreference to the central game object
	 */
	public StatisticController(AlligatorApp game) {
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
