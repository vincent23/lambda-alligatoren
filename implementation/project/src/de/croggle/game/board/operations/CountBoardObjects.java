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
	 * Initializes the BoardObject counter with 0 BoardObjects counted.
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
		CountBoardObjects counter = new CountBoardObjects();
		family.accept(counter);
		return counter.count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {
		count++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		count++;
		alligator.acceptOnChildren(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		count++;
		alligator.acceptOnChildren(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {
		count++;
		board.acceptOnChildren(this);
	}

}
