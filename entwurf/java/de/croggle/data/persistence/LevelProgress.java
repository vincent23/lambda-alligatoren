package de.croggle.data.persistence;



/**
 * Represents the saved progress of a user in one level in the database.
 */
public class LevelProgress {

	private long levelId;
	private boolean solved;
	private String currentBoard;
	private int usedResets;
	private int usedHints;
	private int usedTime;

	/**
	 * Constructs a new LevelProgress based on it's properties.
	 *
	 * @param profileId the id of the user's profile
	 * @param levelId the id of the level
	 * @param solved whether the level has been solved
	 * @param currentBoard the serialized representation of the current board
	 * @param usedResets the number of resets used by the user
	 * @param usedHints the number of hints used by the user
	 * @param usedTime the time spent in the level by the user
	 */
	public LevelProgress (long levelId, boolean solved, String currentBoard, int usedResets, int usedHints, int usedTime) {
	}

	/**
	 * Constructs a new LevelProgress using a cursor to the correct database row.
	 *
	 * @param cursor the cursor
	 */
	public LevelProgress(android.database.Cursor cursor) {
	}

	/**
	 * Gets the id of the level.
	 *
	 * @return the level id
	 */
	public long getLevelId() {
		return levelId;
	}

	/**
	 * Sets the id of the level.
	 *
	 * @param levelId the level id
	 */
	public void setLevelId(long levelId) {
		this.levelId = levelId;
	}

	/**
	 * Gets whether the level has been solved.
	 *
	 * @return true if the level has been solved, false otherwise
	 */
	public boolean isSolved() {
		return solved;
	}

	/**
	 * Sets whether the level has been solved.
	 *
	 * @param solved true if the level has been solved, false otherwise
	 */
	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	/**
	 * Gets the serialized version of the current board.
	 *
	 * @return the currently used board
	 */
	public String getCurrentBoard() {
		return currentBoard;
	}

	/**
	 * Sets the serialized version of the current board.
	 *
	 * @param currentBoard the currently used board
	 */
	public void setCurrentBoard(String currentBoard) {
		this.currentBoard = currentBoard;
	}

	/**
	 * Gets the number of resets by the user.
	 *
	 * @return the number of times the user reseted the level
	 */
	public int getUsedResets() {
		return usedResets;
	}

	/**
	 * Sets the number of resets by the user.
	 *
	 * @param usedResets the number of times the user reseted the level
	 */
	public void setUsedResets(int usedResets) {
		this.usedResets = usedResets;
	}

	/**
	 * Gets the number of hints used by the user.
	 *
	 * @return the number of times the user used hints
	 */
	public int getUsedHints() {
		return usedHints;
	}

	/**
	 * Sets the number of hints used by the user.
	 *
	 * @param usedHints the number of times the user used hints
	 */
	public void setUsedHints(int usedHints) {
		this.usedHints = usedHints;
	}

	/**
	 * Gets the time spent by the user in the level.
	 *
	 * @return the time in seconds
	 */
	public int getUsedTime() {
		return usedTime;
	}

	/**
	 * Sets the time spent by the user in the level.
	 *
	 * @param usedTime the time in seconds
	 */
	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}

}
