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
	 * Creates a new controller. If a profile name is stored in the
	 * shared preferences the corresponding statistic is loaded and stored in
	 * current Statistic.
	 * 
	 * @param game
	 *            The backreference to the central game object
	 */
	public StatisticController(AlligatorApp game) {
	}

	/**
	 * Creates a controller with the given statistic as initial avtive
	 * statistic.
	 * 
	 * @param statistic
	 *            The statistic to set as active
	 * @param game
	 *            The backreference to the central game object
	 */
	public StatisticController(Statistic statistic, AlligatorApp game) {

	}

	/**
	 * Replaces the current statistic with newStatistic. newStatistic gets stored in
	 * the database and overwrites the values of the old statistic.
	 * 
	 * @param newStatistic 
	 * 				The statistic used to replace the currently active statistic.
	 */
	public Statistic editCurrentStatistic(Statistic newStatistic) {
		return null;
	}

	/**
	 * Loads the statistic which belongs to the user identified by profile name
	 * and sets it as the currently active statistic.
	 * 
	 * @param profileName
	 * 			  The name of the user whose statistic is loaded.
	 * @throws IllegalArgumentException
	 *            When the string does not represent a profile in the database
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
	 *            The identifier of the profile whose statistic should be loaded
	 * @return the statistic of the specified profile
	 * @throws IllegalArgumentException
	 *             When the string does not represent a profile in the database
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
