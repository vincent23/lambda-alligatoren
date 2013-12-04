package de.croggle.game.model;

/**
 * 
 */
public interface BoardObject {
	void accept (BoardObjectVisitor visitor);
}
