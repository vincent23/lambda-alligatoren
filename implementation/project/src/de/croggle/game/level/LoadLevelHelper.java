package de.croggle.game.level;

import java.io.IOException;

import com.badlogic.gdx.utils.JsonValue;

import de.croggle.AlligatorApp;
import de.croggle.game.Color;
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
		} catch (InvalidJsonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * @throws InvalidJsonException 
	 */
	private static Level fillGeneric(JsonValue json, int levelIndex,
			int packageIndex) throws IOException, InvalidJsonException {
		String leveltype = json.getString("type");
		Level level = null;
		if (leveltype.equals("multiple choice")) {
			level = fillMultipleChoice(json, levelIndex, packageIndex);
		} else if (leveltype.equals("color edit")) {
			JsonValue data = json.getChild("data");
			JsonValue initialBoard = data.getChild("initial constellation");
			// TODO brauche bei JsonToAlligator ein Board zurück oder cast?
			Color[] userColors = getColorfromJson(data.getChild("user colors"));
			if(userColors.length != 6){
				throw new InvalidJsonException("The user color Array in this json file has to contain six items!");
			}
			Color[] blockedColors = getColorfromJson(data
					.getChild("blocked colors"));

			level = new ColorEditLevel(levelIndex, packageIndex, null, null,
					null, userColors, blockedColors, json.getString("hint"),
					json.getString("description"),
					json.getInt("abort simulation after"));
		} else if (leveltype.equals("term edit")) {
			JsonValue data = json.getChild("data");
			JsonValue initialBoard = data.getChild("initial constellation");
			// TODO brauche bei JsonToAlligator ein Board zurück oder cast?
			Color[] userColors = getColorfromJson(data.getChild("user colors"));
			Color[] blockedColors = getColorfromJson(data
					.getChild("blocked colors"));
			level = new TermEditLevel(levelIndex, packageIndex, null, null,
					null, userColors, blockedColors, json.getString("hint"),
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
				null, null, json.getString("hint"), json.getString("description"), json.getInt("abort simulation after"),
				null, data.getInt("correct answer"));
		return level;
	}

	private static Color[] getColorfromJson(JsonValue json) throws InvalidJsonException {
		int size = json.size;
		if(!json.isArray()){
			throw new InvalidJsonException("There seems to be no Color array in this json file.");
		}
		Color[] color = new Color[size];
		for( int i = 0; i < size; i++){
			color[i]= new Color(json.getInt(i));
		}
		
		for(int i = 0; i < size; i++ ){
			int id = color[i].getId();
			for(int k = 0; k < i; k++){
				if(id == color[k].getId()){
					throw new InvalidJsonException("There is two times the same color.");
				}
			}
		}
		return color;

	}
}
