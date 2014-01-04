package de.croggle.game.board.operations.validation;

import de.croggle.game.Color;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.InternalBoardObject;

/**
 * Error that occurs if a {@link BoardObject} in an expression has no valid
 * {@link Color} set.
 * 
 */
public class ObjectUncoloredError extends AbstractBoardError {

	/**
	 * Creates a new ObjectUncoloredError with the given
	 * {@link InternalBoardObject} as cause.
	 * 
	 * TODO Actually, passing a {@link ColoredBoardObject} here would be
	 * preferable, but as of now, ColoredBoardObjects do not extend BoardObject
	 * and thus cannot be used to call the BoardError's super constructor.
	 * 
	 * @param cause
	 *            the BoardObject that has no color responsible for this error.
	 */
	public ObjectUncoloredError(InternalBoardObject cause) {
		super(cause);
	}

	@Override
	public void haveDispatched(BoardErrorDispatcher dispatcher) {
		dispatcher.dispatch(this);
	}

}
