package de.croggle.game.event;

import de.croggle.game.board.AgedAlligator;

/**
 * 
 * Interface for listeners specifically listening to the onAgedAlligatorVanishes
 * event. This event is produced when a simulator removes any instance of an
 * aged alligator from its associated board. The class is kept general for both
 * the rendered animation and the vanished alligator statistics.
 * 
 * The event is fired <strong>after</strong> the alligator has been removed from
 * the main tree.
 */
public interface AgedAlligatorVanishesListener {

	/**
	 * Receive an alligator vanishes event for further processing. E.g. the
	 * statistics manager can count how many alligators have vanished/been
	 * transformed on the board in a game.
	 * 
	 * @param alligator
	 *            the vanishing alligator
	 * @param positionInParent
	 *            the index of the alligator that vanished in its parent's
	 *            children list, before it was removed
	 */
	public void onAgedAlligatorVanishes(AgedAlligator alligator,
			int positionInParent);

}
