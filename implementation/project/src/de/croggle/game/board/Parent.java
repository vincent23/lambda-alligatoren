package de.croggle.game.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.croggle.game.visitor.BoardObjectVisitor;

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

	@SuppressWarnings("LeakingThisInConstructor")
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
	public final void addChild(InternalBoardObject child) {
		children.add(child);
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
	public boolean replaceChildWith(InternalBoardObject child,
			InternalBoardObject replaceChild) {
		final int location = children.indexOf(child);
		if (location == -1) {
			return false;
		} else {
			children.set(location, replaceChild);
			return true;
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
		if (location > 0 && location + 1 < children.size()) {
			return children.get(location + 1);
		} else {
			return null;
		}
	}

	protected void acceptOnChildren(BoardObjectVisitor visitor) {
		for (InternalBoardObject child : this) {
			child.accept(visitor);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o.getClass() != Parent.class)
			return false;
		
		Parent oParent = (Parent) o;
		if (this.children.size() != oParent.children.size())
			return false;
		
		boolean equal = true;
		for (int i = 0; i < this.children.size(); i++) {
			equal &= this.children.get(i).equals(oParent.children.get(i));
		}
		return equal;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + this.children.hashCode();
		return hash;
	}
}
