package de.croggle.data.persistence;

/**
 * Represents the progress saved by a user during one level in the database.
 */
public class LevelProgress {

	private int levelId;
	private boolean solved;
	private String currentBoard;
	private int usedTime;

	/**
	 * Constructs a new LevelProgress based on it's properties.
	 * 
	 * @param levelId
	 *            the id of the level
	 * @param solved
	 *            whether the level has been solved
	 * @param currentBoard
	 *            the serialized representation of the current board
	 * @param usedTime
	 *            the time spent in the level by the user
	 */
	public LevelProgress(int levelId, boolean solved, String currentBoard,
			int usedTime) {
		this.levelId = levelId;
		this.solved = solved;
		this.currentBoard = currentBoard;
		this.usedTime = usedTime;
	}

	/**
	 * Gets the id of the level.
	 * 
	 * @return the level id
	 */
	public int getLevelId() {
		return levelId;
	}

	/**
	 * Sets the id of the level.
	 * 
	 * @param levelId
	 *            the level id
	 */
	public void setLevelId(int levelId) {
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
	 * @param solved
	 *            true if the level has been solved, false otherwise
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
	 * @param currentBoard
	 *            the currently used board
	 */
	public void setCurrentBoard(String currentBoard) {
		this.currentBoard = currentBoard;
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
	 * @param usedTime
	 *            the time in seconds
	 */
	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LevelProgress other = (LevelProgress) obj;
		if (currentBoard == null) {
			if (other.currentBoard != null)
				return false;
		} else if (!currentBoard.equals(other.currentBoard))
			return false;
		if (levelId != other.levelId)
			return false;
		if (solved != other.solved)
			return false;
		if (usedTime != other.usedTime)
			return false;
		return true;
	}

}
