package de.croggle.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.croggle.game.model.Board;

/**
 * An actor used for representing an alligator constellation.
 *
 * @navassoc 1 - 1 Board
 */
class BoardActor extends ParentActor {

	/**
	 * Creates a new actor.
	 *
	 * @param board Starting board to draw.
	 */
	public BoardActor(Board board) {
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	@Override
	public void act(float delta) {
	}
}
