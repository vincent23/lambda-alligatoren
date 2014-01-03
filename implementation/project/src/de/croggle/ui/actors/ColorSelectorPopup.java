package de.croggle.ui.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;

import de.croggle.game.board.ColoredBoardObject;

/**
 * An actor displaying a set of colors that can be tapped to set a BoardObject's
 * color.
 * 
 */
public class ColorSelectorPopup extends Actor {
	
	private final ColoredBoardObject boardObject;
	
	/**
	 * Creates a new popup to offer the possibility to recolor a certain ColoredBoardObect.
	 * 
	 * @throws IllegalArgumentException if the given {@link ColoredBoardObject} does not allow recoloring
	 * @param c the {@link ColoredBoardObject} to recolor
	 */
	public ColorSelectorPopup(ColoredBoardObject c) {
		if (!c.isRecolorable()) {
			throw new IllegalArgumentException("ColorSelectPopup can only work on ColoredBoardObjects which allow  recoloring.");
		}
		
		this.boardObject = c;
	}
	
}
