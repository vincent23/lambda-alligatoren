package de.croggle.game.model;

import de.croggle.game.BoardObjectVisitor;

/**
 * 
 */
public interface BoardObject {
	void accept (BoardObjectVisitor visitor);
}
