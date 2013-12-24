package de.croggle.game.board;

import de.croggle.game.Color;
import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * An egg represents a variable within the Lambda Calculus. If the guarding
 * alligator eats something, the eaten thing will hatch from the egg.
 */
public class Egg implements InternalBoardObject, ColoredBoardObject {
	private Color color;
	private boolean recolorable;
	private boolean movable;
	private boolean removable;
	private Parent parent;

	/**
	 * Creates a new egg with the specified color and the permission value if
	 * the object is recolorable or not. The color hereby serves as the name of
	 * the variable this egg represents in the Lambda Calculus.
	 * 
	 * @param c
	 *            the color this egg has
	 * @param recolorable
	 *            whether the egg is recolorable (true) or not (false)
	 */
	public Egg(Parent parent, boolean movable, boolean removable, Color c,
			boolean recolorable) {
		this.parent = parent;
		this.movable = movable;
		this.removable = removable;
		this.color = c;
		this.recolorable = recolorable;
	}

	/**
	 * Accepts a visitor, which is then used for traversing the subtree of the
	 * object.
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
		this.parent = parent;
	}

	/**
	 * Creates and returns a deep copy of the board object.
	 * 
	 * @return the deep copy
	 */
	@Override
	public Egg copy() {
		return new Egg(parent, movable, removable, color, recolorable);
	}

	/**
	 * Returns whether the color of the egg can be changed by the user.
	 * 
	 * @return true if the object can be recolored, otherwise false
	 */
	@Override
	public boolean isRecolorable() {
		return recolorable;
	}

	/**
	 * Returns the color that represents a variable's name on the board.
	 * 
	 * @return the current color of the egg
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the egg. In placement mode: Set only if it is marked as
	 * recolorable.
	 * 
	 * @param c
	 *            the new color for the egg
	 */
	@Override
	public void setColor(Color c) {
		this.color = c;
	}

	/**
	 * Returns whether the egg can be moved by the user.
	 * 
	 * @return true if the egg can be moved, otherwise false
	 */
	@Override
	public boolean isMovable() {
		return movable;
	}

	/**
	 * Returns whether the egg can be removed by the user.
	 * 
	 * @return true if the object can be removed, otherwise false
	 */
	@Override
	public boolean isRemovable() {
		return removable;
	}
}
