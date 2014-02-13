package de.croggle.game.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import de.croggle.AlligatorApp;

/**
 * Controls the content of a level package.
 */
public class LevelController {
	// The index of the package the controller controls
	private final int packageIndex;
	private List<Level> levels;
	private final AlligatorApp game;

	/**
	 * Creates the controller with the given package index. It will manage the
	 * levels from the level package defined by <code>packageIndex</code>.
	 * 
	 * @param packageIndex
	 *            the index of the package whose levels should be controlled
	 * @param game
	 *            a instance of Alligator App, urrently used for loading
	 *            Animation.
	 */
	public LevelController(int packageIndex, AlligatorApp game) {
		this.packageIndex = packageIndex;
		this.game = game;
		this.getLevelFromPackage();
		levels.get(0).setUnlocked(true);
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

	/**
	 * Method to load the level of the package the LevelController manages.
	 */
	private void getLevelFromPackage() {
		FileHandle dirHandle;
		if (Gdx.app.getType() == ApplicationType.Android) {
			dirHandle = Gdx.files.internal("json/levels/"
					+ String.format("%02d", this.packageIndex));
		} else {
			// ApplicationType.Desktop ..
			dirHandle = Gdx.files.internal("./assets/json/levels/"
					+ String.format("%02d", this.packageIndex));
		}
		FileHandle[] files = dirHandle.list();
		String[] levelNames = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			levelNames[i] = files[i].name();
		}
		int numberOfLevel = levelNames.length - 1;
		levels = new ArrayList<Level>();
		for (int i = 0; i < numberOfLevel; i++) {
			levels.add(LevelLoadHelper.instantiate(this.packageIndex, i,
					this.game));
		}

		for (int i = 0; i < levels.size(); i++) {
			if (!(game.getPersistenceManager().getLevelProgress(
					game.getProfileController().getCurrentProfileName(),
					levels.get(i).getLevelId()) == null)) {
				if (game.getPersistenceManager()
						.getLevelProgress(
								game.getProfileController()
										.getCurrentProfileName(),
								levels.get(i).getLevelId()).isSolved()) {
					levels.get(i).setSolvedTrue();
					if (i + 1 < levels.size()) {
						levels.get(i + 1).setUnlocked(true);
					}
				}
			}
		}

	}

}
