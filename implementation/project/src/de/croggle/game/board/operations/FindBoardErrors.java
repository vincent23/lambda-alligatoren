package de.croggle.game.board.operations;

import java.util.ArrayList;
import java.util.List;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

/**
 * A board operation to find all error occurrences in a board.
 * 
 */
public class FindBoardErrors implements BoardObjectVisitor {

	private boolean objectsUncolored = false;
	private boolean agedAlligatorChildless = false;
	private boolean coloredAlligatorChildless = false;
	private boolean boardEmpty = false;

	private List<BoardError> errors;

	/**
	 * Initializes a {@link FindBoardErrors} instance by configuring it so it
	 * will only look for {@link ObjectUncoloredError}s and
	 * {@link ColoredAlligatorChildlessError}s.
	 */
	private FindBoardErrors() {
		this(new BoardErrorType[] { BoardErrorType.COLOREDALLIGATOR_CHILDLESS,
				BoardErrorType.UNCOLORED_OBJECT });
	}

	/**
	 * 
	 */
	private FindBoardErrors(BoardErrorType[] errorTypes) {
		this.errors = new ArrayList<BoardError>();
		this.applyErrorTypeSettings(errorTypes);
	}

	/**
	 * Configures this {@link FindBoardErrors} to look for all
	 * {@link BoardErrorType}s specified in the given array.
	 * 
	 * @param errorTypes
	 *            the error types to be looked for
	 */
	private final void applyErrorTypeSettings(BoardErrorType[] errorTypes) {
		objectsUncolored = false;
		agedAlligatorChildless = false;
		coloredAlligatorChildless = false;
		boardEmpty = false;
		for (BoardErrorType t : errorTypes) {
			switch (t) {
			case AGEDALLIGATOR_CHILDLESS: {
				this.agedAlligatorChildless = true;
				break;
			}
			case COLOREDALLIGATOR_CHILDLESS: {
				this.coloredAlligatorChildless = true;
				break;
			}
			case UNCOLORED_OBJECT: {
				this.objectsUncolored = true;
				break;
			}
			case EMPTY_BOARD: {
				this.boardEmpty = true;
				break;
			}
			default: {
				throw new IllegalStateException("This should never happen");
			}
			}
		}
	}

	/**
	 * Returns a list of errors found in the given alligator expression.
	 * 
	 * @param b
	 *            the {@link BoardObject} to find errors in
	 * @return a list of all errors found in the given expression
	 */
	public static List<BoardError> find(BoardObject b) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	/**
	 * Returns a list of errors found in the given alligator expression that are
	 * of the types specified in the given <code>errorTypes</code> array.
	 * 
	 * @param b
	 *            the {@link BoardObject} to find errors in
	 * @param errorTypes
	 *            the {@link BoardErrorType}s to be looked for.
	 * @return a list of all errors of the specified types found in the given
	 *         expression
	 */
	public static List<BoardError> find(BoardObject b,
			BoardErrorType[] errorTypes) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void visitEgg(Egg egg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitBoard(Board board) {
		// TODO Auto-generated method stub

	}
}
