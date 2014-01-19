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
	private boolean countBoard;
	private boolean countEgg;
	private boolean countAgedAlligator;
	private boolean countColoredAlligator;

	/**
	 * Initializes the BoardObject counter with 0 BoardObjects counted.
	 */
	private CountBoardObjects(boolean countBoard, boolean countEgg,
			boolean countAgedAlligator, boolean countColoredAlligator) {
		this.count = 0;
		this.countBoard = countBoard;
		this.countEgg = countEgg;
		this.countAgedAlligator = countAgedAlligator;
		this.countColoredAlligator = countColoredAlligator;
	}

	/**
	 * Count the number of objects in a family.
	 * 
	 * @param family
	 *            the family whose members should be counted
	 * @return the number of family members
	 */
	public static int count(BoardObject family) {
		return CountBoardObjects.count(family, true, true, true, true);
	}

	public static int count(BoardObject family, boolean countBoard,
			boolean countEgg, boolean countAgedAlligator,
			boolean countColoredAlligator) {
		CountBoardObjects counter = new CountBoardObjects(countBoard, countEgg,
				countAgedAlligator, countColoredAlligator);
		family.accept(counter);
		return counter.count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {
		if (countEgg) {
			count++;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		if (countColoredAlligator) {
			count++;
		}
		alligator.acceptOnChildren(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		if (countAgedAlligator) {
			count++;
		}
		alligator.acceptOnChildren(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {
		if (countBoard) {
			count++;
		}
		board.acceptOnChildren(this);
	}

}
