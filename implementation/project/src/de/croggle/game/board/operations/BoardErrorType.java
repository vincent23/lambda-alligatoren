package de.croggle.game.board.operations;

/**
 * An enumeration describing the different BoardError types. Can be used to
 * specify which errors are to be found/looked for when performing a
 * {@link FindBoardErrors} search.
 * 
 */
public enum BoardErrorType {
	/**
	 * Represents {@link ObjectUncoloredError}s.
	 */
	UNCOLORED_OBJECT,
	/**
	 * Represents {@link AgedAlligatorChildlessError}s.
	 */
	AGEDALLIGATOR_CHILDLESS,
	/**
	 * Represents {@link ColoredAlligatorChildlessError}s.
	 */
	COLOREDALLIGATOR_CHILDLESS
}
