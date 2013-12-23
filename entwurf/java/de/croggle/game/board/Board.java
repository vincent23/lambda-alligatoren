package de.croggle.game.board;

import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * Root object of every alligator term. This class offers the root of the tree
 * structure used to model the lambda terms in this project.
 */
public class Board extends Parent implements BoardObject {

	/**
	 * Creates a new board with no children.
	 */
	public Board() {

	}

	/**
	 * Accepts a visitor, which is then used to traverse the object's subtree.
	 * 
	 * @param visitor
	 *            the visitor that tries to access the tree
	 */
	@Override
	public void accept(BoardObjectVisitor visitor) {

	}

	/**
	 * Creates and returns a deep copy of the board object. This means that also
	 * all children must be deep copies and no reference may be used twice.
	 * 
	 * @return the deep copy
	 */
	@Override
	public Board copy() {
		return null;
	}
}
