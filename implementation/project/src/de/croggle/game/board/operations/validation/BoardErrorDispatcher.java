package de.croggle.game.board.operations.validation;

/**
 * Interface for dispatchers of {@link BoardError}s. By calling
 * {@link BoardError}.haveDispatched(this), the respective BoardError will
 * automatically have itself dispatched by the matching dispatcher method.
 * 
 */
public interface BoardErrorDispatcher {

	/**
	 * Dispatches {@link ObjectUncoloredError}s.
	 * 
	 * @param err
	 *            the error to be dispatched
	 */
	void dispatch(ObjectUncoloredError err);

	/**
	 * Dispatches {@link ColoredAlligatorChildlessError}s.
	 * 
	 * @param err
	 *            the error to be dispatched
	 */
	void dispatch(ColoredAlligatorChildlessError err);

	/**
	 * Dispatches {@link AgedAlligatorChildlessError}s.
	 * 
	 * @param err
	 *            the error to be dispatched
	 */
	void dispatch(AgedAlligatorChildlessError err);

	/**
	 * Dispatches {@link EmptyBoardError}s.
	 * 
	 * @param err
	 *            the error to be dispatched
	 */
	void dispatch(EmptyBoardError err);
}
