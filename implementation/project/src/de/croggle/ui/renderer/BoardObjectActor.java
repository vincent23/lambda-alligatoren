package de.croggle.ui.renderer;

import com.badlogic.gdx.scenes.scene2d.Actor;

import de.croggle.game.board.InternalBoardObject;

/**
 * An actor used for representing a board object.
 */
public abstract class BoardObjectActor extends Actor {

	private final InternalBoardObject object;

	/**
	 * The superconstructor for all board object actors.
	 * 
	 * @param object
	 *            the InternalBoardObject represented by the BoardObjectActor
	 */
	public BoardObjectActor(InternalBoardObject object) {
		this.object = object;
	}

	public InternalBoardObject getBoardObject() {
		return object;
	}
}
