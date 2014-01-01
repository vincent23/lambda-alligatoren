package de.croggle.game.board.operations;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

/**
 * A visitor for checking whether the given Board represents a valid term within
 * the lambda calculus (whether the evaluation is possible or not).
 */
public class ValidateConstellation implements BoardObjectVisitor {
	/**
	 *
	 */
	private ValidateConstellation(BoardObject family) {
	}

	/**
	 * Checks whether the given family represents a valid term within the lambda
	 * calculus.
	 * 
	 * @param family
	 *            the family to check for validity
	 * @return true if the family is valid, false otherwise
	 */
	public static boolean isValid(BoardObject family) {
		return false;
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
