package de.croggle.game;

/**
 * 
 * Interface for the event handlers
 *
 */
public interface AbstractEventHandler {

	/**
	 * Receive an event of a specific type.
	 */
	public void receiveEvent(Event event);
	
	/**
	 * Process all received events.
	 */
	public void processEvents();

}
