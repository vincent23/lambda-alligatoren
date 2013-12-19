package de.croggle.game.event;

import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.InternalBoardObject;

/**
 * This board event is produced when a simulator applies the begin of
 * the eating rule. That is, when a subtree (an alligator with its family
 * or alternatively just an egg) is "eaten" by another alligator.
 */
public class EatEvent implements BoardEvent {

	public EatEvent(ColoredAlligator eater, InternalBoardObject eatenFamily) {
	}

	/**
	 * Returns the subtree that is to be removed from the tree of alligator families.
	 * 
	 * @return The parent of all eaten objects, which is being eaten himself.
	 */
	public InternalBoardObject getEatenFamily() {
		return null;
	}
	
	/**
	 * Returns the alligator being responsible for the eating rule to be 
	 * applied. (The lambda abstraction on which a beta conversion is performed).
	 * 
	 * @return The alligator "eating" the EatenFamily.
	 */
	public ColoredAlligator getEater() {
		return null;
	}
	
}
