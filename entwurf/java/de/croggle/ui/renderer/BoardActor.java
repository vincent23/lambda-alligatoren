package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.croggle.game.event.BoardEventListener;

import de.croggle.game.board.Alligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;

import java.util.HashMap;

/**
 * An actor used for representing an alligator constellation.
 *
 * @has 1 - * BoardObjectActor
 */
public class BoardActor extends ParentActor implements BoardEventListener {

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

	/**
	 *
	 */
	@Override
	public void onObjectRecolored(InternalBoardObject recoloredObject) {
	}

	/**
	 *
	 */
	@Override
	public void onEat(ColoredAlligator eater, InternalBoardObject eatenFamily) {
	}

	/**
	 *
	 */
	@Override
	public void onAlligatorVanishes(Alligator alligator) {
	}

	/**
	 *
	 */
	@Override
	public void onBoardRebuilt(Board board) {
	}

	/**
	 *
	 */
	@Override
	public void onReplace(Egg replacedEgg, InternalBoardObject bornFamily) {
	}
}
