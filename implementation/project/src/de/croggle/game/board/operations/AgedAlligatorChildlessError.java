package de.croggle.game.board.operations;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.BoardObject;

/**
 * Error that occurs if an {@link AgedAlligator} has no children.
 * 
 *
 */
public class AgedAlligatorChildlessError extends AbstractBoardError {
	
	public AgedAlligatorChildlessError(BoardObject cause) {
		super(cause);
	}

	@Override
	public void haveDispatched(BoardErrorDispatcher dispatcher) {
		dispatcher.dispatch(this);
	}

}
