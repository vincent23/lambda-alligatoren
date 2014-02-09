package de.croggle.game;

import java.util.ArrayList;
import java.util.List;

/**
 * The color controller manages colors in the game. It is mainly responsible for
 * mapping the virtual colors of board objects consistently on real life colors.
 * Additionally, it provides functionality for generating new colors used on
 * boards if needed by the simulator after applying recolor rules.
 * 
 * The terms "blocked" and "usable" for colors refer to which colors are blocked
 * by the level specification (blocked) and which are used by the game to let
 * the user recolor elements (usable).
 */
public class ColorController {
	private final de.croggle.game.Color uncolored;
	private List<de.croggle.game.Color> usableColors;
	private List<de.croggle.game.Color> bannedColors;

	/**
	 * Initializes the color controller with no colors blocked, no colors usable
	 * and no colors in use.
	 */
	public ColorController() {
		usableColors = new ArrayList<de.croggle.game.Color>();
		bannedColors = new ArrayList<de.croggle.game.Color>();
		uncolored = de.croggle.game.Color.uncolored();
	}

	/**
	 * Performs a lookup upon a given model. Color to consistently map it to a
	 * real life color.
	 * 
	 * @param color
	 *            the color whose real life color is to be looked up
	 * @return an libgdx color to be actually rendered to represent the virtual
	 *         color of a BoardObject
	 */
	public com.badlogic.gdx.graphics.Color getRepresentation(
			de.croggle.game.Color color) {
		if (color == uncolored) {
			return de.croggle.util.convert.ColorConvert.fromHexString("#FFFFFF");
		} else {
			// return lookup.get(color);
			return de.croggle.game.Color.getRepresentation(color);
		}
	}

	/**
	 * Performs a lookup upon a given libgdx.Color and returns the BoardObject
	 * Color represented by it.
	 * 
	 * @param color
	 *            the color which represents the color to be looked up
	 * @return a model color that is represented by the given libgdx Color
	 */
	public de.croggle.game.Color getAssociatedColor(
			com.badlogic.gdx.graphics.Color color) {
		// for (Entry<de.croggle.game.Color, com.badlogic.gdx.graphics.Color>
		// entry : lookup
		// .entrySet()) {
		// if (color.equals(entry.getValue())) {
		// return entry.getKey();
		// }
		// }
		com.badlogic.gdx.graphics.Color[] colors = de.croggle.game.Color
				.getRepresentations();
		for (int i = 0; i < colors.length; i++) {
			if (colors[i].equals(color)) {
				return new de.croggle.game.Color(i);
			}
		}
		return null;
	}

	public de.croggle.game.Color getUncolored() {
		return this.uncolored;
	}

	/**
	 * Returns a new color to be used by the simulator on a board and assigns an
	 * actual libgdx Color to it. This is equivalent to calling
	 * <code>requestColor(allUsedColors)</code>, with <code>allUsedColors</code>
	 * being an array of all colors used on the board.
	 * 
	 * @return a new color to be used on the board
	 * @throws ColorOverflowException
	 *             if there is no color available
	 */
	public de.croggle.game.Color requestColor() throws ColorOverflowException {
		// if (lookup.size() >= MAX_COLORS) {
		// throw new ColorOverflowException(
		// "Exceeded maximum number of colors: " + MAX_COLORS);
		// }
		//
		// de.croggle.game.Color c = new de.croggle.game.Color(lookup.size());
		// this.lookup.put(c,
		// de.croggle.util.convert.Color.fromHexString(colors[c.getId()]));
		// return c;
		return new de.croggle.game.Color(0);
	}

	/**
	 * Returns a color which does not appear in <code>usedColors</code> to be
	 * used by the simulator on a board for recoloring. If all available colors
	 * are in usedColors, a new color is created.
	 * 
	 * @param usedColors
	 *            a set of colors which are already used
	 * @return a color to be used on the board
	 * @throws ColorOverflowException
	 *             if there is no color available
	 */
	public de.croggle.game.Color requestColor(de.croggle.game.Color[] usedColors)
			throws ColorOverflowException {
		if (usedColors.length >= de.croggle.game.Color.MAX_COLORS) {
			throw new ColorOverflowException(
					"Exceeded maximum number of colors: "
							+ de.croggle.game.Color.MAX_COLORS);
		}
		java.util.Arrays.sort(usedColors);
		int i;
		for (i = 0; i < usedColors.length; i++) {
			if (usedColors[i].getId() != i) {
				break;
			}
		}

		de.croggle.game.Color c = new de.croggle.game.Color(i);
		// if (this.lookup.size() <= i) {
		// this.lookup.put(c,
		// de.croggle.util.convert.Color.fromHexString(colors[i]));
		// }

		return c;
	}

	/**
	 * Adds a model color to the list of the colors usable by the user to
	 * recolor board objects.
	 * 
	 * @param color
	 *            a color to be marked as usable
	 */
	public void addUsableColor(de.croggle.game.Color color) {
		this.usableColors.add(color);
	}

	/**
	 * Adds a model color to the list of colors that may not be used to recolor
	 * board objects.
	 * 
	 * @param color
	 *            a color to be blocked
	 */
	public void addBlockedColor(de.croggle.game.Color color) {
		this.bannedColors.add(color);
	}

	/**
	 * 
	 * @return an array of all currently usable colors
	 */
	public de.croggle.game.Color[] getUsableColors() {
		return this.usableColors
				.toArray(new de.croggle.game.Color[this.usableColors.size()]);
	}

	/**
	 * Look up whether a given color is blocked, i.e. it may not occur in the
	 * list of usable colors
	 * 
	 * @param color
	 * @return whether the given color is blocked or not
	 */
	public boolean isBlocked(de.croggle.game.Color color) {
		return this.bannedColors.contains(color);
	}

	/**
	 * 
	 * 
	 * @param color
	 *            the color whose usability should be tested
	 * @return whether the given color is usable or not
	 */
	public boolean isUsable(de.croggle.game.Color color) {
		return this.usableColors.contains(color);
	}
}
