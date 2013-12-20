package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.croggle.game.event.BoardEventListener;

import de.croggle.game.board.Alligator;
import de.croggle.game.board.AgedAlligator;
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

    /**
     * Draws the actor. The sprite batch is configured to draw in he parent's coordinate system.
     * @param batch The sprite batch specifies where to draw into.
     * @param parentAlpha
     */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

    /**
     * Updates the actor based on time.
     * @param delta Time in seconds since the last update.
     */
	@Override
	public void act(float delta) {
	}

	/**
	 * Visualizes the recoloring of an object on the board.
     * @param recoloredObject The object that has been recolored.
	 */
	@Override
	public void onObjectRecolored(InternalBoardObject recoloredObject) {
	}

	/**
	 * Visualizes the process of one alligator eating another and its children, or just an egg, on the board.
     * @param eater The alligator, which eats the other alligator.
     * @param eatenFamily The family which is eaten by the other alligator.
	 */
	@Override
	public void onEat(ColoredAlligator eater, InternalBoardObject eatenFamily) {
	}

	/**
	 * Visualizes the disappearance of an aged alligator on the board.
     * @param alligator The alligator which disappeared.
	 */
	@Override
	public void onAgedAlligatorVanishes(AgedAlligator alligator) {
	}

	/**
	 * Completely rebuilds the board as it is seen on the screen.
     * @param board The board that is going to replace the board that was seen previously.
	 */
	@Override
	public void onBoardRebuilt(Board board) {
	}

	/**
	 * Visualizes the process of replacing an egg within a family with the family the protecting alligator has eaten.
     * @param replacedEgg The hatching egg.
     * @param bornFamily The family that hatches from that egg.
	 */
	@Override
	public void onReplace(Egg replacedEgg, InternalBoardObject bornFamily) {
	}
}
