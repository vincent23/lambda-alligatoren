package de.croggle.game.board;

import de.croggle.game.visitor.BoardObjectVisitor;

/**
 * 
 * Aged alligators represent mandatory braces within the Lambda Calculus. They
 * manipulate the order in which the elements are evaluated.
 */
public class AgedAlligator extends Alligator {

	/**
	 * Creates an aged alligator with no children and the given parent.
	 * 
	 * @param parent
	 *            the parent this alligator should have
	 */
	public AgedAlligator(Parent parent, boolean movable, boolean removable) {
		super(parent, movable, removable);
	}

	private AgedAlligator(AgedAlligator agedAlligator) {
		super(agedAlligator);
	}

	/**
	 * Accepts a visitor, which is then used for traversing the subtree of the
	 * object.
	 * 
	 * @param visitor
	 *            the visitor that tries to access the tree
	 */
	@Override
	public void accept(BoardObjectVisitor visitor) {
		visitor.visitAgedAlligator(this);
		//acceptOnChildren(visitor); // TODO I think, this is not expected behaviour
	}

	/**
	 * Creates and returns a deep copy of the board object.
	 * 
	 * @return the deep copy of this object
	 */
	@Override
	public AgedAlligator copy() {
		return new AgedAlligator(this);
	}
}
