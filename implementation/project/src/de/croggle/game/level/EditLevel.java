package de.croggle.game.level;

import com.badlogic.gdx.graphics.g2d.Animation;

import de.croggle.game.Color;
import de.croggle.game.EditLevelGameController;
import de.croggle.game.board.Board;

public abstract class EditLevel extends Level {

	private Color[] userColors;
	private Color[] blockedColors;

	public EditLevel(int levelIndex, int packageIndex, Board initialBoard,
			Board goalBoard, Animation animation, Color[] userColors,
			Color[] blockedColors, String hint, String description,
			int abortSimulationAfter) {
		super(levelIndex, packageIndex, initialBoard, goalBoard, animation,
				hint, description, abortSimulationAfter);
		this.userColors = userColors;
		this.blockedColors = blockedColors;
	}

	@Override
	public boolean isLevelSolved(Board solution, int steps) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Method to get the userColors of the level.
	 * 
	 * @return the user colors of this level
	 */
	public Color[] getUserColor() {
		return this.userColors;
	}

	/**
	 * Method to get the blocked colors of the level.
	 * 
	 * @return the blocked colors of this level
	 */
	public Color[] getBlockedColor() {
		return this.blockedColors;
	}

	@Override
	public EditLevelGameController createGameController() {
		return new EditLevelGameController(this);
	}

}
