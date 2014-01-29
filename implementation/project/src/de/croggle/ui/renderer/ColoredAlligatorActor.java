package de.croggle.ui.renderer;

import de.croggle.game.board.ColoredAlligator;

/**
 * An actor used for representing a colored alligator.
 */
public class ColoredAlligatorActor extends ColoredBoardObjectActor {

	public ColoredAlligatorActor(ColoredAlligator alligator,
			boolean colorBlindEnabled) {
		super(alligator, colorBlindEnabled, "coloredalligator/foreground",
				"coloredalligator/background");
	}

	/**
	 * Signals the actor to (re-)enter the normal rendering state. That is, an
	 * alligator with a specific color, mouth closed. Will initiate a transition
	 * animation from mouth open to closed if it was open previously.
	 */
	public void enterNormalState() {

	}

	/**
	 * Signals the actor to enter the eating rendering state. That is, an
	 * alligator with a specific color, mouth opened. Will initiate a transition
	 * animation from mouth closed to open if it was closed previously.
	 */
	public void enterEatingState() {

	}

	/**
	 * Signals the actor to enter the dying rendering state. That is, an
	 * alligator with a specific color, mouth closed and turned on its back.
	 * Will initiate a transition animation from mouth open to closed if it was
	 * open previously. Also turns the alligator around 180 degree, if it wasn't
	 * in this state before.
	 */
	public void enterDyingState() {

	}
}
