package de.croggle.game.board;

import de.croggle.game.visitor.BoardObjectVisitor;
/**
 * Alligator is the abstract super class of aged and colored alligators.
 * Both have their similar rendering in common (which implies a similar
 * type) and they share the aspect of being parents.
 * 
 * E.g. for the statistics about how many alligators have been transformed
 * both aged and colored alligators should count and thus need to be
 * assignable to one class of references.
 **/
public abstract class Alligator extends Parent implements InternalBoardObject {

	@Override
	public abstract void accept(BoardObjectVisitor visitor){

	}

	@Override
	public abstract Parent getParent() {

	}
}
