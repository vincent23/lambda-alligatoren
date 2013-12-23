package de.croggle.game.board;

import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * An interface for any object, which resides on the board.
 */
public interface BoardObject {
	/**
	 * Accepts a visitor, which is then used to traverse the object's subtree.
	 * 
	 * @param visitor
	 *            the visitor that tries to access the tree
	 */
	void accept(BoardObjectVisitor visitor);

	/**
	 * Creates and returns a deep copy of the board object. This means that also
	 * all children must be deep copies and no reference may be used twice.
	 * 
	 * @return the deep copy
	 */
	BoardObject copy();
}
