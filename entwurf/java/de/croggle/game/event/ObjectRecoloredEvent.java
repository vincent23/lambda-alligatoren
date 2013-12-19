package de.croggle.game.event;

import de.croggle.game.board.InternalBoardObject;

/**
 * This board event is produced when a simulator performs a recoloring on
 * an internal board object on the board. E.g. this can be caused by the
 * player or alternatively when an alpha conversion occurs.
 */
public class ObjectRecoloredEvent {
	
	/**
	 * Returns the board object whose color was altered by the simulator.
	 * 
	 * @return The board object whose color changed.
	 */
	public InternalBoardObject getRecoloredObject() {
		return null;
	}
}
