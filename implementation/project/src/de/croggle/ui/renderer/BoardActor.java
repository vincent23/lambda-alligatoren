package de.croggle.ui.renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

import de.croggle.game.ColorController;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.operations.FlattenTree;
import de.croggle.game.event.BoardEventListener;

/**
 * An actor used for representing a whole board, i.e. an alligator
 * constellation.
 */
public class BoardActor extends Group implements BoardEventListener {

	private Map<InternalBoardObject, BoardObjectActor> actors;
	private Board board;
	private ActorLayoutConfiguration config;
	
	private BoardActor(Board b) {
		this.board = b;
		actors = new HashMap<InternalBoardObject, BoardObjectActor>();
	}
	
	/**
	 * Creates a new BoardActor.
	 * 
	 * @param board
	 */
	public BoardActor(Board board, ActorLayoutConfiguration config) {
		this(board);
		this.config = config;
		actors = new HashMap<InternalBoardObject, BoardObjectActor>();
		this.onBoardRebuilt(board);
	}

	/**
	 * Creates a new BoardActor. This is the simpler version of constructing a
	 * BoardActor, using most of the default {@link ActorLayoutConfiguration}
	 * properties, only requiring the {@link ColorController} to be set
	 * correctly.
	 * 
	 * @param board
	 */
	public BoardActor(Board board, ColorController controller) {
		this(board);
		this.config = new ActorLayoutConfiguration();
		config.setColorController(controller);
		this.onBoardRebuilt(board);
	}
	
	/**
	 * Returns this {@link BoardActor}'s {@link ActorLayoutConfiguration configuration to layout its actors}.
	 * 
	 * @return this {@link BoardActor}'s {@link ActorLayoutConfiguration}
	 */
	public ActorLayoutConfiguration getLayoutConfiguration() {
		return config;
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
		for (BoardObjectActor boa : actors.values()) {
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
	public void onObjectRecolored(ColoredBoardObject recoloredObject) {
		actors.get(recoloredObject);
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
	public void onEat(final ColoredAlligator eater,
			final InternalBoardObject eatenFamily) {
		ColoredAlligatorActor eaterActor = ((ColoredAlligatorActor) actors
				.get(eater));
		eaterActor.enterEatingState();
		final List<InternalBoardObject> eatenLst = FlattenTree
				.toList(eatenFamily);

		final float animDuration = 0.2f;

		for (InternalBoardObject eaten : eatenLst) {
			MoveToAction action = new MoveToAction();
			action.setPosition(eaterActor.getX(), eaterActor.getY());
			action.setDuration(animDuration);
			actors.get(eaten).addAction(action);
		}

		this.addAction(new TemporalAction() {
			protected void begin() {
				setDuration(animDuration);
			}

			@Override
			protected void update(float percent) {
				// do nothing
			}

			protected void end() {
				for (InternalBoardObject eaten : eatenLst) {
					removeActor(actors.get(eaten));
					actors.remove(eaten);
				}
				removeObjectAnimated(eater);
				ActorLayoutFixer.fixOnRemove(eatenFamily,
						eatenFamily.getParent(), actors, config);
			}
		});
	}
	
	/**
	 * Removes 
	 * 
	 * @param object
	 */
	protected void removeObjectAnimated(final InternalBoardObject object) {
		final float fadingtime = .3f;
		// TODO make sure, fading out even works on our custom actors
		this.addAction(Actions.fadeOut(fadingtime));
		this.addAction(new TemporalAction() {
			protected void begin() {
				setDuration(fadingtime);
			}

			@Override
			protected void update(float percent) {
				// do nothing
			}

			protected void end() {
				removeActor(actors.get(object));
				actors.remove(object);
				ActorLayoutFixer.fixOnRemove(object, object.getParent(), actors, config);
			}
		});
	}

	/**
	 * Visualizes the disappearance of an aged alligator on the board.
	 * 
	 * @param alligator
	 *            the alligator which disappeared
	 */
	@Override
	public void onAgedAlligatorVanishes(AgedAlligator alligator) {
		removeObjectAnimated(alligator);
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
		this.actors = ActorLayoutBuilder.build(board, config);
		clear();
		for (BoardObjectActor actor : this.actors.values()) {
			addActor(actor);
		}
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
		removeActor(actors.get(replacedEgg));
		removeActor(actors.get(replacedEgg));
		ActorLayoutFixer.fixOnRemove(replacedEgg, replacedEgg.getParent(),
				actors, config);
		// still use replacedEgg's parent as bornFamily's parent might not yet
		// have been set
		ActorLayoutFixer.fixOnAdd(bornFamily, replacedEgg.getParent(), actors,
				config);
	}
}
