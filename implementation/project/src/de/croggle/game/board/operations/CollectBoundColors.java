package de.croggle.game.board.operations;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

/**
 * A visitor for collecting all the colors of alligators in a family. This is
 * equivalent to the set of variables which are bound in a given subterm.
 * 
 * TODO implement
 */
public class CollectBoundColors implements BoardObjectVisitor {
	private CollectBoundColors(BoardObject family) {
	}

	/**
	 * Returns the set of colors of alligators in the given family.
	 * 
	 * @param family
	 *            the family to examine
	 * @return the set of bound colors
	 */
	public static Color[] collect(BoardObject family) {
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
