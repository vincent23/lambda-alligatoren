package de.croggle.game.board;

import de.croggle.game.Color;

/**
 * The super interface of all colorable and recolorable board objects.
 * In other words, this interface adds naming functionality to syntax tree elements.
 */
public interface ColoredBoardObject {
	
	/**
	 * Gets whether the object is protected from user interactions involving colors or not.
	 * @return true if the object can be recolored, otherwise false
	 */
	boolean isRecolorable();
	
	/**
	 * Access the color that represents a variable's name on the board.
	 * @return the current color of the BoardObject
	 */
	 Color getColor();
	
	/**
	 * Set the color of the BoardObject, if it is marked as recolorable.
	 * If the method is called and the 
	 * @param c the new color the caller wants the BoardObject to have
	 * @throws RecolorNotAllowedException if recoloring the ColoredBoardObject is not permitted.
	 */
	void setColor(Color c) throws RecolorNotAllowedException;
}
