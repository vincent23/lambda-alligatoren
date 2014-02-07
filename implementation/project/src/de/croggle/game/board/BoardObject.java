package de.croggle.game.board;

import java.util.Map;

import de.croggle.game.Color;
import de.croggle.game.board.operations.BoardObjectVisitor;

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

	/**
	 * Tests two board objects for equality. For the implementation, the same
	 * rules apply as for the implementation of the @{link
	 * java.lang.Object#equals java equals} method.
	 * 
	 * @param b
	 *            the board object this object is compared to
	 * @return true if both board objects match/are equal, false otherwise
	 */
	boolean match(BoardObject b);

	boolean matchWithRecoloring(BoardObject b, Map<Color, Color> recoloring);
}
