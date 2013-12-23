package de.croggle.game.board;

/**
 * Special type of BoardObject, whose specific attribute is, that its not the
 * uppermost BoardObject, which means it must have a parent.
 */
public interface InternalBoardObject extends BoardObject {

	/**
	 * Returns the parent object of the internal board object, means the parent
	 * knot in the tree structure.
	 * 
	 * @return the parent object
	 */
	public Parent getParent();

	/**
	 * Sets the given parent as the parent object.
	 * 
	 * @param parent
	 *            the new parent of this object
	 */
	public void setParent(Parent parent);

	/**
	 * Gets whether the object is protected from the user moving it or not.
	 * 
	 * @return true if the object can be moved, otherwise false
	 */
	public boolean isMovable();

	/**
	 * Gets whether the object is protected being removed from the board or not.
	 * 
	 * @return true if the object can be removed, otherwise false
	 */
	public boolean isRemovable();
}
