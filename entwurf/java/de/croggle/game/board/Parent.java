package de.croggle.game.board;

import java.util.List;
import java.util.Iterator;


/**
 * Parent is an abstract class to model the functions board objects, which can be parents of families, must have.
 * @has 1 - * InternalBoardObject
**/

public abstract class Parent {

	private List<InternalBoardObject> children;

	/**
	 * Adds a child to the familiy of the parent.
	 * @param child is the InternalBoardObject, which is added to the family of the parent.
	 */
	public void addChild(InternalBoardObject child) {
	}

	/**
	 * Removes a child from the family of the parent.
	 * @param child is the InternalBoardObject, which is removed from the family of the parent.
	 * @return whether the removement was successfull.
	 */
	public boolean removeChild(InternalBoardObject child) {
		return false;
	}

	/**
	 * Returns an Iterator on the children list.
	 * @return the Iterator.
	 */
	public Iterator<InternalBoardObject> getIterator() {
		return null;
	}

	/**
	 * Checks whether the given InternalBoardObject is the last child in the list of the parent.
	 * @param child InternalChildObject which should be checked on.
	 * @return returns true if the given child is the last child in the list false otherwise.
	 */
	public boolean isLastChild(InternalBoardObject child) {
	}

	/**
	 * Returns the following child after the one given as parameter.
	 * @param child in the children list.
	 * @return the child which is the next after the child in the list.
	 */
	public InternalBoardObject getNextChild(InternalBoardObject child) {
	}
}
