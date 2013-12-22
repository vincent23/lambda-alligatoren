package de.croggle.game.board;

import de.croggle.game.Color;
import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * An egg represents a variable within the Lambda Calculus. If the guarding
 * alligator eats something, the eaten thing will hatch from the egg.
 * 
 * @composed 1 - 1-0 de.croggle.game.Color
 */
public class Egg implements InternalBoardObject, ColoredBoardObject {
	private Color color;
	private boolean recolorable = true;
	
	/**
	 * Creates a new egg with the specified color.
	 * The color hereby serves as the name of the variable this egg represents, in lambda calculus speech.
	 * The egg is created as a recolorable board object by this constructor.
	 * @param c the color this egg has.
	 */
	public Egg(Color c) {
		this.color = c;
		this.recolorable = true;
	}
	
	/**
	 * Creates a new egg with the specified color and the permission value if the object is recolorable or not.
	 * The color hereby serves as the name of the variable this egg represents, in lambda calculus speech.
	 * @param c the color this egg has.
	 * @param recolorable whether the egg is recolorable (true) or not (false)
	 */
	public Egg(Color c, boolean recolorable) {
		this(c);
		this.recolorable = recolorable;
	}
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

	@Override
	public boolean isRecolorable() {
		// TODO
	}
	
	 @Override
	 public Color getColor() {
		 return this.color;
	 }

	 @Override
	public void setColor(Color c) throws RecolorNotAllowedException {
		// TODO
	}
}
