package de.croggle.game.level;

import com.badlogic.gdx.graphics.g2d.Animation;

import de.croggle.game.board.Board;

public abstract class EditLevel extends Level {

	public EditLevel(int levelIndex, int packageIndex, Board initialBoard,
			Board goalBoard, Animation animation, String hint,
			String description, int abortSimulationAfter) {
		super(levelIndex, packageIndex, initialBoard, goalBoard, animation, hint,
				description, abortSimulationAfter);
	}

	@Override
	public boolean isLevelSolved(Board solution, int steps) {
		// TODO Auto-generated method stub
		return false;
	}

}
