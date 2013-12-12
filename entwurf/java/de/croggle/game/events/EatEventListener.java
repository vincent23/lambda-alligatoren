package de.croggle.game.events;

/**
 * 
 * The interface for listeners specifically listening to EatEvents.
 *
 */
public interface EatEventListener extends BoardEventListener {

	/**
	 * Receive an event of a specific type.
	 */
	public void callback(EatEvent event);

}
