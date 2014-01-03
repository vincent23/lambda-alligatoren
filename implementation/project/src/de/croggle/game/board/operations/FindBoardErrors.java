package de.croggle.game.board.operations;

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

	private FindBoardErrors() {

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
