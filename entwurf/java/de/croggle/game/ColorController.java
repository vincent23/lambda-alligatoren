package de.croggle.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The color controller manages colors in the game. It is mainly responsible for
 * mapping the virtual colors of board objects consistently on real life colors.
 * Additionally, it provides functionality for generating new colors used on boards
 * if needed by the simulator after applying recolor rules.
 * 
 * The terms "blocked" and "usable" for colors refer to which colors are blocked
 * by the level specification (blocked) and which are used by the game to let
 * the user recolor elements (usable).
 * 
 * @navassoc 1 - * de.croggle.game.Color
 */
public class ColorController {
	private List<de.croggle.game.Color> usableColors;
	private List<de.croggle.game.Color> bannedColors;
	private Map<de.croggle.game.Color, com.badlogic.gdx.graphics.Color> lookup;

	/**
	 * Initializes the color controller with no colors blocked, no colors usable
	 * and no colors in use.
	 */
	public ColorController() {
		usableColors = new ArrayList<de.croggle.game.Color>();
		bannedColors = new ArrayList<de.croggle.game.Color>();
		lookup = new HashMap<de.croggle.game.Color, com.badlogic.gdx.graphics.Color>(30);
	}

	/**
	 * Performs a lookup upon a given model.Color to consistently map it to a
	 * real life color.
	 * 
	 * @param color
	 *            the color, whose real life color is to be looked up
	 * @return an libgdx color to be actually rendered to represent the virtual
	 *         color of a BoardObject
	 */
	public com.badlogic.gdx.graphics.Color getRepresantation(de.croggle.game.Color color) {
		return null;
	}

	/**
	 * Performs a lookup upon a given libgdx.Color and returns the BoardObject
	 * Color represented by it.
	 * 
	 * @param color
	 *            the color, which represents the color to be looked up
	 * @return a model color that is represented by the given libgdx Color
	 */
	public com.badlogic.gdx.graphics.Color getAssociatedColor(com.badlogic.gdx.graphics.Color color) {
		return null;
	}

	/**
	 * Returns a new color to be used by the simulator on a board and
	 * assigns an actual libgdx Color to it.
	 * This is equivalent to calling <code>requestColor(allUsedColors)</code>, whith <code>allUsedColors</code> being an array of all colors used on the board.
	 * 
	 * @return a new color to be used on the board
	 * @throws ColorOverflowException if there is no color available
	 */
	public de.croggle.game.Color requestColor() throws ColorOverflowException {
		return null;
	}

	/**
	 * Returns a color, which does not appear in <code>usedColors</code>, to be used by the simulator on a board for recoloring.
	 * If all available colors are in usedColors, a new color is created.
	 *
	 * @param usedColors a set of colors which are already used
	 * @return a color to be used on the board
	 * @throws ColorOverflowException if there is no color available
	 */
	public de.croggle.game.Color requestColor(de.croggle.game.Color[] usedColors) {
	}

	/**
	 * Adds a model color to the list of the colors usable by the user to
	 * recolor board objects.
	 * 
	 * @param color
	 *            a color to be marked as usable
	 */
	public void addUsableColor(de.croggle.game.Color color) {

	}

	/**
	 * Adds a model color to the list of colors that may not be used to recolor
	 * board objects.
	 * 
	 * @param color
	 *            a color to be blocked
	 */
	public void addBlockedColor(de.croggle.game.Color color) {

	}

	/**
	 * 
	 * @return an array of all currently usable colors
	 */
	public de.croggle.game.Color[] getUsableColors() {
		return null;
	}

	/**
	 * Look up whether a given color is blocked, i.e. it may not occur in the
	 * list of usable colors
	 * 
	 * @param color
	 * @return whether the given color is blocked or not
	 */
	public boolean isBlocked(de.croggle.game.Color color) {
		return false;
	}

	/**
	 * 
	 * 
	 * @param color
	 *            the color whose usability should be tested
	 * @return whether the given color is usable or not
	 */
	public boolean isUsable(de.croggle.game.Color color) {
		return false;
	}
}
