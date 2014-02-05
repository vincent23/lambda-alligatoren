package de.croggle.game;

import com.badlogic.gdx.Screen;

import de.croggle.AlligatorApp;
import de.croggle.game.level.MultipleChoiceLevel;
import de.croggle.ui.screens.MultipleChoiceScreen;

public class MultipleChoiceGameController extends GameController {
	private final MultipleChoiceLevel level;
	private int selection;

	public MultipleChoiceGameController(AlligatorApp app,
			MultipleChoiceLevel level) {
		super(app, level);
		this.level = level;
	}

	public void setSelection(int selection) {
		this.selection = selection;
		setSolved(level.validateAnswer(selection));
	}

	@Override
	public Screen createPlacementScreen(AlligatorApp app) {
		return new MultipleChoiceScreen(app, this);
	}
}
