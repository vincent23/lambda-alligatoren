package de.croggle.game.board.operations.validation;

import de.croggle.game.board.Board;

public class EmptyBoardError extends AbstractBoardError {

	public EmptyBoardError(Board cause) {
		super(cause);
	}

	@Override
	public void haveDispatched(BoardErrorDispatcher dispatcher) {
		dispatcher.dispatch(this);
	}

}
