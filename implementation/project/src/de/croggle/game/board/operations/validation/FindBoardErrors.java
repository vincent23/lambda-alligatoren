package de.croggle.game.board.operations.validation;

import java.util.ArrayList;
import java.util.List;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.operations.BoardObjectVisitor;

/**
 * A board operation to find all error occurrences in a board.
 * 
 */
public class FindBoardErrors extends AbstractBoardValidator implements
		BoardObjectVisitor {

	private List<BoardError> errors;

	/**
	 * Initializes a {@link FindBoardErrors} instance by configuring it so it
	 * will only look for {@link ObjectUncoloredError}s and
	 * {@link ColoredAlligatorChildlessError}s.
	 */
	private FindBoardErrors() {
		this(new BoardErrorType[] { BoardErrorType.COLOREDALLIGATOR_CHILDLESS,
				BoardErrorType.OBJECT_UNCOLORED });
	}

	/**
	 * 
	 */
	private FindBoardErrors(BoardErrorType[] errorTypes) {
		super(errorTypes);
		this.errors = new ArrayList<BoardError>();
	}

	/**
	 * Returns a list of errors found in the given alligator expression.
	 * 
	 * @param b
	 *            the {@link BoardObject} to find errors in
	 * @return a list of all errors found in the given expression
	 */
	public static List<BoardError> find(BoardObject b) {
		FindBoardErrors finder = new FindBoardErrors();
		b.accept(finder);
		return finder.errors;
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
		FindBoardErrors finder = new FindBoardErrors(errorTypes);
		b.accept(finder);
		return finder.errors;
	}

	@Override
	public void visitEgg(Egg egg) {
		if (validateObjectUncolored && egg.getColor().equals(Color.uncolored())) {
			this.errors.add(new ObjectUncoloredError(egg));
		}
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		if (validateColoredAlligatorChildless && alligator.getChildCount() == 0) {
			this.errors.add(new ColoredAlligatorChildlessError(alligator));
		}
		if (validateObjectUncolored
				&& alligator.getColor().equals(Color.uncolored())) {
			this.errors.add(new ObjectUncoloredError(alligator));
		}
		alligator.acceptOnChildren(this);
	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		if (validateAgedAlligatorChildless && alligator.getChildCount() == 0) {
			this.errors.add(new AgedAlligatorChildlessError(alligator));
		}
		alligator.acceptOnChildren(this);
	}

	@Override
	public void visitBoard(Board board) {
		if (validateEmptyBoard && board.getChildCount() == 0) {
			this.errors.add(new EmptyBoardError(board));
		}
		board.acceptOnChildren(this);
	}
}
