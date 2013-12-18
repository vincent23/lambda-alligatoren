package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.croggle.game.board.BoardObject;

import java.util.HashMap;

/**
 * An actor used for representing an alligator constellation.
 *
 * @has 1 - * BoardObjectActor
 */
public class BoardActor extends ParentActor {

	private HashMap<BoardObject, BoardObjectActor> actors;

	/**
	 * Creates a new actor.
	 */
	public BoardActor() {
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	@Override
	public void act(float delta) {
	}
}
