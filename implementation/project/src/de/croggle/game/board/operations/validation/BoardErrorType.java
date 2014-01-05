package de.croggle.game.board.operations.validation;

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
	OBJECT_UNCOLORED,
	/**
	 * Represents {@link AgedAlligatorChildlessError}s.
	 */
	AGEDALLIGATOR_CHILDLESS,
	/**
	 * Represents {@link ColoredAlligatorChildlessError}s.
	 */
	COLOREDALLIGATOR_CHILDLESS,
	/**
	 * Represents {@link EmptyBoardError}s.
	 */
	EMPTY_BOARD;

	/**
	 * Returns all representations of {@link BoardError}s in this enumeration.
	 * Has the same effect as the standard Enum.values() method.
	 * 
	 * @return all representations of {@link BoardError}s in this enumeration
	 */
	public static BoardErrorType[] all() {
		return BoardErrorType.values();
	}
}
