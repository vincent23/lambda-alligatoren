package de.croggle.data.persistence;

import de.croggle.AlligatorApp;

/**
 * Controller, that holds and controls the active Statistic. The active
 * Statistic is the one that belongs to the active profile.
 */
public class StatisticController implements StatisticsDeltaProcessor {

	private Statistic statistic;
	private AlligatorApp game;

	/**
	 * Creates a controller, whose statistic is set as null.
	 * 
	 * @param game
	 *            the backreference to the central game object
	 */
	public StatisticController(AlligatorApp game) {
	}

	/**
	 * Creates a controller with the given statistic as initial avtive
	 * statistic.
	 * 
	 * @param statistic
	 *            the statistic to set as active
	 * @param game
	 *            the backreference to the central game object
	 */
	public StatisticController(Statistic statistic, AlligatorApp game) {

	}

	/**
	 * Replaces the active statistic by the given one and connects it to the
	 * active profile. The avtive profile and statistic should not be null.
	 * 
	 * @param statistic
	 *            the statistic to replace the current one
	 */
	public void changeCurrentStatistic(Statistic statistic) {
	}

	/**
	 * Returns the active statistic, that belongs to the active profile.
	 * 
	 * @return the active statistic
	 */
	public Statistic getCurrentStatistic() {
		return null;
	}

	/**
	 * Returns the statistic of the profile that is identified by the given
	 * string.
	 * 
	 * @param profileName
	 *            the identifier of the profile whose statistic should be loaded
	 * @return the statistic of the specified profile
	 * @throws IllegalArgumentException
	 *             when the string does not represent a profile in the database
	 */
	public Statistic getStatistic(String profileName) {
		return null;
	}

	/**
	 * Adds the delta to the values of the active statistic. There needs to be
	 * an active statistic that is not null.
	 */
	@Override
	public void processDelta(Statistic statisticsDelta) {

	}

}
