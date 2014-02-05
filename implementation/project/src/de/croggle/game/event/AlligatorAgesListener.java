package de.croggle.game.event;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.ui.renderer.AgedAlligatorActor;
import de.croggle.ui.renderer.ColoredAlligatorActor;

/**
 * 
 * Interface for listeners specifically listening to the onAge event. This event
 * is produced after a simulator has realized the end of the eating rule and
 * needs to put a "parenthesis" AgedAlligator around the hatched expression.
 * 
 * Event listeners can assume that the replacement has already completely taken
 * place. That means, that aged has its new parent set and the colored alligator
 * is not in the list of children of its parent any more.
 */
public interface AlligatorAgesListener {

	/**
	 * Receive an alligator aged event for further processing. E.g. the renderer
	 * can replace the {@link ColoredAlligatorActor} with an
	 * {@link AgedAlligatorActor}
	 * 
	 * @param colored
	 *            {@link ColoredAlligator} that aged
	 * @param aged
	 *            {@link AgedAlligator} that replaces colored
	 */
	public void onAge(ColoredAlligator colored, AgedAlligator aged);

}
