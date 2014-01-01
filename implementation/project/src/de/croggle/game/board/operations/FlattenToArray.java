package de.croggle.game.board.operations;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

/**
 * A visitor to flatten the syntax tree. Use the method "flatten" to receive a
 * "flat" array of BoardObjects. Useful if sequentially iterating through all
 * objects in the tree is needed to be achieved.
 */
public class FlattenToArray implements BoardObjectVisitor {
	private FlattenToArray() {
	}

	/**
	 * Systematically travels the tree and adds each element part of it <b>one
	 * single time</b> to the array that is returned. Useful if sequentially
	 * iterating through all objects in the tree is needed to be achieved.
	 * 
	 * @param tree
	 *            The alligator tree to be flattened.
	 * @return A list with all elements in the given tree.
	 */
	public BoardObject[] flatten(BoardObject tree) {
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
