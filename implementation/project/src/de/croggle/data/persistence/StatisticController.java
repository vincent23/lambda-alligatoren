package de.croggle.data.persistence;

import de.croggle.AlligatorApp;

/**
 * Controller that holds and controls the active Statistic. The active Statistic
 * is the one that belongs to the active profile.
 */
public class StatisticController implements StatisticsDeltaProcessor {

	/**
	 * The currently active statistic.
	 */
	private Statistic currentStatistic;

	/**
	 * The backreference to the central game object.
	 */
	private AlligatorApp game;

	/**
	 * Creates a new controller. On initialization the active statistic is set
	 * to null.
	 * 
	 * @param game
	 *            the backreference to the central game object
	 */
	public StatisticController(AlligatorApp game) {
		this.game = game;
	}

	/**
	 * Creates a controller with the given statistic as initial active
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
	 * Replaces the current statistic with a new one. The new statistic is
	 * stored in the database and overwrites the values of the old statistic.
	 * 
	 * @param newStatistic
	 *            the statistic used to replace the currently active statistic
	 */
	public void editCurrentStatistic(Statistic newStatistic) {
		currentStatistic = newStatistic;
		String profileName = game.getProfileController()
				.getCurrentProfileName();
		game.getPersistenceManager().editStatistic(profileName, newStatistic);
	}

	/**
	 * Loads the statistic which belongs to the user identified by profile name
	 * and sets it as the currently active statistic.
	 * 
	 * @param profileName
	 *            the name of the user whose statistic is loaded
	 * 
	 */
	public void changeCurrentStatistic(String profileName) {
		currentStatistic = game.getPersistenceManager().getStatistic(
				profileName);
	}

	/**
	 * Returns the active statistic that belongs to the active profile.
	 * 
	 * @return the active statistic
	 */
	public Statistic getCurrentStatistic() {
		return currentStatistic;
	}

	/**
	 * Returns the statistic of the profile that is identified by the given
	 * string.
	 * 
	 * @param profileName
	 *            the identifier of the profile whose statistic should be loaded
	 * @return the statistic of the specified profile
	 */
	public Statistic getStatistic(String profileName) {
		return game.getPersistenceManager().getStatistic(profileName);
	}

	/**
	 * Adds the delta to the values of the active statistic. There needs to be
	 * an active statistic that is not null.
	 */
	@Override
	public void processDelta(Statistic statisticsDelta) {

		currentStatistic.setAlligatorsEaten(currentStatistic
				.getAlligatorsEaten() + statisticsDelta.getAlligatorsEaten());
		currentStatistic.setAlligatorsPlaced(currentStatistic
				.getAlligatorsPlaced() + statisticsDelta.getAlligatorsPlaced());
		currentStatistic.setEggsHatched(currentStatistic.getEggsHatched()
				+ statisticsDelta.getEggsHatched());
		currentStatistic.setEggsPlaced(currentStatistic.getEggsPlaced()
				+ statisticsDelta.getEggsPlaced());
		currentStatistic.setRecolorings(currentStatistic.getRecolorings()
				+ statisticsDelta.getRecolorings());
		currentStatistic.setResetsUsed(currentStatistic.getResetsUsed()
				+ statisticsDelta.getResetsUsed());
		currentStatistic.setUsedHints(currentStatistic.getUsedHints()
				+ statisticsDelta.getUsedHints());
		currentStatistic.setPlaytime(currentStatistic.getPlaytime()
				+ statisticsDelta.getPlaytime());
		currentStatistic.setLevelsComplete(currentStatistic.getLevelsComplete()
				+ statisticsDelta.getLevelsComplete());
		currentStatistic.setPackagesComplete(currentStatistic
				.getPackagesComplete() + statisticsDelta.getPackagesComplete());

		game.getAchievementController().processStatisticChange(statisticsDelta,
				currentStatistic);
		game.getPersistenceManager().editStatistic(
				game.getProfileController().getCurrentProfileName(),
				currentStatistic);
	}

}
