package de.croggle.game.board.operations.validation;

import de.croggle.game.board.BoardObject;

public abstract class AbstractBoardError implements BoardError {

	private final BoardObject cause;

	public AbstractBoardError(BoardObject cause) {
		if (cause == null) {
			throw new NullPointerException(
					"Cannot instantiate a BoardError without a cause");
		}
		this.cause = cause;
	}

	@Override
	public BoardObject getCause() {
		return this.cause;
	}
}
