package de.croggle.game.level;

import java.io.IOException;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import de.croggle.AlligatorApp;
import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.util.convert.JsonToAlligator;

//TODO Animation setzen!

/**
 * Encapsulates the functionality needed for instantiating a level/game from the
 * respective JSON file. Therefore it removes a larger portion of program logic
 * from the <code>LevelController</code>, which in turn delegates requests for
 * level instantiation to this class' instantiate method.
 * 
 * The class is only visible inside the de.croggle.game.level package
 */
class LevelLoadHelper {

	//private static final int FRAME_COLS = 4;
	//private static final int FRAME_ROWS = 3;

	private LevelLoadHelper() {
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
			level = fillGeneric(json, levelIndex, packageIndex, game);
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
	private static JsonValue getJson(int packageIndex, int levelIndex){

		FileHandle handle = Gdx.files.internal("json/levels/"
				+ String.format("%02d", packageIndex)
				+ String.format("/%02d", levelIndex) + ".json");
		JsonReader reader = new JsonReader();
		JsonValue de_croggle = reader.parse(handle.readString());

		return de_croggle.getChild("levels");
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
			int packageIndex, AlligatorApp game) throws InvalidJsonException {
		String leveltype = json.getString("type");
		Level level = null;
		if (leveltype.equals("multiple choice")) {
			level = fillMultipleChoice(json, levelIndex, packageIndex, game);
		} else if (leveltype.equals("color edit")) {
			JsonValue data = json.getChild("data");
			JsonValue initialBoard = data.getChild("initial constellation");
			JsonValue goalBoard = data.getChild("objective");
			Color[] userColors = getColorfromJson(data.getChild("user colors"));
			if (userColors.length != 6) {
				throw new InvalidJsonException(
						"The user color Array in this json file has to contain six items!");
			}
			Color[] blockedColors = getColorfromJson(data
					.getChild("blocked colors"));
			Animation animation = getAnimationfromJson(data, game);

			level = new ColorEditLevel(levelIndex, packageIndex,
					JsonToAlligator.convertBoard(initialBoard),
					JsonToAlligator.convertBoard(goalBoard), animation,
					userColors, blockedColors, json.getString("hint"),
					json.getString("description"),
					json.getInt("abort simulation after"));
		} else if (leveltype.equals("term edit")) {
			JsonValue data = json.getChild("data");
			JsonValue initialBoard = data.getChild("initial constellation");
			JsonValue goalBoard = data.getChild("objective");
			Color[] userColors = getColorfromJson(data.getChild("user colors"));
			if (userColors.length != 6) {
				throw new InvalidJsonException(
						"The user color Array in this json file has to contain six items!");
			}
			Color[] blockedColors = getColorfromJson(data
					.getChild("blocked colors"));
			Animation animation = getAnimationfromJson(data, game);
			level = new TermEditLevel(levelIndex, packageIndex,
					JsonToAlligator.convertBoard(initialBoard),
					JsonToAlligator.convertBoard(goalBoard), animation,
					userColors, blockedColors, json.getString("hint"),
					json.getString("description"),
					json.getInt("abort simulation after"));

		} else {
			throw new InvalidJsonException("Unspecified leveltype!");
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
	 * @throws InvalidJsonException
	 */
	private static Level fillMultipleChoice(JsonValue json, int levelIndex,
			int packageIndex, AlligatorApp game) throws InvalidJsonException {
		JsonValue data = json.getChild("data");
		JsonValue initialBoard = data.getChild("initial");
		int correctAnswer = data.getInt("correct answer");
		Board[] answers = getAnswersfromJson(data.getChild("answers"));
		Animation animation = getAnimationfromJson(data, game);
		Level level = new MultipleChoiceLevel(levelIndex, packageIndex,
				JsonToAlligator.convertBoard(initialBoard),
				answers[correctAnswer], animation, json.getString("hint"),
				json.getString("description"),
				json.getInt("abort simulation after"), answers, correctAnswer);
		return level;
	}

	/**
	 * Method to generate a Board Array from the given json.
	 * 
	 * @param json
	 *            the JsonValue the Array should be generated from
	 * @return the generated Board Array
	 * @throws InvalidJsonException
	 *             if json has the wrong format
	 */
	private static Board[] getAnswersfromJson(JsonValue json)
			throws InvalidJsonException {
		int size = json.size;
		if (!json.isArray()) {
			throw new InvalidJsonException(
					"There seems to be no answer array in this json file.");
		} else if (size != 3) {
			throw new InvalidJsonException(
					"The number of naswers should be three!");
		}
		Board[] answers = new Board[size];
		for (int i = 0; i < size; i++) {
			answers[i] = JsonToAlligator.convertBoard(json.get(i));
		}
		return answers;
	}

	/**
	 * Method to generate a Array of Colors from Json
	 * 
	 * @param json
	 *            the Json the Array should be generated from
	 * @return the generated Array
	 * @throws InvalidJsonException
	 *             if the Json has a wrong format
	 */
	private static Color[] getColorfromJson(JsonValue json)
			throws InvalidJsonException {
		int size = json.size;
		if (!json.isArray()) {
			throw new InvalidJsonException(
					"There seems to be no Color array in this json file.");
		}
		Color[] color = new Color[size];
		for (int i = 0; i < size; i++) {
			color[i] = new Color(json.getInt(i));
		}

		for (int i = 0; i < size; i++) {
			int id = color[i].getId();
			for (int k = 0; k < i; k++) {
				if (id == color[k].getId()) {
					throw new InvalidJsonException(
							"There is two times the same color.");
				}
			}
		}
		return color;

	}

	/**
	 * Method to generate Animation from a path given in the json.
	 * 
	 * @param json
	 *            JsonValue containing the path
	 * @param game
	 *            context of the game
	 * @return the Animation created
	 */
	public static Animation getAnimationfromJson(JsonValue json,
			AlligatorApp game) {
		/*
		AssetManager manager = game.getAssetManager();
		Texture animationSheet = manager.get(json.getString("animation"),
				Texture.class);
		TextureRegion[][] tmp = TextureRegion.split(animationSheet,
				animationSheet.getWidth() / FRAME_COLS,
				animationSheet.getHeight() / FRAME_ROWS);
		TextureRegion[] animationFrames = new TextureRegion[FRAME_COLS
				* FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				animationFrames[index++] = tmp[i][j];
			}
		}

		// TODO passt die frametime?
		return new Animation(0.025f, animationFrames);
		*/
		return null;
	}
}
