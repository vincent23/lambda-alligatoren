package de.croggle.game.board.operations.validation;

import de.croggle.game.board.ColoredAlligator;

/**
 * Error that occurs if a {@link ColoredAlligator} has no children.
 * 
 * 
 */
public class ColoredAlligatorChildlessError extends AbstractBoardError {

	public ColoredAlligatorChildlessError(ColoredAlligator cause) {
		super(cause);
	}

	@Override
	public void haveDispatched(BoardErrorDispatcher dispatcher) {
		dispatcher.dispatch(this);
	}

}
