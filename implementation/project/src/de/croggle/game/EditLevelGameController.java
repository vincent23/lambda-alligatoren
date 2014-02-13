package de.croggle.game;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.LevelProgress;
import de.croggle.game.board.Board;
import de.croggle.game.level.EditLevel;
import de.croggle.util.convert.AlligatorToJson;
import de.croggle.util.convert.JsonToAlligator;

public class EditLevelGameController extends GameController {
	private final EditLevel level;

	public EditLevelGameController(AlligatorApp app, EditLevel level) {
		super(app, level);
		this.level = level;
		setupColorController();
	}

	@Override
	protected void onAfterLoadProgress(LevelProgress progress) {
		final String serializedBoard = progress.getCurrentBoard();
		if (serializedBoard == null) {
			return;
		}
		final Board previousBoard = JsonToAlligator
				.convertBoard(serializedBoard);
		setUserBoard(previousBoard);
	}

	@Override
	protected void onBeforeSaveProgress(LevelProgress progress) {
		progress.setCurrentBoard(AlligatorToJson.convert(getUserBoard()));
	}

	@Override
	protected ColorController createColorController() {
		if (level == null) {
			// call from super constructor, ignore this
			return null;
		}
		final ColorController colorController = new ColorController();
		for (Color color : level.getUserColor()) {
			colorController.addUsableColor(color);
		}
		for (Color color : level.getBlockedColor()) {
			colorController.addBlockedColor(color);
		}
		return colorController;
	}

	@Override
	protected void onFinishedSimulation() {
		getProgress().setSolved(
				level.isLevelSolved(getSimulator().getCurrentBoard(),
						getSimulator().getSteps()));
	}

}
