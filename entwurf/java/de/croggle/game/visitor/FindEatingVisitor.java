package de.croggle.game.visitor;

import de.croggle.game.board.ColoredAlligator;

/**
 *
 */
public class FindEatingVisitor implements BoardObjectVisitor {
	private ColoredAlligator eater;

	public FindEatingVisitor() {
		this.eater = null;
	}

	/**
	 *
	 */
	private void foundEater(ColoredAlligator eater) {
		this.eater = eater;
	}

	/**
	 *
	 */
	private boolean hasFoundEater() {
		return eater != null;
	}

	/**
	 *
	 */
	public ColoredAlligator getEater() {
		return eater;
	}
}
