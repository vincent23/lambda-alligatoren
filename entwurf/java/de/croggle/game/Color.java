package de.croggle.game;

/**
 * A color represents a variable name.
 */
public class Color {
	private int id;

	/**
	 * Creates a color with the given id. The id needs to be between 0 and 29
	 * and represents a certain "real" color according to the ColorController.
	 * 
	 * @param id
	 *            the identifying color id
	 * @throws IllegalArgumentException
	 *             when the id is not a number between 0 and 29
	 */
	public Color(int id) {

	}

	/**
	 * Gets the globally unique color id between 0 and 29.
	 */
	public int getId() {
		return 0;
	}
}
