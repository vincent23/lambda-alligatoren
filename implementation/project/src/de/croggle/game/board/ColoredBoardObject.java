package de.croggle.game.board;

import de.croggle.game.Color;

/**
 * Interface of all colorable and recolorable board objects. In other words,
 * this interface adds naming functionality to syntax tree elements.
 */
public interface ColoredBoardObject extends InternalBoardObject {

	/**
	 * Returns whether the color of the board object can be changed by the user.
	 * 
	 * @return true if the object can be recolored, otherwise false
	 */
	boolean isRecolorable();

	/**
	 * Returns the color that represents a variable's name on the board.
	 * 
	 * @return the current color of the board object
	 */
	Color getColor();

	/**
	 * Sets the color of the board object. In placement mode: Set only if it is
	 * marked as recolorable.
	 * 
	 * @param c
	 *            the new color for the board object
	 */
	void setColor(Color c);
}
