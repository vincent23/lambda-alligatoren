package de.croggle.controller.event;

import de.croggle.model.ColoredAlligator;
import de.croggle.model.InternalBoardObject;

/**
 * This board event is produced when a simulator applies the begin of
 * the eating rule. That is, when a subtree (an alligator with its family
 * or alternatively just an egg) is "eaten" by another alligator.
 */
public class EatEvent implements BoardEvent {
	
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
