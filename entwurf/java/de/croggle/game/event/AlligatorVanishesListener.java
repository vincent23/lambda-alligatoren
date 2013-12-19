package de.croggle.game.event;

import de.croggle.game.board.Alligator;

/**
 * 
 * The interface for listeners specifically listening to AlligatorVanishesEvents.
 * This board event is produced when a simulator removes any instance of
 * an alligator (aged or colored) from its associated board. The class is kept
 * general for both the rendered animation and the vanished alligator statistics
 * are either similar or behave the same for both types of alligators.
 */
public interface AlligatorVanishesListener {
	
	/**
	 * Receive an alligator vanishes event for further processing.
	 * E.g. the statistics manager can count how many alligators have
	 * vanished/been transformed on the boar in a game.
	 * 
	 * @param alligator The vanishing alligator.
	 */
	public void onAlligatorVanishes(Alligator alligator);
	
}
