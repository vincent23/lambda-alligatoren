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
	 * Construct a new LevelProgress based on it's properties.
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
	 * Construct a new LevelProgress using a cursor to the correct database row.
	 *
	 * @param cursor the cursor
	 */
	public LevelProgress(android.database.Cursor cursor) {
	}

	/**
	 * Get the id of the level.
	 *
	 * @return the level id
	 */
	public long getLevelId() {
		return levelId;
	}

	/**
	 * Set the id of the level.
	 *
	 * @param levelId the level id
	 */
	public void setLevelId(long levelId) {
		this.levelId = levelId;
	}

	/**
	 * Get whether the level has been solved.
	 *
	 * @return true if the level has been solved, false otherwise
	 */
	public boolean isSolved() {
		return solved;
	}

	/**
	 * Set whether the level has been solved.
	 *
	 * @param solved true if the level has been solved, false otherwise
	 */
	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	/**
	 * Get the serialized version of the current board.
	 *
	 * @return the currently used board
	 */
	public String getCurrentBoard() {
		return currentBoard;
	}

	/**
	 * Set the serialized version of the current board.
	 *
	 * @param currentBoard the currently used board
	 */
	public void setCurrentBoard(String currentBoard) {
		this.currentBoard = currentBoard;
	}

	/**
	 * Get the number of resets by the user.
	 *
	 * @return the number of time the user reseted the level
	 */
	public int getUsedResets() {
		return usedResets;
	}

	/**
	 * Set the number of resets by the user.
	 *
	 * @param usedResets the number of time the user reseted the level
	 */
	public void setUsedResets(int usedResets) {
		this.usedResets = usedResets;
	}

	/**
	 * Get the number of hints used by the user.
	 *
	 * @return the number of time the user used hints
	 */
	public int getUsedHints() {
		return usedHints;
	}

	/**
	 * Set the number of hints used by the user.
	 *
	 * @param usedHints the number of time the user used hints
	 */
	public void setUsedHints(int usedHints) {
		this.usedHints = usedHints;
	}

	/**
	 * Get the time spent by the user in the level.
	 *
	 * @return the time in seconds
	 */
	public int getUsedTime() {
		return usedTime;
	}

	/**
	 * Set the time spent by the user in the level.
	 *
	 * @param usedTime the time in seconds
	 */
	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}

}
