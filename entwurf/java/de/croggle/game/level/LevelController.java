package de.croggle.game.level;

import java.util.List;

/**
 * Controls the content of a level package.
 * 
 * @navassoc 1 - * de.croggle.game.level.Level
 * @depend - <uses> - de.croggle.game.level.LoadLevelHelper
 */
public class LevelController {
	// The index of the package the controller controls
	private int packageIndex;
	private List<Level> levels;

	/**
	 * Creates the controller with an empty list of levels.
	 */
	public LevelController() {

	}

	/**
	 * Creates the controller with a given list of levels to manage.
	 * 
	 * @param levels
	 *            the list of levels the controller should hold
	 */
	public LevelController(List<Level> levels) {

	}

	/**
	 * Creates the controller with the given package index. It will manage the
	 * levels from the level package defined by <code>packageIndex</code>.
	 * 
	 * @param packageIndex
	 *            the index of the package whose levels should be controlled
	 */
	public LevelController(int packageIndex) {

	}

	/**
	 * Returns the level specified by the given index. The index must be between
	 * 0 and <code>getPackageSize()</code> - 1.
	 * 
	 * @param levelIndex
	 *            the index of the level that should be returned
	 * @return the desired level
	 */
	public Level getLevel(int levelIndex) {
		return null;
	}

	/**
	 * Returns the package index of the package of the level the controller
	 * currently holds.
	 * 
	 * @return the package index
	 */
	public int getPackageIndex() {
		return 0;
	}

	/**
	 * Returns the size of the package, i.e. how many levels the controller
	 * holds.
	 * 
	 * @return the package size
	 */
	public int getPackageSize() {
		return 0;
	}

}
