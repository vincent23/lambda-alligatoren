package de.croggle.game.event;

/**
 * 
 * The interface for listeners specifically listening to EatEvents.
 * @depend - <listens_to> - EatEvent
 */
public interface EatEventListener {

	/**
	 * Receive an eat event for further processing.
	 * E.g. the renderer can determine by accepting an eat event where
	 * an eat animation has to be played.
	 * 
	 * @param event The eat event to be processed.
	 */
	public void callback(EatEvent event);

}
