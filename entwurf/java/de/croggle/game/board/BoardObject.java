package de.croggle.game.board;

import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * An interface for any object, which resides on the board.
 */
public interface BoardObject {
    /**
     * Accepts a visitor, which is then used to traverse the object's subtree.
     */
	void accept(BoardObjectVisitor visitor);
}
