package de.croggle.ui.renderer;

import com.badlogic.gdx.scenes.scene2d.Actor;

import de.croggle.game.board.BoardObject;

/**
 * An actor used for representing a board object.
 */
public abstract class BoardObjectActor extends Actor {

	private final BoardObject object;

	/**
	 * The superconstructor for all board object actors.
	 * 
	 * @param object
	 *            the InternalBoardObject represented by the BoardObjectActor
	 */
	public BoardObjectActor(BoardObject object) {
		this.object = object;
	}
}
