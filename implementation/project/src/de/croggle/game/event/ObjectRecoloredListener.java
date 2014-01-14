package de.croggle.game.event;

import de.croggle.game.board.ColoredBoardObject;

/**
 * 
 * Interface for listeners specifically listening to the onObjectRecolored
 * event. This event is produced when a simulator performs a recoloring on an
 * internal board object on the board. E.g. this can be caused by the player or
 * alternatively when an alpha conversion occurs.
 */
public interface ObjectRecoloredListener {

	/**
	 * Receive an object recolored event for further processing. E.g. the
	 * renderer can determine by accepting an eat event where a board object has
	 * to be recolored.
	 * 
	 * @param recoloredObject
	 *            the board object whose color changed
	 */
	public void onObjectRecolored(ColoredBoardObject recoloredObject);

}
