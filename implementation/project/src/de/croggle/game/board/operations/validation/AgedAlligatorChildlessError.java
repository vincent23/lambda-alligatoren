package de.croggle.game.board.operations.validation;

import de.croggle.game.board.AgedAlligator;

/**
 * Error that occurs if an {@link AgedAlligator} has no children.
 * 
 * 
 */
public class AgedAlligatorChildlessError extends AbstractBoardError {

	public AgedAlligatorChildlessError(AgedAlligator cause) {
		super(cause);
	}

	@Override
	public void haveDispatched(BoardErrorDispatcher dispatcher) {
		dispatcher.dispatch(this);
	}

}
