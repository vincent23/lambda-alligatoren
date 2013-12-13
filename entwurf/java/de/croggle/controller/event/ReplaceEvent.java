package de.croggle.controller.event;

import de.croggle.model.Egg;
import de.croggle.model.InternalBoardObject;

/**
 * This board event is produced when a simulator realizes the end of
 * the eating rule. That is, when a copy of a subtree (an alligator with its family
 * or alternatively just an egg) "hatches out" of an egg.
 */
public class ReplaceEvent implements BoardEvent {
	
	/**
	 * Returns the subtree that was added. It will become a child to the original 
	 * parent of the egg that emerged into the returned subtree.
	 * 
	 * @return The family which will emerge from an egg.
	 */
	public InternalBoardObject getBornFamily() {
		return null;
	}
	
	/**
	 * Returns the egg where a family will hatch out. 
	 * (A lambda variable on which a beta conversion is performed).
	 * 
	 * @return The egg which hatches out to become the born family
	 */
	public Egg getReplacedEgg() {
		return null;
	}
	
}
