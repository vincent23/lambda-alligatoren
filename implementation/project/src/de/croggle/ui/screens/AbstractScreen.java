package de.croggle.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.croggle.AlligatorApp;
import de.croggle.data.AssetManager;

/**
 * Abstract screen, with all the basic things a screen needs.
 */
public abstract class AbstractScreen implements Screen {

	protected AlligatorApp game;
	protected final Stage stage;
	protected final Table table;
	private Texture background;
	private String backgroundPath;
	private final OrthographicCamera camera;
	private boolean widgetsInitialized = false;

	private final InputMultiplexer inputMediator;

	/**
	 * Super constructor for all screens. Initializes everything they share,
	 * e.g. their stage.
	 * 
	 * @param game
	 *            the back reference to the central game
	 */
	public AbstractScreen(AlligatorApp game) {
		this.game = game;
		stage = new Stage(1024, 600, true, game.batch);

		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 600);
		stage.setCamera(camera);

		// make the screen as well as the stage an input processor
		inputMediator = new InputMultiplexer(stage, new BackButtonHandler());

		if (AlligatorApp.DEBUG) {
			table.debug(); // turn on all debug lines (table, cell, and widget)
		}
	}

	/**
	 * Called in order to cause the screen to release all resources held.
	 */
	@Override
	public void dispose() {
		stage.dispose();
		background.dispose();
	}

	/**
	 * Called when this screen should no longer be the game's current screen.
	 */
	@Override
	public void hide() {

	}

	/**
	 * Returns the sceen's camera's viewport width, i.e. the number of virtual
	 * pixels that actors drawn onto this screen will assume the screen has
	 * horizontally.
	 * 
	 * @return the viewport width
	 */
	public float getViewportWidth() {
		return camera.viewportWidth;
	}

	/**
	 * Returns the sceen's camera's viewport height, i.e. the number of virtual
	 * pixels that actors drawn onto this screen will assume the screen has
	 * vertically.
	 * 
	 * @return the viewport height
	 */
	public float getViewportHeight() {
		return camera.viewportHeight;
	}

	/**
	 * Called when this screen is paused. A screen is paused before it is
	 * destroyed, when the user pressed the Home button or e.g. an incoming call
	 * happens.
	 */
	@Override
	public void pause() {

	}

	/**
	 * Called when the screen should render itself.
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the background
		if (backgroundPath != null) {
			game.batch.begin();
			background = game.getAssetManager().get(backgroundPath,
					Texture.class);
			background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			game.batch.draw(background, 0, 0, getViewportWidth(),
					getViewportHeight());
			game.batch.end();
		}

		stage.act(delta);
		stage.draw();
		// draw debugging lines
		Table.drawDebug(stage);
		// we are done with rendering the screen, so alpha is meaningless and
		// should hence be 1.0
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glColorMask(false, false, false, true);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glColorMask(true, true, true, true);
	}

	/**
	 * Called when the application is resized. This can happen at any point
	 * during a non-paused state but will never happen before a call to
	 * create().
	 * 
	 * @param width
	 *            the width, which the newly resized screen will have.
	 * @param height
	 *            the height, which the newly resized screen will have.
	 */
	@Override
	public void resize(int width, int height) {
		stage.setViewport(1024, 600, true);
		camera.update();
	}

	/**
	 * Called in order to move the screen back from its paused state.
	 */
	@Override
	public void resume() {
	}

	/**
	 * Called when this screen should be the game's current screen. The method
	 * is final since it implements default behavior which must not be
	 * overridden. If you want to add code to be called on show, override the
	 * protected method onShow() instead.
	 */
	@Override
	public void show() {
		// if the loading screen has initialized everything, this returns
		// instantly on its own
		AssetManager.getInstance().finishLoading();
		if (!widgetsInitialized) {
			initializeWidgets();
		}
		onShow();

		Gdx.input.setInputProcessor(inputMediator);
	}

	/**
	 * Override this method to
	 */
	protected void onShow() {

	}

	protected void initializeWidgets() {
		widgetsInitialized = true;
	}

	protected boolean areWidgetsInitialized() {
		return widgetsInitialized;
	}

	public void setBackground(String backgroundPath) {
		try {
			// stupid libgdx (docu), calling getFileHandle would already throw
			// an exception
			FileHandle h = Gdx.files.internal(backgroundPath);
			if (!h.exists()) {
				System.err.println("Libgdx promise not fulfilled");
				throw new GdxRuntimeException("Background not found");
			}
		} catch (GdxRuntimeException ex) {
			System.err.println("Couldn't load background \"" + backgroundPath
					+ "\". Falling back to standard.");
			setBackground("textures/swamp.png");
			return;
		}
		AssetManager manager = de.croggle.data.AssetManager.getInstance();
		TextureLoader.TextureParameter backgroundParams = new TextureLoader.TextureParameter();
		backgroundParams.genMipMaps = true;

		manager.load(backgroundPath, Texture.class, backgroundParams);
		this.backgroundPath = backgroundPath;

	}

	/**
	 * Override this method if you want to use the LogicalPredecessorListener
	 * and set the respective predecessor screen in it. On default, the
	 * previously shown screen is used.
	 */
	protected void showLogicalPredecessor() {
		game.showPreviousScreen();
	}

	/**
	 * an input processor mainly saves us implementing every method of
	 * InputProcessor in the screen
	 */
	private class BackButtonHandler extends InputAdapter {
		@Override
		public boolean keyUp(int keycode) {
			if (keycode == Keys.BACK) {
				game.returnToPreviousScreen();
				return true;
			}
			return false;
		}

	}

	/**
	 * Listener to return to a logical predecessor screen, e.g. if a back button
	 * is pressed. To define the logical predecessor of a screen, you need to
	 * overwrite the getLogicalPredecessor method of the screen. This procedure
	 * allows to delay the definition and instantiation of the predecessor
	 * screen up until the point where the listener is invoked.
	 */
	protected class LogicalPredecessorListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.getSoundController().playSound("buttonSound1.mp3");
			showLogicalPredecessor();
		}
	}

	// click listener for buttons that change to the main menu
	protected class MainMenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showMainMenuScreen(true);
		}
	}

	// click listener for buttons that change to the level packages screen
	protected class PackagesScreenClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showLevelPackagesScreen();
		}
	}

	// guess what
	protected class AchievementScreenClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showAchievementScreen();
		}
	}

	protected class SettingsScreenClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showSettingsScreen(true);
		}
	}

	protected class StatisticScreenClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showStatisticScreen();
		}
	}

	protected class SelectProfileScreenClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showSelectProfileScreen();
		}
	}

	protected class ProfileSetNameScreenClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showProfileSetNameScreen(true);
		}
	}

	protected class CreditsScreenClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showCreditsScreen();
		}
	}
}
