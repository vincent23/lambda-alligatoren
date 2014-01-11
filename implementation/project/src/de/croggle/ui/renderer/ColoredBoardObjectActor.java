package de.croggle.ui.renderer;

import de.croggle.game.ColorController;
import de.croggle.game.board.InternalBoardObject;

public class ColoredBoardObjectActor extends BoardObjectActor {
	
	protected final ColorController controller;

	public ColoredBoardObjectActor(InternalBoardObject object, ColorController controller) {
		super(object);
		this.controller = controller;
	}

}
