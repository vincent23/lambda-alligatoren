package de.croggle.game;

import de.croggle.AlligatorApp;
import de.croggle.game.level.EditLevel;

public class EditLevelGameController extends GameController {
	private EditLevel level;

	public EditLevelGameController(AlligatorApp app, EditLevel level) {
		super(app, level);
		this.level = level;
		setupColorController();
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

}
