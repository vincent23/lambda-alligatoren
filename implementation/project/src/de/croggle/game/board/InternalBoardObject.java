package de.croggle.game.board;

/**
 * Special type of BoardObject whose specific attribute is that its not the
 * uppermost BoardObject which means it must have a parent.
 */
public interface InternalBoardObject extends BoardObject {

	/**
	 * Returns the parent object of the internal board object, meaning the
	 * parent node in the tree structure.
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
	 * Returns whether the object can be moved by the user.
	 * 
	 * @return true if the object can be moved, otherwise false
	 */
	public boolean isMovable();

	/**
	 * Returns whether the object can be removed by the user.
	 * 
	 * @return true if the object can be removed, otherwise false
	 */
	public boolean isRemovable();

	public InternalBoardObject copy();
}
