package de.croggle.game.board.operations;

import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;

/**
 * Error that occurs if a {@link ColoredAlligator} has no children.
 * 
 *
 */
public class ColoredAlligatorChildlessError extends AbstractBoardError {

	public ColoredAlligatorChildlessError(BoardObject cause) {
		super(cause);
	}

	@Override
	public void haveDispatched(BoardErrorDispatcher dispatcher) {
		dispatcher.dispatch(this);
	}

}