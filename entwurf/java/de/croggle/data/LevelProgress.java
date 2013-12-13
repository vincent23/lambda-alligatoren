package de.croggle.data;



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
	 * @param profileId The id of the user's profile.
	 * @param levelId The id of the level.
	 * @param solved Whether the level has been solved.
	 * @param currentBoard The serialized representation of the current board.
	 * @param usedResets The number of resets used by the user.
	 * @param usedHints The number of hints used by the user.
	 * @param usedTime The time spent in the level by the user.
	 */
	public LevelProgress (long levelId, boolean solved, String currentBoard, int usedResets, int usedHints, int usedTime) {
	}

	/**
	 * Construct a new LevelProgress using a cursor to the correct database row.
	 *
	 * @param cursor The cursor.
	 */
	public LevelProgress(android.database.Cursor cursor) {
	}

	/**
	 * Get the id of the level.
	 *
	 * @return The level id.
	 */
	public long getLevelId() {
		return levelId;
	}

	/**
	 * Set the id of the level.
	 *
	 * @param levelId The level id.
	 */
	public void setLevelId(long levelId) {
		this.levelId = levelId;
	}

	/**
	 * Get whether the level has been solved.
	 *
	 * @return True if the level has been solved, false otherwise.
	 */
	public boolean isSolved() {
		return solved;
	}

	/**
	 * Set whether the level has been solved.
	 *
	 * @param solved True if the level has been solved, false otherwise.
	 */
	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	/**
	 * Get the serialized version of the current board.
	 *
	 * @return The board.
	 */
	public String getCurrentBoard() {
		return currentBoard;
	}

	/**
	 * Set the serialized version of the current board.
	 *
	 * @param currentBoard The board.
	 */
	public void setCurrentBoard(String currentBoard) {
		this.currentBoard = currentBoard;
	}

	/**
	 * Get the number of resets by the user.
	 *
	 * @return The number of resets.
	 */
	public int getUsedResets() {
		return usedResets;
	}

	/**
	 * Set the number of resets by the user.
	 *
	 * @param usedResets The number of resets.
	 */
	public void setUsedResets(int usedResets) {
		this.usedResets = usedResets;
	}

	/**
	 * Get the number of hints used by the user.
	 *
	 * @return The number of hints.
	 */
	public int getUsedHints() {
		return usedHints;
	}

	/**
	 * Set the number of hints used by the user.
	 *
	 * @param usedHints The number of hints.
	 */
	public void setUsedHints(int usedHints) {
		this.usedHints = usedHints;
	}

	/**
	 * Get the time spent by the user in the level.
	 *
	 * @return The time in seconds.
	 */
	public int getUsedTime() {
		return usedTime;
	}

	/**
	 * Set the time spent by the user in the level.
	 *
	 * @param usedTime The time in seconds.
	 */
	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}

}
