package de.croggle.game.board.operations.validation;

public abstract class AbstractBoardValidator {
	protected boolean objectsUncolored = false;
	protected boolean agedAlligatorChildless = false;
	protected boolean coloredAlligatorChildless = false;
	protected boolean boardEmpty = false;

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
}
