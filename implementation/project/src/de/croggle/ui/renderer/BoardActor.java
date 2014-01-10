package de.croggle.ui.renderer;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.board.operations.FlattenTree;
import de.croggle.game.event.BoardEventListener;

/**
 * An actor used for representing a whole board, i.e. an alligator
 * constellation.
 */
public class BoardActor extends Actor implements BoardEventListener {

	private Map<InternalBoardObject, BoardObjectActor> actors;
	private Board board;

	/**
	 * Creates a new actor.
	 * 
	 * @param board
	 */
	public BoardActor(Board board) {
		this.board = board;
	}

	/**
	 * Draws the actor.
	 * 
	 * @param batch
	 *            the sprite batch specifies where to draw into
	 * @param parentAlpha
	 *            the parent's alpha value
	 */
	public void draw(SpriteBatch batch, float parentAlpha) {
		for(BoardObjectActor boa : actors.values()) {
			boa.draw(batch, parentAlpha);
		}
	}

	/**
	 * Updates the actor based on time.
	 * 
	 * @param delta
	 *            time in seconds since the last update
	 */
	public void act(float delta) {
		super.act(delta); // makes progress on every action
	}

	/**
	 * Visualizes the recoloring of an object on the board.
	 * 
	 * @param recoloredObject
	 *            the object that has been recolored
	 */
	@Override
	public void onObjectRecolored(InternalBoardObject recoloredObject) {
	}

	/**
	 * Visualizes the process of one alligator eating another and its children,
	 * or just an egg, on the board.
	 * 
	 * @param eater
	 *            the alligator which eats the other alligator
	 * @param eatenFamily
	 *            the family which is eaten by the other alligator
	 */
	@Override
	public void onEat(ColoredAlligator eater, InternalBoardObject eatenFamily) {
		ColoredAlligatorActor eaterActor = ((ColoredAlligatorActor) actors.get(eater));
		eaterActor.enterEatingState();
		List<InternalBoardObject> eatenLst = FlattenTree.toList(eatenFamily);
		for (InternalBoardObject eaten : eatenLst) {
			MoveToAction action = new MoveToAction();
			action.setPosition(eaterActor.getX(), eaterActor.getY());
			action.setDuration(0.2f);
			actors.get(eaten).addAction(action);
		}
	}

	/**
	 * Visualizes the disappearance of an aged alligator on the board.
	 * 
	 * @param alligator
	 *            the alligator which disappeared
	 */
	@Override
	public void onAgedAlligatorVanishes(AgedAlligator alligator) {
	}

	/**
	 * Completely rebuilds the board as it is seen on the screen.
	 * 
	 * @param board
	 *            the board that is going to replace the board that was seen
	 *            previously
	 */
	@Override
	public void onBoardRebuilt(Board board) {
		this.board = board;
		this.actors = BoardObjectActorBuilder.build(board);
	}

	/**
	 * Visualizes the process of replacing an egg within a family with the
	 * family the protecting alligator has eaten.
	 * 
	 * @param replacedEgg
	 *            the hatching egg
	 * @param bornFamily
	 *            the family that hatches from that egg
	 */
	@Override
	public void onReplace(Egg replacedEgg, InternalBoardObject bornFamily) {
		actors.remove(replacedEgg);
		Parent p = replacedEgg.getParent();
		//ActorLayoutFixer.fixOnRemove(p, p.getChildPosition(bornFamily), childWidth, stillPresent, actors, growth, animationlength);
	}
}
