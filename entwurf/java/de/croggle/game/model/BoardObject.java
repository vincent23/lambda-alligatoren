package de.croggle.game.model;

import de.croggle.game.BoardObjectVisitor;

/**
 * an interface for any object, which resides on the board.
 */
public interface BoardObject {
    /**
     * accept-method according to the "Visitor-Pattern".
     */
	void accept (BoardObjectVisitor visitor);
}
