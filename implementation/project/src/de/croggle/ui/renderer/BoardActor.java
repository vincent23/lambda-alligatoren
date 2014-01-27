package de.croggle.ui.renderer;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import de.croggle.game.ColorController;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
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

	private ActorLayout layout;

	private final ActorLayoutConfiguration config;
	private float posX;
	private float posY;
	private float maxX;
	private float maxY;
	private float minX;
	private float minY;
	private float maxScale;
	private float minScale;
	private final Vector2 point;

	private BoardActorGestureListener gestureListener;

	public BoardActor(Board b, ActorLayoutConfiguration config) {
		this.config = config;
		this.point = new Vector2();
		onBoardRebuilt(b);

		maxX = Float.POSITIVE_INFINITY;
		maxY = Float.POSITIVE_INFINITY;
		minX = Float.NEGATIVE_INFINITY;
		minY = Float.NEGATIVE_INFINITY;

		calculateLimits();
		initializePosition();
	}
	
	private void calculateLimits() {
		calculateScaleLimits();
		calculatePanLimits();
	}
	
	private void calculateScaleLimits() {
		Board b = layout.getBoard();
		Map<BoardObject, Float> heightMap = layout.getLayoutStatistics().getHeightMap();
		float boardHeight = heightMap.get(b) / getScaleY();
		
		// zoom limits
		float lowestScale = 0.2373046875f; // 0.75^5 TODO
		// allow maximum enlargement to have the smallest object being displayed
		// with half the screen size
		maxScale = getWidth() / (config.getUniformObjectWidth() * 2 * lowestScale);
		// allow maximum the whole tree times 1.2 fitting on the screen
		minScale = Math.min(boardHeight * 1.2f / getHeight(), 1.f);
		
		System.out.println("minScale: " + minScale + ", maxScale: " + maxScale);
	}
	
	private void calculatePanLimits() {
		Board b = layout.getBoard();
		Map<BoardObject, Float> heightMap = layout.getLayoutStatistics().getHeightMap();
		Map<BoardObject, Float> widthMap = layout.getLayoutStatistics().getWidthMap();
		
		Vector2 origin = config.getTreeOrigin();
		float scale = getScaleX();
		
		float boardHeight = heightMap.get(b);
		float boardWidth = widthMap.get(b);
		
		// pan limits
		maxX = getWidth() - origin.x * scale;
		minX = -(origin.x + boardWidth) * scale;
		maxY = getHeight() + (boardHeight - origin.y) * scale;
		minY = -origin.y * scale;
		System.out.println("maxX: " + maxX + ", minX: " + minX);
		System.out.println("maxY: " + maxY + ", minY: " + minY);
	}
	
	private void initializePosition() {
		// have the tree displayed horizontally centered and with its top at the
		// upper edge
		ActorLayoutStatistics stats = layout.getLayoutStatistics();
		Vector2 orig = config.getTreeOrigin();
		float treeMidX = orig.x
				+ stats.getWidthMap().get(layout.getBoard()) / 2;
		float treeTop = orig.y;
		System.out.println("w " + getWidth() + ", h " + getHeight() + ", sx " + getScaleX() + ", sy " + getScaleY());
		posX = -(treeMidX - getWidth() * getScaleX() / 2);
		posY = -(treeTop - getHeight() * getScaleY());
		System.out.println("treeX: " + treeMidX + ", treeY: " + treeTop);
		System.out.println("posX: " + posX + ", posY: " + posY);
	}

	/**
	 * Creates a new BoardActor. This is the simpler version of constructing a
	 * BoardActor, using most of the default {@link ActorLayoutConfiguration}
	 * properties, only requiring the {@link ColorController} to be set
	 * correctly.
	 * 
	 * @param board
	 * @param controller
	 */
	public BoardActor(Board board, ColorController controller) {
		this(board, new ActorLayoutConfiguration()
				.setColorController(controller));
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		float x = getX();
		float y = getY();
		if(clipBegin()) {
			setX(x + posX);
			setY(y + posY);
			
			super.draw(batch, parentAlpha);
			
			setX(x);
			setY(y);
			clipEnd();
		}
	}
	
	@Override
	public Actor hit (float x, float y, boolean touchable) {
		//System.out.println("hit: " + x + ", " + y);
		
		if (touchable && getTouchable() != Touchable.enabled) {
			return null;
		}
		Array<Actor> children = getChildren();
		for (int i = children.size - 1; i >= 0; i--) {
			Actor child = children.get(i);
			if (!child.isVisible()) {
				continue;
			}
			child.parentToLocalCoordinates(point.set(x - posX, y - posY));
			Actor hit = child.hit(point.x, point.y, touchable);
			if (hit != null) {
				return hit;
			}
		}
		// no child hit
		if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
			return this;
		} else {
			return null;
		}
	}

	/**
	 * GestureListener for the BoardActor
	 * 
	 */
	private class BoardActorGestureListener extends ActorGestureListener {

		@Override
		public void pan(InputEvent event, float x, float y, float deltaX,
				float deltaY) {
			Vector2 delta = new Vector2(deltaX, deltaY);
			if (posX + delta.x >= minX
					&& posX + delta.x <= maxX) {
				posX += delta.x;
			}
			if (posY + delta.y >= minY
					&& posY + delta.y <= maxY) {
				posY += delta.y;
			}
			//System.out.println("position: " + posX + ", " + posY);
		}
		
		@Override
		public void pinch(InputEvent event, Vector2 initialPointer1,
				Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
			System.out.println("pinch");
			float density = Gdx.graphics.getDensity();
			float dist = initialPointer1.dst(initialPointer2);
			float newdist = pointer1.dst(pointer2);
			float delta = newdist - dist;
			float percent = delta / density / 4;
			float pointX = initialPointer1.x + (initialPointer2.x - initialPointer1.x)/2;
			float pointY = initialPointer1.y + (initialPointer2.y - initialPointer1.y)/2;
			zoomIn(percent, pointX, pointY);
			System.out.println("delta: " + delta + ", percent: " + percent);
		}
	}

	public boolean zoomIn(float percent) {
		return zoomIn(percent, getWidth() / 2, getHeight()/2);
	}
	
	public boolean zoomIn(float percent, float pointX, float pointY) {
		if (percent < 0) {
			return zoomOut(-percent, pointX, pointY);
		}
		float scale = getScaleX();
		float factor = 1 + percent / 100;
		float newScale = scale * factor;
		if (newScale <= maxScale) {
			setScale(newScale);
			calculatePanLimits();
			
			float newX = pointX * factor;
			float newY = pointY * factor;
			float dx = newX - pointX;
			float dy = newY - pointY;
			
			if (posX - dx >= minX && posX - dx <= maxX) {
				posX -= dx;
			}
			if (posY - dy >= minY && posY - dy <= maxY) {
				posY -= dy;
			}

			return true;
		}
		return false;
	}
	
	public boolean zoomOut(float percent) {
		return zoomOut(percent, getWidth() / 2, getHeight()/2);
	}
	
	public boolean zoomOut(float percent, float pointX, float pointY) {
		if (percent < 0) {
			return zoomIn(-percent, pointX, pointY);
		}
		float scale = getScaleX();
		float factor = 1 - percent / 100;
		float newScale = scale * factor;
		if (newScale >= minScale) {
			setScale(newScale);
			calculatePanLimits();
			
			float newX = pointX * factor;
			float newY = pointY * factor;
			float dx = newX - pointX;
			float dy = newY - pointY;
			
			if (posX - dx >= minX && posX - dx <= maxX) {
				posX -= dx;
			}
			if (posY - dy >= minY && posY - dy <= maxY) {
				posY -= dy;
			}
			
			return true;
		}
		return false;
	}
	
	/**
	 * Visualizes the recoloring of an object on the board.
	 * 
	 * @param recoloredObject
	 *            the object that has been recolored
	 */
	@Override
	public void onObjectRecolored(ColoredBoardObject recoloredObject) {
		// TODO have actor updated
		layout.getActor(recoloredObject);
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
		ColoredAlligatorActor eaterActor = ((ColoredAlligatorActor) layout
				.getActor(eater));
		eaterActor.enterEatingState();
		final List<InternalBoardObject> eatenLst = FlattenTree
				.toList(eatenFamily);

		final float animDuration = 0.2f;

		for (InternalBoardObject eaten : eatenLst) {
			MoveToAction action = new MoveToAction();
			action.setPosition(eaterActor.getX(), eaterActor.getY());
			action.setDuration(animDuration);
			layout.getActor(eaten).addAction(action);
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
					layout.onRemoveSingle(eaten);
				}
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
				List<ActorDelta> removeDeltas = layout.onRemoveSingle(object);
				// TODO
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
	public void onAgedAlligatorVanishes(AgedAlligator alligator,
			int positionInParent) {
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
	public final void onBoardRebuilt(Board board) {
		clearChildren();
		layout = ActorLayoutBuilder.build(board, config);
		for (BoardObjectActor actor : layout) {
			addActor(actor);
		}
		registerLayoutListeners();
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
		List<ActorDelta> removeDeltas = layout.onRemoveSingle(replacedEgg);
		List<ActorDelta> addDeltas = layout.onAdd(bornFamily);
		// TODO
	}

	@Override
	protected void sizeChanged() {
		calculateLimits();
		initializePosition();

		this.removeListener(gestureListener);
		gestureListener = new BoardActorGestureListener();
		this.addListener(gestureListener);
	}

	private void registerLayoutListeners() {
		for (BoardObjectActor child : layout) {
			if (child.getBoardObject() instanceof ColoredBoardObject) {
				ColoredBoardObject o = (ColoredBoardObject) child
						.getBoardObject();
				// if (o.isRecolorable()) {
				child.addListener(new RecolorPopupListener(o));
				// }
			}
		}
	}

	private class RecolorPopupListener extends ClickListener {
		private ColoredBoardObject o;

		public RecolorPopupListener(ColoredBoardObject o) {
			this.o = o;
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			System.out.println("Click");
			// TODO
		}
	}
}
