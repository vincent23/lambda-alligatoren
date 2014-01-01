package de.croggle.game.board.operations;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

/**
 * A visitor for traversing trees of BoardObjects. It visits a node at first and
 * then each of it's children from left to right.
 */
public interface BoardObjectVisitor {
	/**
	 * Called when an egg is visited.
	 * 
	 * @param egg
	 *            the egg which is visited
	 */
	void visitEgg(Egg egg);

	/**
	 * Called when a colored alligator is visited.
	 * 
	 * @param alligator
	 *            the colored alligator which is visited
	 */
	void visitColoredAlligator(ColoredAlligator alligator);

	/**
	 * Called when an aged alligator is visited.
	 * 
	 * @param alligator
	 *            the aged alligator which is visited
	 */
	void visitAgedAlligator(AgedAlligator alligator);

	/**
	 * Called when the board is visited.
	 * 
	 * @param board
	 *            the board which is visited
	 */
	void visitBoard(Board board);
}
