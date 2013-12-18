package de.croggle.game.board;

import de.croggle.game.Color;
import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * Colored alligators represent lambda abstractions in the Lambda Calculus. The
 * color of the alligator is the variable, which is bound by the abstraction.
 * 
 * @composed 1 - 1-0 de.croggle.game.Color
 **/
public class ColoredAlligator extends Alligator {
	private Color color;

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
