package de.croggle.game.visitor;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;

/**
 * A visitor for counting the number of objects in a family.
 */
public class CountBoardObjectsVisitor implements BoardObjectVisitor {
	private int count;

	/**
	 *
	 */
	private CountBoardObjectsVisitor() {
		this.count = 0;
	}

	/**
	 * Count the number of objects in a family.
	 *
	 * @param family the family whose members should be counted
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
