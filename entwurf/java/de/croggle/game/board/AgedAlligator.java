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

	/**
	 * Creates an aged alligator with no child objects and no parent.
	 */
	public AgedAlligator() {

	}

	/**
	 * Creates an aged alligator with no children and the given parent.
	 * 
	 * @param parent
	 *            the parent this alligator should have
	 */
	public AgedAlligator(Parent parent) {

	}

	/**
	 * Returns the parent object of the internal board object, means the parent
	 * knot in the tree structure.
	 * 
	 * @return the parent object
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
	 * @return the deep copy of this object
	 */
	@Override
	public AgedAlligator copy() {
		return null;
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
