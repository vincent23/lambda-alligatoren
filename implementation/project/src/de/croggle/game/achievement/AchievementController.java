package de.croggle.game.achievement;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.Statistic;
import de.croggle.data.persistence.StatisticsDeltaProcessor;

/**
 * Controller responsible for the achievements and for checking whether
 * achievements have been achieved.
 */
public class AchievementController implements StatisticsDeltaProcessor {

	/**
	 * A list of all achievements available in the game.
	 */
	private List<Achievement> availableAchievements;

	/**
	 * A list of all achievements unlocked by the currently active user.
	 */
	private List<Achievement> unlockedAchievements;
	/**
	 * list of achievements unlocked during the last level
	 */
	private List<Achievement> latestUnlockedAchievements;

	/**
	 * The backreference to the central game object.
	 */
	private AlligatorApp game;

	/**
	 * Creates a new Controller. On initialization the unlocked achievements are
	 * set to null.
	 * 
	 * @param game
	 *            the backreference to the central game object
	 */
	public AchievementController(AlligatorApp game) {
		this.unlockedAchievements = new ArrayList<Achievement>();
		this.game = game;

	}

	/**
	 * Receives statistics delta from the just finished level and processes it.
	 * 
	 * @param statisticsDelta
	 *            changes within the statistic of an account which occured
	 *            during the completion of a level
	 * @return list of achieved achievements (might be empty)
	 */
	public List<Achievement> processStatisticsDelta(Statistic statisticsDelta) {
		return null;
	}

	/**
	 * Initiates the available achievements.
	 */
	public void initiateAvailableAchievements() {
		for (Achievement achievement : availableAchievements) {
			achievement.initialize();
		}
	}

	/**
	 * Loads the achievements unlocked by the user with the name
	 * <code>profileName</code> and sets them as the unlocked achievements.
	 * 
	 * @param profileName
	 *            the name of the user which unlocked achievements are loaded
	 * @return true if the change was successful, false otherwise
	 * @throws IllegalArgumentException
	 *             whenever the string does not represent a profile in the
	 *             database
	 */
	public void changeUnlockedAchievements(String profileName)
			throws IllegalArgumentException {

	}

	/**
	 * Get the achievements unlocked by the currently active user.
	 * 
	 * @return a list of unlocked achievements
	 */
	public List<Achievement> getUnlockedAchievements() {
		return unlockedAchievements;
	}

	/**
	 * Get all achievements available in the game.
	 * 
	 * @return a list of available achievements
	 */
	public List<Achievement> getAvailableAchievements() {
		return availableAchievements;
	}

	/**
	 * Returns the list of achievements that were unlocked during the last
	 * completed level The list may be emptied afterwards, so no references
	 * should be held.
	 * 
	 * @return the list of latest unlocked achievements
	 */
	public List<Achievement> getLatestUnlockedAchievements() {
		return latestUnlockedAchievements;
	}

	/**
	 * Checks whether the new statistic changes in a level cause new
	 * achievements to get unlocked. In this case it sets them as unlocked; the
	 * newly unlocked achievements can be accessed via the
	 * <code>getLatestUnlockedAchievements()</code> method.
	 * 
	 * @param statisticsDelta
	 *            the packed statistic changes
	 */
	@Override
	public void processDelta(Statistic statisticsDelta) {
		List<Achievement> unlockedAchievements = new ArrayList<Achievement>();
		Statistic statistic = null; //TODO: database access here?
		latestUnlockedAchievements.clear();
		for ( Achievement achievement: availableAchievements) {
			int oldVal = achievement.getIndex(); //TODO: How do i get the basevalue from the database in here?
			int newVal = achievement.requirementsMet(statistic, statisticsDelta); // the new stuff
			if ( oldVal != newVal) {
				achievement.setIndex(newVal);
				unlockedAchievements.add(achievement);
				latestUnlockedAchievements.add(achievement);
			}
		}
	}
}
