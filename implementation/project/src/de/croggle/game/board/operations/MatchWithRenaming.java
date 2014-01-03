package de.croggle.game.board.operations;

import de.croggle.game.board.BoardObject;

/**
 * A board operation that offers functionality to compare alligator expressions
 * regardless of the concrete color/variable name mapping. That means, that for
 * two alligator expressions to match it is only necessary that a bijective
 * transfer function between the color names of expression a and the color names
 * of expression b exists (alpha equivalence).
 * 
 */
public class MatchWithRenaming {
	private MatchWithRenaming() {

	}

	/**
	 * Performs a test for alpha equivalence (see class description) between two
	 * alligator expressions.
	 * 
	 * @param a the first expression to be compared
	 * @param b the second expression to be compared
	 * @return true, if the two given expressions are alpha-equivalent, false otherwise
	 */
	public static boolean match(BoardObject a, BoardObject b) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
