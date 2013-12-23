package de.croggle.game.board;

import java.util.Iterator;
import java.util.List;

/**
 * Parent is an abstract class to model the functions board objects - which can
 * be parents of families - must have.
 * 
 * @has 1 - * InternalBoardObject
 **/

public abstract class Parent {

	private List<InternalBoardObject> children;

	/**
	 * Superconstructor of all parents. Creates a parent with no children.
	 */
	protected Parent() {
	}

	/**
	 * Adds a child to the family of the parent.
	 * 
	 * @param child
	 *            the child which should be added to the family of the parent
	 */
	public void addChild(InternalBoardObject child) {
	}

	/**
	 * Removes a child from the family of the parent.
	 * 
	 * @param child
	 *            the child which should be removed from the family of the
	 *            parent
	 * @return whether the removal was successful
	 */
	public boolean removeChild(InternalBoardObject child) {
		return false;
	}

	/**
	 * Replaces a child object with another one. If the given child is not
	 * found, nothing is replaced and false is returned.
	 * 
	 * @param child
	 *            the child to replace
	 * @param replaceChild
	 *            the child that replaces the current child
	 * @return true on success, false otherwise
	 */
	public boolean replaceChildWith(InternalBoardObject child,
			InternalBoardObject replaceChild) {
		return false;
	}

	/**
	 * Returns an iterator for the children list.
	 * 
	 * @return the iterator
	 */
	public Iterator<InternalBoardObject> getIterator() {
		return null;
	}

	/**
	 * Checks whether the given InternalBoardObject is the last/rightmost child
	 * in the list of the parent.
	 * 
	 * @param child
	 *            the child which should be checked on
	 * @return returns true if the given child is the last child in the list,
	 *         false otherwise
	 */
	public boolean isLastChild(InternalBoardObject child) {
		return false;
	}

	/**
	 * Returns the child next to (to the right of) the one given as a parameter.
	 * 
	 * @param child
	 *            the child whose successor should be returned
	 * @return the child which is the next one in the list after the given child
	 */
	public InternalBoardObject getNextChild(InternalBoardObject child) {
		return null;
	}
}
