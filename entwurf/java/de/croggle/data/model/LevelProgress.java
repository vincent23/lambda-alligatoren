package de.croggle.data.model;

import android.database.Cursor;

import de.croggle.game.model.Board;

public class LevelProgress {

	private long id;
	private long profileId;
	private long levelId;
	private boolean solved;
	private String currentBoard;
	private int usedResets;
	private int usedHints;

	public LevelProgress(long profileId, long levelId, boolean solved, String currentBoard, int usedResets, usedHints) {
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

	public String getCurrentBoard() {
		return currentBoard;
	}

	public void setCurrentBoard(String currentBoard) {
		this.currentBoard = currentBoard;
	}

	public int getUsedResets() {
		return usedResets;
	}

	public void setUsedResets(int usedResets) {
		this.usedResets = usedResets;
	}

	public int getUsedHints() {
		return usedHints;
	}

	public void setUsedHints(int usedHints) {
		this.usedHints = usedHints;
	}
}
