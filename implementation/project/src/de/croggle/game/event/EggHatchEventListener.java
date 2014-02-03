package de.croggle.game.event;

import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;

/**
 * 
 * Interface for listeners specifically listening to the onHatched event. This
 * event is produced after a simulator has realized the end of the eating rule.
 * That is, when a copy of a subtree (an alligator with its family or
 * alternatively just an egg) "hatched out" of an egg.
 * 
 * Event listeners can assume that the replacement has already completely taken
 * place. That means, that bornFamily has its new parent set and the replacedEgg
 * is not in the list of children of its parent any more.
 */
public interface EggHatchEventListener {

	/**
	 * Receive an object replaced event for further processing. E.g. the
	 * renderer can determine by accepting a replaced event where an egg's
	 * rendering needs to be replaced with a new family.
	 * 
	 * @param replacedEgg
	 *            the egg which hatches out to become the born family
	 * @param bornFamily
	 *            the family which will emerge from an egg
	 */
	public void onHatched(Egg replacedEgg, InternalBoardObject bornFamily);

}
