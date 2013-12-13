package de.croggle.model;

import de.croggle.model.visitor.BoardObjectVisitor;

/**
 * An interface for any object, which resides on the board.
 */
public interface BoardObject {
    /**
     * Accepts a visitor, which is then used to traverse the object's subtree.
     */
	void accept(BoardObjectVisitor visitor);
}
