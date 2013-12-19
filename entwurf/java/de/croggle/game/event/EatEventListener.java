package de.croggle.game.event;

import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.InternalBoardObject;

/**
 * 
 * The interface for listeners specifically listening to EatEvents.
 * This board event is produced when a simulator applies the begin of
 * the eating rule. That is, when a subtree (an alligator with its family
 * or alternatively just an egg) is "eaten" by another alligator.
 */
public interface EatEventListener {

	/**
	 * Receive an eat event for further processing.
	 * E.g. the renderer can determine by accepting an eat event where
	 * an eat animation has to be played.
	 * 
	 * @param eater The alligator "eating" the EatenFamily.
	 * @param eatenFamily The parent of all eaten objects, which is being eaten himself.
	 */
	public void onEat(ColoredAlligator eater, InternalBoardObject eatenFamily);

}
