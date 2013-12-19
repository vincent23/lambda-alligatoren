package de.croggle.game.event;

import de.croggle.game.board.Alligator;

/**
 * This board event is produced when a simulator removes any instance of
 * an alligator (aged or colored) from its associated board. The class is kept
 * general for both the rendered animation and the vanished alligator statistics
 * are either similar or behave the same for both types of alligators.
 */
public class AlligatorVanishesEvent {
	
	/**
	 * Returns the alligator that is removed/ vanishes from the board
	 * due to either an aged alligator has just one child left or a
	 * colored alligator has eaten another family.
	 * 
	 * @return The vanishing alligator.
	 */
	public Alligator getAlligator() {
		return null;
	}
	
}
