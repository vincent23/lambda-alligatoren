package de.croggle.game.board.operations.validation;

public abstract class AbstractBoardValidator {
	protected boolean validateObjectUncolored = false;
	protected boolean validateAgedAlligatorChildless = false;
	protected boolean validateColoredAlligatorChildless = false;
	protected boolean validateEmptyBoard = false;

	public AbstractBoardValidator(BoardErrorType[] errorTypes) {
		this.applyErrorTypeSettings(errorTypes);
	}

	/**
	 * Configures this {@link ValidateConstellation} to look for all
	 * {@link BoardErrorType}s specified in the given array.
	 * 
	 * @param errorTypes
	 *            the error types to be looked for
	 */
	protected final void applyErrorTypeSettings(BoardErrorType[] errorTypes) {
		validateObjectUncolored = false;
		validateAgedAlligatorChildless = false;
		validateColoredAlligatorChildless = false;
		validateEmptyBoard = false;
		for (BoardErrorType t : errorTypes) {
			switch (t) {
			case AGEDALLIGATOR_CHILDLESS: {
				this.validateAgedAlligatorChildless = true;
				break;
			}
			case COLOREDALLIGATOR_CHILDLESS: {
				this.validateColoredAlligatorChildless = true;
				break;
			}
			case OBJECT_UNCOLORED: {
				this.validateObjectUncolored = true;
				break;
			}
			case EMPTY_BOARD: {
				this.validateEmptyBoard = true;
				break;
			}
			default: {
				throw new IllegalStateException("This should never happen");
			}
			}
		}
	}
}
