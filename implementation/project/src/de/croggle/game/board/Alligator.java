package de.croggle.game.board;

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
	private boolean movable;
	private boolean removable;

	/**
	 * Superconstructor for all alligators.
	 * 
	 * @param parent
	 *            the parent of the alligator
	 * @param movable
	 *            whether the board object is movable or not
	 * @param removable
	 *            whether the board object is removable or not
	 */
	protected Alligator(Parent parent, boolean movable, boolean removable) {
		this.parent = parent;
		this.movable = movable;
		this.removable = removable;
	}

	/**
	 * Initializes an alligator without a parent and no children.
	 * 
	 * @param movable
	 *            whether the board object is movable or not
	 * @param removable
	 *            whether the board object is removable or not
	 */
	protected Alligator(boolean movable, boolean removable) {
		this.parent = null;
		this.movable = movable;
		this.removable = removable;
	}

	/**
	 * Copy constructor for alligators.
	 * 
	 * @param alligator
	 *            the alligator to be copied.
	 */
	protected Alligator(Alligator alligator) {
		super(alligator);
		this.parent = alligator.parent;
		this.movable = alligator.movable;
		this.removable = alligator.removable;
	}

	/**
	 * Returns the parent object of this alligator. TODO maybe throw
	 * null-pointer exception here? Accessing parents where there is no parent
	 * seems undesirable.
	 * 
	 * @return the parent alligator
	 */
	@Override
	public Parent getParent() {
		return parent;
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

	public boolean isMovable() {
		return movable;
	}

	public boolean isRemovable() {
		return removable;
	}
}