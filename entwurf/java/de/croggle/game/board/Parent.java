package de.croggle.game.board;

import java.util.Iterator;
import java.util.List;

/**
 * Parent is an abstract class to model the functions board objects, which can
 * be parents of families, must have.
 * 
 * @has 1 - * InternalBoardObject
 **/

public abstract class Parent {

	/**
	 * Superconstrucor of all parents. Creates a parent with no children.
	 */
	public Parent() {

	}

	private List<InternalBoardObject> children;

	/**
	 * Adds a child to the family of the parent.
	 * 
	 * @param child
	 *            the child, which is added to the family of the parent
	 */
	public void addChild(InternalBoardObject child) {
	}

	/**
	 * Removes a child from the family of the parent.
	 * 
	 * @param child
	 *            the child, which is removed from the family of the parent
	 * @return whether the removal was successful
	 */
	public boolean removeChild(InternalBoardObject child) {
		return false;
	}

	/**
	 * Replaces the child object with the replaceChild object. If the given
	 * child is not found, nothing gets replaced and false is returned.
	 * 
	 * @param child
	 *            the child to replace
	 * @param replaceChild
	 *            the child that replaces the current child
	 * @return true on replace success, false otherwise
	 */
	public boolean replaceChildWith(InternalBoardObject child,
			InternalBoardObject replaceChild) {
		return false;
	}

	/**
	 * Returns an Iterator for the children list.
	 * 
	 * @return the Iterator
	 */
	public Iterator<InternalBoardObject> getIterator() {
		return null;
	}

	/**
	 * Checks whether the given InternalBoardObject is the last/rightmost child
	 * in the list of the parent.
	 * 
	 * @param child
	 *            the child is the InternalBoardObject which should be checked
	 *            on
	 * @return returns true if the given child is the last child in the list
	 *         false otherwise
	 */
	public boolean isLastChild(InternalBoardObject child) {
		return false;
	}

	/**
	 * Returns the following child after the one given as parameter.
	 * 
	 * @param child
	 *            the child is an InternalBoardObject in the children list
	 * @return the child which is the next after the child in the list
	 */
	public InternalBoardObject getNextChild(InternalBoardObject child) {
		return null;
	}
}
