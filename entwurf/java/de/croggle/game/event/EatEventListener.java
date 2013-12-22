package de.croggle.game.event;

import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.InternalBoardObject;

/**
 * 
 * Interface for listeners specifically listening to the onEat event.
 * This event is produced when a simulator applies the begin of
 * the eating rule. That is, when a subtree (an alligator with its family
 * or alternatively just an egg) is "eaten" by another alligator.
 */
public interface EatEventListener {

	/**
	 * Receive an eat event for further processing.
	 * E.g. the renderer can determine by accepting an eat event where
	 * an eat animation has to be played.
	 * 
	 * @param eater the alligator "eating" the EatenFamily
	 * @param eatenFamily the parent of all eaten objects, which is being eaten himself
	 */
	public void onEat(ColoredAlligator eater, InternalBoardObject eatenFamily);

}
