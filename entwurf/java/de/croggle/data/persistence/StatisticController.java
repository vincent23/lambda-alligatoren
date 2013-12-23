package de.croggle.data.persistence;

import de.croggle.AlligatorApp;

/**
 * Controller, that holds and controls the active Statistic. The active
 * Statistic is the one that belongs to the active profile.
 */
public class StatisticController implements StatisticsDeltaProcessor {

	/**
	 * The currently active statistic.
	 */
	private Statistic currentStatistic;
	
	/**
	 * Backreference to the game.
	 */
	private AlligatorApp game;

	/**
	 * Creates a new controller. On initialization the active statistic is set to null.
	 * 
	 * @param game the backreference to the central game object
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
	 * Replaces the current statistic with newStatistic. newStatistic gets stored in
	 * the database and overwrites the values of the old statistic.
	 * 
	 * @param newStatistic 
	 * 				the statistic used to replace the currently active statistic
	 */
	public Statistic editCurrentStatistic(Statistic newStatistic) {
		return null;
	}

	/**
	 * Loads the statistic which belongs to the user identified by profile name
	 * and sets it as the currently active statistic.
	 * 
	 * @param profileName
	 * 			  the name of the user whose statistic is loaded
	 * @throws IllegalArgumentException
	 *            whenever the string does not represent a profile in the database
	 */
	public void changeCurrentStatistic(String profileName) throws IllegalArgumentException{
		
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
	 *             whenever the string does not represent a profile in the database
	 */
	public Statistic getStatistic(String profileName) throws IllegalArgumentException {
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
