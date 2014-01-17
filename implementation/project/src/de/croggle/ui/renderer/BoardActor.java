package de.croggle.ui.renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

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
public class BoardActor extends Actor implements BoardEventListener {

	private Map<InternalBoardObject, BoardObjectActor> actors;
	private Board board;
	private ActorLayoutConfiguration config;
	private OrthographicCamera camera;
	
	private final Vector2 CAMERA_SIZE = new Vector2(1024, 600);
	
	private BoardActor(Board b) {
		this.board = b;
		actors = new HashMap<InternalBoardObject, BoardObjectActor>();
		camera = new OrthographicCamera(CAMERA_SIZE.x, CAMERA_SIZE.y);
		camera.position.set(CAMERA_SIZE.x / 2 - getX(), CAMERA_SIZE.y / 2 - getY(), 0);
		camera.update();
	}
	
	/**
	 * Creates a new BoardActor with the given {@link ActorLayoutConfiguration}.
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
		this.config = new ActorLayoutConfiguration(board);
		config.setColorController(controller);
		this.onBoardRebuilt(board);
	}
	
	/**
	 * GestureListener for the BoardActor
	 * 
	 */
	private class BoardActorGestureListener extends ActorGestureListener {
		@Override
		public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
			camera.position.x -= deltaX * camera.zoom;
			camera.position.y -= deltaY * camera.zoom;
			camera.update();
			//BoardActor.this.setX(getX() + deltaX);
			//BoardActor.this.setY(getY() + deltaY);
		}
		
		public void zoom(InputEvent event, float initialDistance, float distance) {
			System.out.println("zoom: " + camera.zoom);
			
			/*float offset = (float) (0.02 * Math.signum(initialDistance - distance));
			if (camera.zoom + offset > 0) {
				camera.zoom += offset;
			}
			*/
			camera.zoom *= Math.pow(1.02, (initialDistance - distance) /200);
			camera.update();
		}
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
		batch.flush();
		Matrix4 original = batch.getProjectionMatrix();
		// set up clipping
		clipBegin();
		// set own camera to look through
		batch.setProjectionMatrix(camera.combined);
		for (BoardObjectActor child : actors.values()) {
			child.draw(batch, parentAlpha);
		}
		batch.setProjectionMatrix(original);
		clipEnd();
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
			final InternalBoardObject eatenFamily, int eatenParentPosition) {
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
	public void onAgedAlligatorVanishes(AgedAlligator alligator, int positionInParent) {
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
		ActorLayoutFixer.fixOnRemove(replacedEgg, replacedEgg.getParent(),
				actors, config);
		// still use replacedEgg's parent as bornFamily's parent might not yet
		// have been set
		ActorLayoutFixer.fixOnAdd(bornFamily, replacedEgg.getParent(), actors,
				config);
	}
	
	@Override
	protected void sizeChanged() {
		System.out.println("Size changed: " + getWidth() + ", " + getHeight());
		// we want to always have the camera believe it shows 1024 x 600 pixels
		//camera.viewportWidth = getWidth();
		//camera.viewportHeight = getHeight();
		
		// unfortunately, getX gives us screen pixels, which are not suitable for our game-world pixels,
		// so we must translate them first
		float x = getX() / Gdx.graphics.getWidth() * CAMERA_SIZE.x;
		float y = getY() / Gdx.graphics.getHeight() * CAMERA_SIZE.y;
		camera.position.set(CAMERA_SIZE.x / 2 - x, CAMERA_SIZE.y / 2 - y, 0);
		camera.update();
		// use device independent camera size to display alligator world
		this.addListener(new BoardActorGestureListener());
	}
}
