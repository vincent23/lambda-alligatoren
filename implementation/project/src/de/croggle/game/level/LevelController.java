package de.croggle.game.level;

import java.util.List;

import de.croggle.AlligatorApp;

/**
 * Controls the content of a level package.
 */
public class LevelController {
	// The index of the package the controller controls
	private int packageIndex;
	private List<Level> levels;
	private AlligatorApp game;


	/**
	 * Creates the controller with the given package index. It will manage the
	 * levels from the level package defined by <code>packageIndex</code>.
	 * 
	 * @param packageIndex
	 *            the index of the package whose levels should be controlled
	 */
	public LevelController(int packageIndex, AlligatorApp game) {
		this.packageIndex = packageIndex;
		this.levels = this.getLevelFromPackage(packageIndex);
		this.game = game;
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
		return levels.get(levelIndex);
	}

	/**
	 * Returns the package index of the package of the level the controller
	 * currently holds.
	 * 
	 * @return the package index
	 */
	public int getPackageIndex() {
		return packageIndex;
	}

	/**
	 * Returns the size of the package, i.e. how many levels the controller
	 * holds.
	 * 
	 * @return the package size
	 */
	public int getPackageSize() {
		return levels.size();
	}
	
	private List<Level> getLevelFromPackage(int packageIndex){
		//TODO
		
		
		return null;
	}


}
