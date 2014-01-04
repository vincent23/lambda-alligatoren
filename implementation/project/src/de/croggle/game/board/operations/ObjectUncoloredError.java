package de.croggle.game.board.operations;

import de.croggle.game.Color;
import de.croggle.game.board.BoardObject;

/**
 * Error that occurs if a {@link BoardObject} in an expression has no valid
 * {@link Color} set.
 * 
 */
public class ObjectUncoloredError extends AbstractBoardError {

	public ObjectUncoloredError(BoardObject cause) {
		super(cause);
	}

	@Override
	public void haveDispatched(BoardErrorDispatcher dispatcher) {
		dispatcher.dispatch(this);
	}

}
