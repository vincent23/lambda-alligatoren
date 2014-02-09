package de.croggle.game.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import de.croggle.AlligatorApp;

/**
 * Controls the overview over the different level packages.
 */
public class LevelPackagesController {

	private AlligatorApp game;
	private List<LevelPackage> levelPackages;

	/**
	 * Creates a new controller with no packages attached.
	 * 
	 * @param game
	 *            the backreference to the central game object
	 */
	public LevelPackagesController(AlligatorApp game) {
		this.game = game;
		this.initialiseLevelPackages();
	}

	/**
	 * Gets the level controller which is responsible for handling the levels
	 * within the level package.
	 * 
	 * @param packageId
	 *            the Id of the chosen LevelPackage
	 * @return the level controller one must use to handle the levels within the
	 *         level package
	 */
	public LevelController getLevelController(int packageId) {
		return new LevelController(packageId, game);
	}

	/**
	 * Gets the levelPackages of the game.
	 * 
	 * @return a List of levelPackages
	 */
	public List<LevelPackage> getLevelPackages() {
		return this.levelPackages;
	}

	/**
	 * Method to initialize the levelPackages from the assets.
	 * 
	 */
	private void initialiseLevelPackages() {
		FileHandle handle = Gdx.files.internal("json/levels");
		FileHandle[] packageNames = handle.list();
		int numberOfPackages = packageNames.length;
		levelPackages = new ArrayList<LevelPackage>();
		for (int i = 0; i < numberOfPackages; i++) {
			levelPackages.add(this.loadPackage(i));
		}

	}

	/**
	 * @param PackageIndex
	 *            of the Level Package which should be loaded.
	 * @return the Level Package belonging to the given index.
	 */
	private LevelPackage loadPackage(int packageIndex) {
		FileHandle handle = Gdx.files.internal("json/levels/"
				+ String.format("%02d", packageIndex) + "/package.json");
		JsonReader reader = new JsonReader();
		JsonValue de_croggle = reader.parse(handle.readString());
		JsonValue json = de_croggle.child().getChild("packages");
		String animation = json.getString("animation");
		Boolean hasAnimation = false;
		if (!animation.equals("")) {
			hasAnimation = true;
		}

		LevelPackage levelPackage = new LevelPackage(packageIndex,
				json.getString("name"), json.getString("description"),
				json.getString("banner"), hasAnimation,
				json.getString("animation"),
				json.getString("design"));
		return levelPackage;
	}

}
