package de.croggle.util.convert;

import java.util.Iterator;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;

public class JsonToAlligator {

	/**
	 * Cannot instantiate this helper class.
	 */
	private JsonToAlligator() {

	}

	/**
	 * Converts a json-formatted string into an alligator constellation.
	 * 
	 * @param json
	 *            the json-formatted string to be parsed into an alligator
	 *            constellation
	 * @return an alligator constellation based on the given json string
	 */
	public static BoardObject convert(String json) {
		JsonReader reader = new JsonReader();
		JsonValue val = reader.parse(json);

		return convert(val);
	}

	/**
	 * Converts a JsonValue into an alligator constellation.
	 * 
	 * @param json
	 *            the JsonValue to be parsed into an alligator constellation
	 * @return an alligator constellation based on the given JsonValue
	 */
	public static BoardObject convert(JsonValue json) {
		if (json.hasChild("families")) {
			return toBoard(json);
		} else {
			return dispatchInternalBoardObject(json);
		}
	}

	/**
	 * Dispatches a JsonValue by examining its "type" attribute to the
	 * respective build*er function.
	 * 
	 * @param json
	 *            a JsonValue representing a valid InternalBoardObject
	 * @return the alligator constellation belonging to the given JsonValue
	 */
	private static InternalBoardObject dispatchInternalBoardObject(
			JsonValue json) {
		String type = json.getString("type");
		if (type.equals("egg")) {
			return buildEgg(json);
		} else if (type.equals("colored alligator")) {
			return buildColoredAlligator(json);
		} else if (type.equals("aged alligator")) {
			return buildAgedAlligator(json);
		} else {
			throw new IllegalArgumentException(
					"JsonValue json does not contain a valid type: " + type);
		}
	}

	/**
	 * Builds an Egg object from the given JsonValue.
	 * 
	 * @param egg
	 *            a JsonValue to be parsed
	 * @return an Egg object matching the description given in "egg"
	 */
	private static Egg buildEgg(JsonValue egg) {
		boolean movable = egg.getBoolean("movable");
		boolean removable = egg.getBoolean("removable");
		boolean recolorable = egg.getBoolean("recolorable");

		int colorId = egg.getInt("color");
		Color c;
		if (colorId < 0) {
			c = Color.uncolored();
		} else {
			c = new Color(colorId);
		}

		Egg e = new Egg(movable, removable, c, recolorable);

		return e;
	}

	/**
	 * Builds an AgedAlligator object from the given JsonValue.
	 * 
	 * @param agedAlligator
	 *            a JsonValue to be parsed
	 * @return an AgedAlligator object matching the description given in
	 *         "agedAlligator"
	 */
	private static AgedAlligator buildAgedAlligator(JsonValue agedAlligator) {
		boolean movable = agedAlligator.getBoolean("movable");
		boolean removable = agedAlligator.getBoolean("removable");

		AgedAlligator aa = new AgedAlligator(movable, removable);
		fillParent(aa, agedAlligator, "children");

		return aa;
	}

	/**
	 * Builds an ColoredAlligator object from the given JsonValue.
	 * 
	 * @param coloredAlligator
	 *            a JsonValue to be parsed
	 * @return an ColoredAlligator object matching the description given in
	 *         "coloredAlligator"
	 */
	private static ColoredAlligator buildColoredAlligator(
			JsonValue coloredAlligator) {
		boolean movable = coloredAlligator.getBoolean("movable");
		boolean removable = coloredAlligator.getBoolean("removable");
		boolean recolorable = coloredAlligator.getBoolean("recolorable");

		int colorId = coloredAlligator.getInt("color");
		Color c;
		if (colorId < 0) {
			c = Color.uncolored();
		} else {
			c = new Color(colorId);
		}

		ColoredAlligator ca = new ColoredAlligator(movable, removable, c,
				recolorable);
		fillParent(ca, coloredAlligator, "children");

		return ca;
	}

	/**
	 * Traverses the list of (json-formatted) children, given by its name, of a
	 * given JsonValue, and adds InternalBoardObjects built from them to the
	 * list of children of the given (alligator) Parent object.
	 * 
	 * @param p
	 *            the Parent, whose children are to be added
	 * @param pJson
	 *            the JsonValue belonging to the given parent, to retrieve the
	 *            children from
	 * @param childListName
	 *            the name of the list containing all children of the given
	 *            JsonValue parent.
	 */
	private static void fillParent(Parent p, JsonValue pJson,
			String childListName) {
		JsonValue children = pJson.get(childListName);
		if (!children.isArray()) {
			throw new IllegalArgumentException(
					"Given parent does not contain a List of children in child \""
							+ childListName + "\"");
		}
		Iterator<JsonValue> it = children.iterator();
		while (it.hasNext()) {
			InternalBoardObject child = dispatchInternalBoardObject(it.next());
			p.addChild(child);
		}
	}

	/**
	 * Builds a board object from the given JsonValue.
	 * 
	 * @param board
	 *            the board's JsonValue representation to be parsed
	 * @return a board matching the constellation description in "board"
	 */
	private static Board toBoard(JsonValue board) {
		Board b = new Board();
		fillParent(b, board, "families");
		return b;
	}
}
