package de.croggle.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The color controller manages colors in the game. He is mainly responsible to
 * map the virtual colors of board objects consistently on real life colors.
 * Additionally it provides functionality to generate new colors used on boards
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
	private Map<de.croggle.game.Color, java.awt.Color> lookup;

	/**
	 * Initializes the color controller with no colors blocked, no colors usable
	 * and no colors in use.
	 */
	public ColorController() {
		usableColors = new ArrayList<de.croggle.game.Color>();
		bannedColors = new ArrayList<de.croggle.game.Color>();
		lookup = new HashMap<de.croggle.game.Color, java.awt.Color>(30);
	}

	/**
	 * Performs a lookup upon a given model.Color to consistently map it to a
	 * real life color.
	 * 
	 * @param color
	 *            The color, whose real life color is to be looked up
	 * @return An awt color to be actually rendered to represent the virtual
	 *         color of a BoardObject
	 */
	public java.awt.Color getRepresantation(de.croggle.game.Color color) {
		return null;
	}

	/**
	 * Performs a lookup upon a given awt.Color and returns the BoardObject
	 * Color represented by it.
	 * 
	 * @param color
	 *            The color, which represents the color to be looked up
	 * @return A model color that is represented by the given awt.Color
	 */
	public java.awt.Color getAssociatedColor(java.awt.Color color) {
		return null;
	}

	/**
	 * Returns a new color to be used by the simulator on a board and already
	 * assigns an actual awt.Color to it.
	 * 
	 * @return A new color to be used on the board
	 */
	public de.croggle.game.Color requestColor() {
		return null;
	}

	/**
	 * Adds a model color to the list of the colors usable by the user to
	 * recolor board objects.
	 * 
	 * @param color
	 *            A color to be marked as usable
	 */
	public void addUsableColor(de.croggle.game.Color color) {

	}

	/**
	 * Adds a model color to the list of colors that may not be used to recolor
	 * board objects.
	 * 
	 * @param color
	 *            A color to be blocked
	 */
	public void addBlockedColor(de.croggle.game.Color color) {

	}

	/**
	 * 
	 * @return An array of all currently usable colors
	 */
	public de.croggle.game.Color[] getUsableColors() {
		return null;
	}

	/**
	 * Look up whether a given color is blocked, i.e. it may not occur in the
	 * list of usable colors
	 * 
	 * @param color
	 * @return Whether the given color is blocked or not
	 */
	public boolean isBlocked(de.croggle.game.Color color) {
		return false;
	}

	/**
	 * 
	 * 
	 * @param color
	 *            the color whose usability should be tested
	 * @return Whether the given color is usable or not
	 */
	public boolean isUsable(de.croggle.game.Color color) {
		return false;
	}
}
