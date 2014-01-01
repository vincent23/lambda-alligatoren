package de.croggle.game.board.operations;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

/**
 * A visitor for counting the number of objects in a family.
 */
public class CountBoardObjects implements BoardObjectVisitor {
	private int count;

	/**
	 *
	 */
	private CountBoardObjects() {
		this.count = 0;
	}

	/**
	 * Count the number of objects in a family.
	 * 
	 * @param family
	 *            the family whose members should be counted
	 * @return the number of family members
	 */
	public static int count(BoardObject family) {
		return 0;
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
