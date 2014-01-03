package de.croggle.game.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.croggle.game.board.operations.BoardObjectVisitor;

/**
 * Parent is an abstract class to model the functions board objects - which can
 * be parents of families - must have.
 **/

public abstract class Parent implements Iterable<InternalBoardObject> {

	private List<InternalBoardObject> children;

	/**
	 * Superconstructor of all parents. Creates a parent with no children.
	 */
	protected Parent() {
		children = new ArrayList<InternalBoardObject>();
	}

	protected Parent(Parent parent) {
		this();
		for (InternalBoardObject child : parent) {
			InternalBoardObject copiedChild = child.copy();
			copiedChild.setParent(this);
			addChild(copiedChild);
		}
	}

	/**
	 * Adds a child to the family of the parent.
	 * 
	 * @param child
	 *            the child which should be added to the family of the parent
	 */
	public final boolean addChild(InternalBoardObject child) {
		child.setParent(this);
		if (!children.contains(child)) {
			children.add(child);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Inserts a given child to the list of children of this parent. The child
	 * is inserted before the element currently at the given position.
	 * 
	 * @param child
	 *            the child to be inserted
	 * @param pos
	 *            the position, where the child is to be inserted
	 */
	public boolean insertChild(InternalBoardObject child, int pos) {
		child.setParent(this);
		if (!children.contains(child)) {
			this.children.add(pos, child);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Finds and returns the position of a given child in the list of children
	 * this parent has.
	 * 
	 * 
	 * @param child
	 *            the child whose position is to be obtained
	 * @return the position of child
	 */
	public int getChildPosition(InternalBoardObject child) {
		return this.children.indexOf(child);
	}

	/**
	 * Returns the first child of this parent.
	 * 
	 * @return the first child of this parent
	 * @throws NoSuchChildException
	 *             if this parent has no children
	 */
	public InternalBoardObject getFirstChild() {
		if (this.children.isEmpty()) {
			throw new NoSuchChildException();
		}

		return this.children.get(0);
	}

	/**
	 * Returns the number of children this parent has.
	 * 
	 * @return the number of children this parent has
	 */
	public int getChildCount() {
		return this.children.size();
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
		return children.remove(child);
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
	public boolean replaceChild(InternalBoardObject child,
			InternalBoardObject replaceChild) {
		final int location = children.indexOf(child);
		if (location == -1) {
			return false;
		} else {
			replaceChild.setParent(this);
			if (!children.contains(replaceChild)) {
				children.set(location, replaceChild);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Returns an iterator for the children list.
	 * 
	 * @return the iterator
	 */
	public Iterator<InternalBoardObject> iterator() {
		return children.iterator();
	}

	/**
	 * Returns an iterator initially pointing to the element at the given
	 * position
	 * 
	 * @param begin
	 *            the position at which to start iterating
	 * @return the iterator
	 */
	public Iterator<InternalBoardObject> iterator(int begin) {
		return children.listIterator(begin);
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
		return getNextChild(child) == null;
	}

	/**
	 * Returns the child next to (to the right of) the one given as a parameter.
	 * 
	 * @param child
	 *            the child whose successor should be returned
	 * @return the child which is the next one in the list after the given
	 *         child, null if it is the last child
	 */
	public InternalBoardObject getNextChild(InternalBoardObject child) {
		final int location = children.indexOf(child);
		if (location >= 0 && location + 1 < children.size()) {
			return children.get(location + 1);
		} else {
			return null;
		}
	}

	public void acceptOnChildren(BoardObjectVisitor visitor) {
		for (InternalBoardObject child : this) {
			child.accept(visitor);
		}
	}

	public boolean match(BoardObject o) {
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;

		Parent oParent = (Parent) o;
		if (this.children.size() != oParent.children.size())
			return false;

		boolean equal = true;
		for (int i = 0; i < this.children.size(); i++) {
			equal &= this.children.get(i).match(oParent.children.get(i));
		}
		return equal;
	}
}
