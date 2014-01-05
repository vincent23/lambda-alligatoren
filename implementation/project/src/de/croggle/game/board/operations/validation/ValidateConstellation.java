package de.croggle.game.board.operations.validation;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.board.operations.BoardObjectVisitor;

/**
 * A visitor for checking whether the given Board represents a valid term within
 * the lambda calculus (whether the evaluation is possible or not).
 */
public class ValidateConstellation extends AbstractBoardValidator implements
		BoardObjectVisitor {

	private boolean isValid = true;

	/**
	 * Initializes a constellation validator by configuring it so it will only
	 * look for {@link ObjectUncoloredError}s and
	 * {@link ColoredAlligatorChildlessError}s.
	 */
	private ValidateConstellation() {
		this(new BoardErrorType[] { BoardErrorType.COLOREDALLIGATOR_CHILDLESS,
				BoardErrorType.OBJECT_UNCOLORED });
	}

	/**
	 * 
	 */
	private ValidateConstellation(BoardErrorType[] errorTypes) {
		super(errorTypes);
	}

	/**
	 * Checks whether the given family represents a valid term within the lambda
	 * calculus.
	 * 
	 * @param family
	 *            the family to check for validity
	 * @return true if the family is valid, false otherwise
	 */
	public static boolean isValid(BoardObject family) {
		ValidateConstellation validator = new ValidateConstellation();
		family.accept(validator);
		return validator.isValid;
	}

	/**
	 * Checks whether the given family represents a valid term within the lambda
	 * calculus, only considering the given set of {@link BoardErrorType}s.
	 * 
	 * @param family
	 *            the family to check for validity
	 * @param errorTypes
	 *            the types of errors being looked for
	 * @return true if the family is valid, false otherwise
	 */
	public static boolean isValid(BoardObject family,
			BoardErrorType[] errorTypes) {
		ValidateConstellation validator = new ValidateConstellation(errorTypes);
		family.accept(validator);
		return validator.isValid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitEgg(Egg egg) {
		validateColoredObject(egg);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		// Attention: must remain in this order, otherwise cancellation of child
		// iteration will not happen if the alligator has no color
		validateColoredObject(alligator);
		validateParent(alligator, validateColoredAlligatorChildless);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		validateParent(alligator, validateAgedAlligatorChildless);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visitBoard(Board board) {
		validateParent(board, validateEmptyBoard);
	}

	/**
	 * Takes a given ColoredBoardObject and invalidates this validation run if
	 * its color is {@link Color}.uncolored() and objectsUncolored is set to
	 * true.
	 * 
	 * @param cbo
	 *            the ColoredBoardObject to be validated
	 */
	private void validateColoredObject(ColoredBoardObject cbo) {
		if (this.validateObjectUncolored) {
			this.isValid &= !cbo.getColor().equals(Color.uncolored());
		}
	}

	/**
	 * Takes a parent and the corresponding "look for empty XY" flag (e.g.
	 * agedAlligatorChildless) and tests, if the flag is set, if the parent is
	 * childless. In this case, the validation run will be invalidated.
	 * 
	 * @param p
	 *            the parent to be validated
	 * @param validationEnabled
	 *            the "look for"-flag corresponding to the given parent's type
	 */
	private void validateParent(Parent p, boolean validationEnabled) {
		if (validationEnabled && p.getChildCount() == 0) {
			this.isValid = false;
		} else {
			for (InternalBoardObject child : p) {
				child.accept(this);
				if (!isValid) {
					break;
				}
			}
		}
	}

}
