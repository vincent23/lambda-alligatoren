package de.croggle.game.board;

import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * Alligator is the abstract super class of aged and colored alligators. Both
 * have their similar rendering in common (which implies a similar type) and
 * they share the aspect of being parents.
 * 
 * E.g. for the statistics about how many alligators have been transformed, both
 * aged and colored alligators should count and thus need to be assignable to
 * one class of references.
 **/
public abstract class Alligator extends Parent implements InternalBoardObject {

	private Parent parent;

	/**
	 * Superconstructor for all alligators.
	 */
	protected Alligator() {
	}

	/**
	 * Accepts a visitor which is then used for traversing the subtree of the
	 * object.
	 * 
	 * @param visitor
	 *            the visitor that tries to access the tree
	 */
	@Override
	public abstract void accept(BoardObjectVisitor visitor);

	/**
	 * Returns the parent object of this alligator.
	 * 
	 * @return the parent alligator
	 */
	@Override
	public abstract Parent getParent();

	/**
	 * Sets the given parent as the parent object.
	 * 
	 * @param parent
	 *            the new parent of this object
	 */
	@Override
	public void setParent(Parent parent) {
	}
}
