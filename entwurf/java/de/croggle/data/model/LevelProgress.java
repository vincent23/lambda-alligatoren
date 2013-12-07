package de.croggle.data.model;

import android.database.Cursor;

import de.croggle.game.model;

public class LevelProgress {

	private long id;
	private long profileId;
	private long levelId;
	private boolean solved;
	private String currentBoard;

	public LevelProgress(long profileId, long levelId, boolean solved, Board currentBoard) {
	}

	public LevelProgress(android.database.Cursor cursor) {
	}

	public long getId() {
		return id;
	}

	public long getProfiledId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public long getLevelId() {
		return levelId;
	}

	public void setLevelId(long levelId) {
		this.levelId = levelId;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public Board getCurrentBoard() {
	}

	public void setCurrentBoard(Board board) {
	}
}
