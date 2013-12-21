package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;

/**
 * A visitor for finding a colored alligator which can eat a family next to it.
 */
public class FindEatingVisitor implements BoardObjectVisitor {
	private ColoredAlligator eater;

	/**
	 * Creates a new visitor with no found alligator.
	 */
	public FindEatingVisitor() {
		this.eater = null;
	}

	/**
	 * Called when the first alligator which can eat is found.
	 *
	 * @param eater the eating alligator
	 */
	private void foundEater(ColoredAlligator eater) {
		this.eater = eater;
	}

	/**
	 * Returns whether the visitor has already found an eating alligator.
	 *
	 * @return true if an eater was found, false otherwise
	 */
	private boolean hasFoundEater() {
		return eater != null;
	}

	/**
	 * Gets the eating alligator that was found by the visitor.
	 *
	 * @return the eating alligator if it was found already, otherwise null
	 */
	public ColoredAlligator getEater() {
		return eater;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {

	}

}
