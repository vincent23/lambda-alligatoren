package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;

/**
 * A visitor for checking whether the given Board represents a valid Lambda-Term (whether the evaluation is possilble or not).
 */
public class ValidationVisitor implements BoardObjectVisitor {
	/**
	 *
	 */
	private ValidationVisitor(BoardObject family) {
	}

	/**
	 * Checks whether the given family represents a valid LambdaTerm.
	 *
	 * @param family the family to check for validity
	 * @return true if the family is valid, false otherwise
	 */
	public static boolean isValid(BoardObject family) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board);

}
