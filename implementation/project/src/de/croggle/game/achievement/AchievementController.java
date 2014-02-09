package de.croggle.game.achievement;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.util.SparseIntArray;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.Statistic;
import de.croggle.data.persistence.manager.PersistenceManager;

/**
 * Controller responsible for the achievements and for checking whether
 * achievements have been achieved.
 */
public class AchievementController {

	/**
	 * A list of all achievements available in the game.
	 */
	private List<Achievement> availableAchievements;

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
		this.availableAchievements = new ArrayList<Achievement>();
		this.latestUnlockedAchievements = new ArrayList<Achievement>();
		this.game = game;
		initiateAvailableAchievements();

	}

	/**
	 * Converts an input from the database into a list of actual achievements.
	 * 
	 * @param tupels
	 *            information coming from the database
	 * @return list of achievements represented in the input
	 */
	protected List<Achievement> convertInputFromDatabase(SparseIntArray tupels) {
		List<Achievement> converted = new ArrayList<Achievement>();
		for (int i = 0; i < tupels.size(); i++) {
			int id = tupels.keyAt(i);
			Achievement achievement = AchievementFactory.createAchievement(id);
			achievement.setIndex(tupels.valueAt(i));
			converted.add(achievement);
		}
		return converted;

	}

	/**
	 * Initiates the available achievements.
	 */
	private void initiateAvailableAchievements() {
		availableAchievements = AchievementFactory
				.createListOfAchievementTypes();
		latestUnlockedAchievements.clear();

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
	 */
	public void changeUnlockedAchievements(String profileName) {
		initiateAvailableAchievements();
		PersistenceManager pm = game.getPersistenceManager();
		SparseIntArray unlockedAchievements = pm.getAllUnlockedAchievements(profileName);
		for (Achievement achievement : availableAchievements) {
			achievement.setIndex(unlockedAchievements.get(achievement.getId()));
		}
	
		
	}

	/**
	 * Get the achievements unlocked by the currently active user.
	 * 
	 * @return a list of unlocked achievements
	 */
	public List<Achievement> getUnlockedAchievements() {
		final List<Achievement> unlocked = new ArrayList<Achievement>();
		for (Achievement achievment : availableAchievements) {
			if (achievment.getIndex() > 0) {
				unlocked.add(achievment);
			}
		}
		return unlocked;
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
	 * Changes the available and (by that indirectly the unlocked) achievements
	 * and returns said changes.
	 * 
	 * @param statistic
	 *            the users alltime statistics
	 * @param statisticDelta
	 *            the change in the users statistic after the played level
	 * @return a list of achievements with their stage after the change
	 */
	protected List<Achievement> updateAchievements(Statistic statistic,
			Statistic statisticDelta) {
		Log.d("statistic trace", "Statistic: Playtime:" + statistic.getPlaytime());
		Log.d("statistic trace", "Statistic: Alligators Eaten " + statistic.getAlligatorsEaten() );
		Log.d("statistic trace", "Statistic: Alligators placed " + statistic.getAlligatorsPlaced() );
		Log.d("statistic trace", "Statistic: LevelsCompleted " + statistic.getLevelsComplete() );
		List<Achievement> latestChanges = new ArrayList<Achievement>();
		for (Achievement achievement : availableAchievements) {
			int oldVal = achievement.getIndex();
			int newVal = achievement.requirementsMet(statistic, statisticDelta); 
			if (oldVal != newVal) {
				achievement.setIndex(newVal);
				latestChanges.add(achievement);
			}
		}
		return latestChanges;
	}
	
	/**
	 * Checks whether the new statistic changes in a level cause new
	 * achievements to get unlocked. In this case it sets them as unlocked; the
	 * newly unlocked achievements can be accessed via the
	 * <code>getLatestUnlockedAchievements()</code> method.
	 * 
	 * @param statisticsDelta
	 *            the packed statistic changes
	 * @param statistic
	 *            the updated statistic
	 */
	public void processStatisticChange(Statistic statisticsDelta,
			Statistic statistic) {
		Log.d("statistic trace", "Statistic in AchievementController: Playtime:" + statisticsDelta.getPlaytime()); // TODO remove Debug code
		Log.d("statistic trace", "Statistic in AchievementController: Alligators Eaten " + statisticsDelta.getAlligatorsEaten() );
		Log.d("statistic trace", "Statistic in AchievementController: Alligators placed " + statisticsDelta.getAlligatorsPlaced() );
		Log.d("statistic trace", "Statistic in AchievementController: LevelsCompleted " + statisticsDelta.getLevelsComplete() );
		latestUnlockedAchievements.clear();
		latestUnlockedAchievements = updateAchievements(statistic,
				statisticsDelta);
		PersistenceManager pm = game.getPersistenceManager();
		pm.saveUnlockedAchievements(game.getProfileController()
				.getCurrentProfileName(), latestUnlockedAchievements);

	}
}
