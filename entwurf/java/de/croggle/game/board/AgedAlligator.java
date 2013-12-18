package de.croggle.game.board;

import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * 
 * Aged alligators represent mandatory braces within the Lambda Calculus. They
 * manipulate the order in which the elements are evaluated.
 * 
 * @has 1 - * BoardObject
 */
public class AgedAlligator extends Alligator {

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(BoardObjectVisitor visitor) {
		// TODO Auto-generated method stub

	}

}
