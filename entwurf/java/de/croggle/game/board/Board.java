package de.croggle.game.board;

import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * Root object of every alligator term. This class acts as the root of the tree
 * structure used for modelling the lambda terms.
 */
public class Board extends Parent implements BoardObject {

	/**
	 * Creates a new board with no children.
	 */
	public Board() {

	}

	/**
	 * Accepts a visitor which is then used for traversing the object's subtree.
	 * 
	 * @param visitor
	 *            the visitor that tries to access the tree
	 */
	@Override
	public void accept(BoardObjectVisitor visitor) {

	}

	/**
	 * Creates and returns a deep copy of the board object.
	 * 
	 * @return the deep copy
	 */
	@Override
	public Board copy() {
		return null;
	}
}
