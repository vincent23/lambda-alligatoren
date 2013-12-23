package de.croggle.game.board;

import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * An interface for any object which resides on the board.
 */
public interface BoardObject {
	/**
	 * Accepts a visitor which is then used for traversing the object's subtree.
	 * 
	 * @param visitor
	 *            the visitor that tries to access the tree
	 */
	void accept(BoardObjectVisitor visitor);

	/**
	 * Creates and returns a deep copy of the board object.
	 * 
	 * @return the deep copy
	 */
	BoardObject copy();
}
