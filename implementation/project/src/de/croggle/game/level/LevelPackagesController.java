package de.croggle.game.level;

import de.croggle.AlligatorApp;

/**
 * Controls the overview over the different level packages.
 */
public class LevelPackagesController {

	private AlligatorApp game;

	/**
	 * Creates a new controller with no packages attached.
	 * 
	 * @param game
	 *            the backreference to the central game object
	 */
	public LevelPackagesController(AlligatorApp game) {
		this.game = game;
	}
	
	/**
	 * Gets the level controller which is responsible for handling the levels
	 * within the level package.
	 * 
	 * @param packageId the Id of the chosen LevelPackage
	 * @return the level controller one must use to handle the levels within the
	 *         level package
	 */
	public LevelController getLevelController(int packageId) {
		return new LevelController(packageId, game);
	}

}
