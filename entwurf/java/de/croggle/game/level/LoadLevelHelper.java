package de.croggle.game.level;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Encapsulates the functionality needed for
 * instantiating a level/game from the respective JSON file. Therefore it removes a
 * larger portion of program logic from the <code>LevelController</code>, which in turn
 * delegates requests for level instantiation to this class' instantiate method.
 */
public class LoadLevelHelper {

	private LoadLevelHelper(){}

	/**
	 * Called to load a new level. With both the package index and the level index
	 * it is possible to distinctively indentify the required level.
	 * 
	 * @param packageIndex
	 *            specifies the level package from which the level is supposed
	 *            to be loaded
	 * @param levelIndex
	 *            the id of the level within the package
	 * @return the level denoted by the given indices/identifiers
	 */
	static Level instantiate(int packageIndex, int levelIndex) {
		return null;
	}

	/**
	 * Obtain the json value from the json file associated with the given level
	 * and package ids.
	 * 
	 * @param packageIndex
	 *            specifies the level package from which the level is supposed
	 *            to be loaded
	 * @param levelIndex
	 *            the id of the level within the package.
	 * @return json value with the data needed to create the requested level
	 *         from
	 */
	private static JsonValue getJson(int packageIndex, int levelIndex) {
		return null;
	}

	/**
	 * Fill the level object with all generic information given in the specified
	 * JSONValue. Expects the json value to be the root object of a level json
	 * file.
	 * 
	 * @param level
	 *            A level, which is to be filled with information from a json
	 *            value
	 * @param json
	 *            The JSON object from which to read the level's properties
	 */
	private static void fillGeneric(Level level,
			com.badlogic.gdx.utils.JsonValue json) {

	}

	/**
	 * Fill a given multiple choice level with mc-level specific data obtained
	 * from the json value given. Expects the json value to be the root object
	 * of a level json file.
	 * 
	 * @param level
	 *            A multiple choice level, which is to be filled with
	 *            information from a json value
	 * @param json
	 *            The JSON object from which to read the level's properties
	 */
	private static void fillMultipleChoice(MultipleChoiceLevel level,
			JsonValue json) {

	}
}
