package de.croggle.controller.event;

/**
 * 
 * The interface for listeners specifically listening to ReplaceEvents.
 * @depend - <listens_to> - ReplaceEvent
 */
public interface ReplaceEventListener extends BoardEventListener {
	
	/**
	 * Receive an object replaced event for further processing.
	 * E.g. the renderer can determine by accepting a replaced event where
	 * an egg's rendering needs to be replaced with a new family.
	 * 
	 * @param event The ReplaceEvent to be processed.
	 */
	public void callback(ReplaceEvent event);
	
}
