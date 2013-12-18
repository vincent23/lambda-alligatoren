package de.croggle.game.level;

import com.badlogic.gdx.graphics.g2d.Animation;

import de.croggle.game.Color;
import de.croggle.game.board.Board;

/**
 * @composed 1 - 3 de.croggle.game.board.Board
 */
public abstract class Level {
	private int levelIndex;
	private int packageIndex;

	private Board inputBoard;
	private Board goalBoard;
	private Animation animation;

	private Color userColors[];

	public int getPackageIndex() {
		return 0;
	}

	public int getLevelIndex() {
		return 0;
	}

	// LevelState state; //?
	public Board getInputBoard() {
		return null;
	}

	public Board getGoalBoard() {
		return null;
	}

	public abstract boolean hasAnimation();

	public Animation getAnimation() {
		return null;
	}
}
