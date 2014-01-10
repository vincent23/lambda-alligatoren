package de.croggle.game.level;

import java.io.IOException;

import com.badlogic.gdx.utils.JsonValue;

import de.croggle.AlligatorApp;
import de.croggle.util.convert.JsonToAlligator;

/**
 * Encapsulates the functionality needed for instantiating a level/game from the
 * respective JSON file. Therefore it removes a larger portion of program logic
 * from the <code>LevelController</code>, which in turn delegates requests for
 * level instantiation to this class' instantiate method.
 */
public class LoadLevelHelper {

	private LoadLevelHelper() {
	}

	/**
	 * Called to load a new level. With both the package index and the level
	 * index it is possible to distinctively identify the required level.
	 * 
	 * @param packageIndex
	 *            specifies the level package from which the level is supposed
	 *            to be loaded
	 * @param levelIndex
	 *            the id of the level within the package
	 * @return the level denoted by the given indices/identifiers
	 */
	static Level instantiate(int packageIndex, int levelIndex, AlligatorApp game) {
		JsonValue json = getJson(packageIndex, levelIndex);
		Level level = null;
		try {
			level = fillGeneric(json, levelIndex, packageIndex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

		return level;
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
	 * @throws IOException
	 *             if there is an error reading the json file
	 */
	private static Level fillGeneric(JsonValue json, int levelIndex,
			int packageIndex) throws IOException {
		String leveltype = json.getString("type");
		Level level = null;
		if (leveltype.equals("multiple choice")) {
			level = fillMultipleChoice(json, levelIndex, packageIndex);
		} else if (leveltype.equals("color edit")) {
			JsonValue data = json.getChild("data");
			JsonValue initialBoard = data.getChild("initial constellation");
			// TODO brauche bei JsonToAlligator ein Board zurück oder cast?
			level = new ColorEditLevel(levelIndex, packageIndex, null, null,
					null, null, json.getString("hint"),
					json.getString("description"),
					json.getInt("abort simulation after"));
		} else if (leveltype.equals("step count")) {
			level = new TermEditLevel(levelIndex, packageIndex, null, null,
					null, null, json.getString("hint"),
					json.getString("description"),
					json.getInt("abort simulation after"));

		} else {
			throw new IOException("Unspecified leveltype!");
		}
		return level;

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
	private static Level fillMultipleChoice(JsonValue json, int levelIndex,
			int packageIndex) {
		JsonValue data = json.getChild("data");
		Level level = new MultipleChoiceLevel(levelIndex, packageIndex, null,
				null, null, null, json.getString("hint"),
				json.getString("description"),
				json.getInt("abort simulation after"), null,
				data.getInt("correct answer"));
		return level;
	}
}
