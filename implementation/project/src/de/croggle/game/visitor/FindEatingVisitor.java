package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

/**
 * A visitor for finding a colored alligator which can eat a family next to it.
 */
public class FindEatingVisitor implements BoardObjectVisitor {
	private ColoredAlligator eater;

	/**
	 * Creates a new visitor with no found alligator.
	 */
	private FindEatingVisitor() {
		this.eater = null;
	}

	/**
	 * Called when the first alligator which can eat is found.
	 * 
	 * @param eater
	 *            the eating alligator
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
	 * Search a colored alligator which can eat a family next to it.
	 * 
	 * @param board
	 *            the board in which colored alligators should be searched
	 * @return the eating alligator if one was found, otherwise null
	 */
	public static ColoredAlligator findEater(Board board) {
		return null;
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
