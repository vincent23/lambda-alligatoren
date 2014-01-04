package de.croggle.game.board.operations.validation;

import de.croggle.game.board.BoardObject;

/**
 * The base interface of all BoardErrors.
 * 
 */
public interface BoardError {

	/**
	 * Get the cause of this BoardError, i.e. the BoardObject immediately
	 * responsible for the error to happen.
	 * 
	 * @return
	 */
	BoardObject getCause();

	/**
	 * Have this BoardError dispatched by the appropriate dispatcher method
	 * (visitor pattern).
	 * 
	 * @param dispatcher
	 *            the dispatcher to dispatch this error
	 */
	void haveDispatched(BoardErrorDispatcher dispatcher);
}
