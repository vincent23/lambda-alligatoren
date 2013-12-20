package de.croggle.game.board;

import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * Root object of every alligator term. This class offers the root of the tree structur used ro model the lambdaterms in this project.
 **/
public class Board extends Parent implements BoardObject {

	@Override
	public void accept(BoardObjectVisitor visitor) {

	}

}
