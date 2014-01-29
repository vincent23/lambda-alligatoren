package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingChangeListener;
import de.croggle.game.ColorController;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.event.BoardEventListener;

/**
 * An actor used for representing a whole board, i.e. an alligator
 * constellation.
 */
public class BoardActor extends Group implements SettingChangeListener {

	private ActorLayout layout;

	private final ActorLayoutConfiguration config;
	private final BoardActorBoardEventListener boardEventListener;
	private float posX;
	private float posY;
	private final WorldPane world;
	private boolean colorBlind;

	private BoardActorGestureListener gestureListener;

	/**
	 * Creates a new BoardActor. The actor layout of the board's representation
	 * will be created using the given {@link ActorLayoutConfiguration}.
	 * 
	 * @param b
	 *            the board this {@link BoardActor} will represent
	 * @param config
	 *            an {@link ActorLayoutConfiguration} used for creating the
	 *            actor layout
	 */
	public BoardActor(Board b, ActorLayoutConfiguration config) {
		this.config = config;
		colorBlind = config.isColorBlindEnabled();
		this.world = new WorldPane(this);
		super.addActor(world);
		boardEventListener = new BoardActorBoardEventListener(this);
		boardEventListener.onBoardRebuilt(b);

		
		initializePosition();
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

	public void draw(SpriteBatch batch, float parentAlpha) {
		if (clipBegin()) {
			super.draw(batch, parentAlpha);
			clipEnd();
		}
	}

	private void initializePosition() {
		final float offsetLeft = 0;
		final float offsetTop = 30;
		
		// have the tree displayed horizontally centered and with its top at the
		// upper edge
		ActorLayoutStatistics stats = layout.getLayoutStatistics();
		Vector2 orig = config.getTreeOrigin();
		float zoom = getZoom();
		// TODO don't think, this is generally correct
		float treeMidX = orig.x + stats.getWidthMap().get(layout.getBoard())
				/ 2;
		float treeTop = orig.y;
		posX = -(treeMidX - getWidth() * zoom / 2) + offsetLeft;
		posY = getHeight() * zoom - treeTop - offsetTop;
	}
	
	float getZoom() {
		return world.getScaleX();
	}
	
	public boolean zoomIn(float percent, float pointX, float pointY) {
		return gestureListener.zoomIn(percent, pointX, pointY);
	}
	
	public boolean zoomIn(float percent) {
		return zoomIn(percent, getWidth() / 2, getHeight() / 2);
	}
	
	public boolean zoomOut(float percent, float pointX, float pointY) {
		return gestureListener.zoomOut(percent, pointX, pointY);
	}

	public boolean zoomOut(float percent) {
		return zoomOut(percent, getWidth() / 2, getHeight() / 2);
	}

	@Override
	protected void sizeChanged() {
		world.syncBounds();
		
		this.removeListener(gestureListener);
		gestureListener = new BoardActorGestureListener(this);
		this.addListener(gestureListener);
		
		gestureListener.calculateLimits();
		initializePosition();
	}

	void registerLayoutListeners() {
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
			System.out.println("Click: " + x + ", " + y);
			// TODO
		}
	}

	ActorLayout getLayout() {
		return layout;
	}

	void setLayout(ActorLayout layout) {
		this.layout = layout;
	}

	WorldPane getWorld() {
		return world;
	}

	ActorLayoutConfiguration getLayoutConfiguration() {
		return config;
	}
	
	float getWorldX() {
		return posX;
	}
	
	void setWorldX(float x) {
		posX = x;
	}
	
	float getWorldY() {
		return posY;
	}
	
	void setWorldY(float y) {
		posY = y;
	}

	/**
	 * 
	 * @return a {@link BoardEventListener} to provide a means for this
	 *         {@link BoardActor} to be updated if the represented {@link Board}
	 *         changes.
	 */
	public BoardEventListener getBoardEventListener() {
		return boardEventListener;
	}

	public void setColorBlindEnabled(boolean enabled) {
		if (enabled == colorBlind) {
			return;
		} else {
			config.setColorBlindEnabled(enabled);
			colorBlind = enabled;
			for (Actor actor : world.getChildren()) {
				if (actor instanceof ColoredBoardObjectActor) {
					((ColoredBoardObjectActor) actor)
							.setColorBlindEnabled(enabled);
				}
			}
		}
	}

	public boolean getColorBlindEnabled() {
		return colorBlind;
	}

	@Override
	public void onSettingChange(Setting setting) {
		if (setting.isColorblindEnabled() != colorBlind) {
			setColorBlindEnabled(setting.isColorblindEnabled());
		}
	}

	// stuff inherited from Group that is not necessary
	/**
	 * @deprecated
	 */
	public void addActor(Actor actor) {
	}

	/**
	 * @deprecated
	 */
	public void addActorAfter(Actor a, Actor b) {
	}

	/**
	 * @deprecated
	 */
	public void addActorAt(int index, Actor actor) {
	}

	/**
	 * @deprecated
	 */
	public void addActorBefore(Actor a, Actor b) {
	}

	/**
	 * @deprecated
	 */
	public void clearChildren() {

	}

	/**
	 * @deprecated
	 */
	public boolean removeActor(Actor a) {
		return false;
	}

	/**
	 * @deprecated
	 */
	public boolean swapActor(Actor a, Actor b) {
		return false;
	}

	/**
	 * @deprecated
	 */
	public boolean swapActor(int x, int y) {
		return false;
	}
}
