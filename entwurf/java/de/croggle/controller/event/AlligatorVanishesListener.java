package de.croggle.controller.event;

/**
 * 
 * The interface for listeners specifically listening to AlligatorVanishesEvents.
 *
 */
public interface AlligatorVanishesListener extends BoardEventListener {
	
	/**
	 * Receive an alligator vanishes event for further processing.
	 * E.g. the statistics manager can count how many alligators have
	 * vanished/been transformed on the boar in a game.
	 * 
	 * @param event The AlligatorVanishesEvents to be processed.
	 */
	public void callback(AlligatorVanishesEvent event);
	
}