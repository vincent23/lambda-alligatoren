package de.croggle.game.achievement;

import java.util.List;

import de.croggle.data.persistence.Statistic;
import de.croggle.data.persistence.StatisticsDeltaProcessor;
import de.croggle.AlligatorApp;

/**
 * Controller, responsible for the achievements and checking whether achievements have been achieved.
 * @navassoc 1 - * de.croggle.game.achievement.Achievement
 * @navassoc 1 - * de.croggle.data.persistence.manager.AchievementManager
 */
public class AchievementController implements StatisticsDeltaProcessor{

	/**
	 * A list of all achievements available in the game.
	 */
	private List<Achievement> availableAchievements;
	
	/**
	 * A list of all achievements unlocked by the currently active user.
	 */
	private List<Achievement> unlockedAchievements;
	
	/**
	 * The backreference to the central game object.
	 */
	private AlligatorApp game;

	/**
	* Creates a new Controller. If a profile name is stored in the shared preferences the corresponding 
	* unlocked achievements are loaded and stored in unlockedAchievements.
	* @param game the backreference to the central game object.
	*/
	public AchievementController(AlligatorApp game) {

	}

    /**
     * Recieves statisticsDelta from the just finished Level and processes it.
     * @param statisticsDelta changes within the statistic of an account,  which occured during the completion of a level.
     * @return List<Achievement> List of achieved achievements. Can be empty if no achievements were achieved.
     */
	public List<Achievement> processStatisticsDelta(Statistic statisticsDelta) {
		return null;
	}

    /**
     * Initiates the the available achievements.
     */
    public void initiateAvailableAchievements() {
    }
    
    /**
     * Loads the achievements unlocked by the user with the name profileName and sets them as the unlocked achievements.
     * @param profileName The name of the user which unlocked achievements are loaded.
     */
    public void changeUnlockedAchievements(String profileName) {
    	
    }
    
    /**
     * Get the achievements unlocked by the currently active user.
     * @return A List of unlocked achievements.
     */
    public List<Achievement> getUnlockedAchievements() {
    	return null;
    }
    
    /**
     * Get all achievements available in the game.
     * @return A List of available achievements.
     */
    public List<Achievement> getAvailableAchievements() {
    	return null;
    }

	@Override
	public void processDelta(Statistic statisticsDelta) {
		// TODO Auto-generated method stub
		
	}
}
