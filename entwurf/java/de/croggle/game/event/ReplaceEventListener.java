package de.croggle.game.event;

import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;

/**
 * 
 * The interface for listeners specifically listening to ReplaceEvents.
 * This board event is produced after a simulator has realized the end of
 * the eating rule. That is, when a copy of a subtree (an alligator with its family
 * or alternatively just an egg) "hatched out" of an egg.
 * Event listeners can assume, that the replacement has already completely taken place.
 * That means, that bornFamily has its new parent set and the replacedEgg is not in the list of childs of its parent any more.
 */
public interface ReplaceEventListener {
	
	/**
	 * Receive an object replaced event for further processing.
	 * E.g. the renderer can determine by accepting a replaced event where
	 * an egg's rendering needs to be replaced with a new family.
	 * 
	 * @param replacedEgg The egg which hatches out to become the born family
	 * @param bornFamily The family which will emerge from an egg.
	 */
	public void onReplace(Egg replacedEgg, InternalBoardObject bornFamily);
	
}
