package de.croggle.game.achievement;

import java.util.List;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.Statistic;
import de.croggle.data.persistence.StatisticsDeltaProcessor;

/**
 * Controller responsible for the achievements and for checking whether achievements
 * have been achieved.
 * 
 * @navassoc 1 - * de.croggle.game.achievement.Achievement
 * @navassoc 1 - * de.croggle.data.persistence.manager.AchievementManager
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
	// list of achievements unlocked during the last level
	private List<Achievement> latestUnlockedAchievements;

	/**
	 * The backreference to the central game object.
	 */
	private AlligatorApp game;

	/**
	 * Creates a new Controller. On initialization the unlocked achievements are set to null.
	 * 
	 * @param game
	 *            the backreference to the central game object
	 */
	public AchievementController(AlligatorApp game) {

	}

	/**
	 * Receives statistics delta from the just finished level and processes it.
	 * 
	 * @param statisticsDelta
	 *            changes within the statistic of an account, which occured
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
	}

	/**
	 * Loads the achievements unlocked by the user with the name <code>profileName</code> and
	 * sets them as the unlocked achievements.
	 * 
	 * @param profileName
	 *            the name of the user which unlocked achievements are loaded
	 * @return true if the change was successful, false otherwise
	 */
	public boolean changeUnlockedAchievements(String profileName) {

	}

	/**
	 * Get the achievements unlocked by the currently active user.
	 * 
	 * @return a list of unlocked achievements
	 */
	public List<Achievement> getUnlockedAchievements() {
		return null;
	}

	/**
	 * Get all achievements available in the game.
	 * 
	 * @return a list of available achievements
	 */
	public List<Achievement> getAvailableAchievements() {
		return null;
	}

	/**
	 * Returns the list of achievements that were unlocked during the last
	 * completed level The list may be emptied afterwards, so no references
	 * should be held.
	 * 
	 * @return the list of latest unlocked achievements
	 */
	public List<Achievement> getLatestUnlockedAchievements() {
		return null;
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

	}
}
