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
	 * Creates a new ColoredAlligator with the specified color. The color hereby
	 * serves as the name of variables bound by this abstraction in lambda
	 * calculus speech. The ColoredAlligator is created as a recolorable board
	 * object by this constructor.
	 * 
	 * @param c
	 *            the color this alligator has.
	 */
	public ColoredAlligator(Color c) {
		this.color = c;
		this.recolorable = true;
	}

	/**
	 * Creates a new ColoredAlligator with the specified color and the
	 * permission value if the object is recolorable or not. The color hereby
	 * serves as the name of variables bound by this abstraction in lambda
	 * calculus speech.
	 * 
	 * @param c
	 *            the color this alligator has.
	 * @param recolorable
	 *            whether the ColoredAlligator is recolorable (true) or not
	 *            (false)
	 */
	public ColoredAlligator(Color c, boolean recolorable) {
		this(c);
		this.recolorable = recolorable;
	}

	/**
	 * Returns the parent object of this alligator.
	 * 
	 * @return the parent alligator
	 */
	@Override
	public Parent getParent() {
		return null;
	}

	/**
	 * Sets the given parent as the parent object.
	 * 
	 * @param parent
	 *            the new parent of this object
	 */
	@Override
	public void setParent(Parent parent) {
	}

	/**
	 * Accepts a visitor, which is then used to traverse the object's subtree.
	 * 
	 * @param visitor
	 *            the visitor that tries to access the tree
	 */
	@Override
	public void accept(BoardObjectVisitor visitor) {

	}

	/**
	 * Creates and returns a deep copy of the board object. This means that also
	 * all children must be deep copies and no reference may be used twice.
	 * 
	 * @return the deep copy
	 */
	@Override
	public ColoredAlligator copy() {
		return null;
	}

	/**
	 * Gets whether the object is protected from user interactions involving
	 * colors or not.
	 * 
	 * @return true if the object can be recolored, otherwise false
	 */
	@Override
	public boolean isRecolorable() {
		return false;
	}

	/**
	 * Returns the color that represents a variable's name on the board.
	 * 
	 * @return the current color of the BoardObject
	 */
	@Override
	public Color getColor() {
		return this.color;
	}

	/**
	 * Sets the color of the BoardObject, in placement mode only if it is marked
	 * as recolorable.
	 * 
	 * @param c
	 *            the new color the caller wants the BoardObject to have
	 */
	@Override
	public void setColor(Color c) {
	}

	/**
	 * Gets whether the object is protected from the user moving it or not.
	 * 
	 * @return true if the object can be moved, otherwise false
	 */
	@Override
	public boolean isMovable() {
		return false;
	}

	/**
	 * Gets whether the object is protected being removed from the board or not.
	 * 
	 * @return true if the object can be removed, otherwise false
	 */
	@Override
	public boolean isRemovable() {
		return false;
	}
}
