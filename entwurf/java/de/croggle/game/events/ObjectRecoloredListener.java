package de.croggle.game.events;

/**
 * 
 * The interface for listeners specifically listening to ObjectRecoloredEvents.
 *
 */
public interface ObjectRecoloredListener extends BoardEventListener {
	
	/**
	 * Receive an object recolored event for further processing.
	 * E.g. the renderer can determine by accepting an eat event where
	 * a board object has to be recolored.
	 * 
	 * @param event The ObjectRecoloredEvent to be processed.
	 */
	public void callback(ObjectRecoloredEvent event);
	
}
