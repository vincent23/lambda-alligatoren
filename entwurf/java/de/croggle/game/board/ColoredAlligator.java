package de.croggle.game.board;

import de.croggle.game.Color;
import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * Colored alligators represent lambda abstractions in the Lambda Calculus. The
 * color of the alligator is the variable, which is bound by the abstraction.
 * 
 * @composed 1 - 1-0 de.croggle.game.Color
 **/
public class ColoredAlligator extends Alligator implements ColoredBoardObject {
	private Color color;
	private boolean recolorable = true;
	
	/**
	 * Creates a new ColoredAlligator with the specified color.
	 * The color hereby serves as the name of variables bound by this abstraction in lambda calculus speech.
	 * The ColoredAlligator is created as a recolorable board object by this constructor.
	 * @param c the color this alligator has.
	 */
	public ColoredAlligator(Color c) {
		this.color = c;
		this.recolorable = true;
	}
	
	/**
	 * Creates a new ColoredAlligator with the specified color and the permission value if the object is recolorable or not.
	 * The color hereby serves as the name of variables bound by this abstraction in lambda calculus speech.
	 * @param c the color this alligator has.
	 * @param recolorable whether the ColoredAlligator is recolorable (true) or not (false)
	 */
	public ColoredAlligator(Color c, boolean recolorable) {
		this(c);
		this.recolorable = recolorable;
	}

	@Override
	public Parent getParent() {

		return null;
	}

	@Override
	public void accept(BoardObjectVisitor visitor) {
		
	}


	@Override
	public ColoredAlligator copy() {
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
