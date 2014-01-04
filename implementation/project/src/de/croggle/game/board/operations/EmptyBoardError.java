package de.croggle.game.board.operations;

import de.croggle.game.board.BoardObject;

public class EmptyBoardError extends AbstractBoardError {

	public EmptyBoardError(BoardObject cause) {
		super(cause);
	}

	@Override
	public void haveDispatched(BoardErrorDispatcher dispatcher) {
		dispatcher.dispatch(this);
	}

}
