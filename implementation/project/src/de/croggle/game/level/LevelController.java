package de.croggle.game.level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
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
		this.game = game;
		this.getLevelFromPackage(packageIndex);
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
	
	private void getLevelFromPackage(int packageIndex){
		AssetManager manager  = game.getContext().getAssets();
		String[] levelNames = null;
		try {
			levelNames = manager.list("json/levels/" + this.packageIndex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numberOfLevel = levelNames.length-1;
		levels = new ArrayList<Level>();
		for(int i = 0; i < numberOfLevel; i++ ){
			levels.add(LoadLevelHelper.instantiate(packageIndex, i));
		}
		
	}


}
