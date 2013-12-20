package de.croggle.game.board;

import de.croggle.game.Color;
import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * An egg represents a variable within the Lambda Calculus. If the guarding
 * alligator eats something, the eaten thing will hatch from the egg.
 * 
 * @composed 1 - 1-0 de.croggle.game.Color
 */
public class Egg implements InternalBoardObject {
	private Color color;

	@Override
	public void accept(BoardObjectVisitor visitor) {

	}

	@Override
	public Parent getParent() {
		return null;
	}


	@Override
	public Egg copy() {
	}

	/**
	 * Gets the color oft the egg.
	 * @return the color.
	 */
	public Color getColor(){
		return null;

	}
}
