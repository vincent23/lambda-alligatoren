package de.croggle.game.events;

import de.croggle.game.model.InternalBoardObject;

/**
 * This board event is produced when a simulator performs a recoloring on
 * an internal board object on the board. E.g. this can be caused by the
 * player or alternatively when an alpha conversion occurs.
 */
public class ObjectRecoloredEvent implements BoardEvent {
	
	/**
	 * Returns the board object whose color was altered by the simulator.
	 * 
	 * @return The board object whose color changed.
	 */
	public InternalBoardObject getRecoloredObject() {
		return null;
	}
}
