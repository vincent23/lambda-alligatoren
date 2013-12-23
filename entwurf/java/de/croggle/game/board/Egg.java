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
	private boolean recolorable;
	private Parent parent;

	/**
	 * Creates a new egg with the specified color. The color hereby serves as
	 * the name of the variable this egg represents, in lambda calculus speech.
	 * The egg is created as a recolorable board object by this constructor.
	 * 
	 * @param c
	 *            the color this egg has.
	 */
	public Egg(Color c) {
		this.color = c;
		this.recolorable = true;
	}

	/**
	 * Creates a new egg with the specified color and the permission value if
	 * the object is recolorable or not. The color hereby serves as the name of
	 * the variable this egg represents, in lambda calculus speech.
	 * 
	 * @param c
	 *            the color this egg has.
	 * @param recolorable
	 *            whether the egg is recolorable (true) or not (false)
	 */
	public Egg(Color c, boolean recolorable) {
		this(c);
		this.recolorable = recolorable;
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
	 * Returns the parent object of this egg.
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
	 * Creates and returns a deep copy of the board object. This means that no
	 * reference may be used twice.
	 * 
	 * @return the deep copy
	 */
	@Override
	public Egg copy() {
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
