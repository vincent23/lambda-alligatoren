package de.croggle.ui.renderer;

import de.croggle.game.board.Egg;

/**
 * An actor used for representing an egg.
 */
public class EggActor extends ColoredBoardObjectActor {

	public EggActor(Egg egg, boolean colorBlindEnabled) {
		super(egg, colorBlindEnabled, "egg/foreground", "egg/background");
	}

	/**
	 * Signals the actor to (re-)enter the normal rendering state. That is, an
	 * egg with a specific color.
	 */
	public void enterNormalState() {

	}

	/**
	 * Signals the actor to enter the hatching rendering state. That is,
	 * scattered eggshell with the specific color. Will initiate a transition
	 * animation from a normal egg to the broken eggshell.
	 */
	public void enterHatchingState() {

	}
}
