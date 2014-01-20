package de.croggle.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
	private OrthographicCamera camera;
	private boolean assetsLoaded = false;

	protected int screenWidth;
	protected int screenHeight;

	/**
	 * Superconstructor for all screens. Initializes everything they share, e.g.
	 * their stage.
	 * 
	 * @param game
	 *            the backreference to the central game
	 */
	public AbstractScreen(AlligatorApp game) {
		this.game = game;
		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 600);

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		table.debug(); // turn on all debug lines (table, cell, and widget)

	}

	/**
	 * Called in order to cause the screen to release all resources held.
	 */
	public void dispose() {
		stage.dispose();
		background.dispose();
	}

	/**
	 * Called when this screen should no longer be the game's current screen.
	 */
	public void hide() {

	}

	/**
	 * Called when this screen is paused. A screen is paused before it is
	 * destroyed, when the user pressed the Home button or e.g. an incoming call
	 * happens.
	 */
	public void pause() {

	}

	/**
	 * Called when the screen should render itself.
	 */
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the background
		if (background != null) {
			game.batch.begin();
			game.batch.draw(background, 0, 0);
			game.batch.end();
		}

		stage.act(delta);
		stage.draw();
		// draw debugging lines
		Table.drawDebug(stage);
		// we are done with rendering the screen, so alpha is meaningless and should hence be 1.0
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
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	/**
	 * Called in order to move the screen back from its paused state.
	 */
	public void resume() {
	}

	/**
	 * Called when this screen should be the game's current screen. The method
	 * is final since it implements default behavior which must not be
	 * overridden. If you want to add code to be called on show, override the
	 * protected method onShow() instead.
	 */
	public final void show() {
		if (!assetsLoaded()) {
			AssetManager.getInstance().finishLoading();
			this.setAssetsLoaded(true);
		}
		onShow();

		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * Override this method to
	 */
	protected void onShow() {

	}

	public void setBackground(String backgroundPath) {
		AssetManager manager = de.croggle.data.AssetManager.getInstance();
		TextureLoader.TextureParameter backgroundParams = new TextureLoader.TextureParameter();
		backgroundParams.genMipMaps = true;

		manager.load(backgroundPath, Texture.class, backgroundParams);
		manager.finishLoading();
		background = game.getAssetManager().get(backgroundPath, Texture.class);
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	/**
	 * Tells, whether this screen's asset dependencies have been loaded
	 * externally (e.g. by a {@link LoadingScreen}) or whether the screen has to
	 * finish loading them itself.
	 * 
	 * @return true if all assets have been loaded before
	 */
	public boolean assetsLoaded() {
		return assetsLoaded;
	}

	/**
	 * Sets this screen's asset dependencies' loading status.
	 * 
	 * @param assetsLoaded
	 *            true, if all assets have been loaded already, false otherwise.
	 */
	public void setAssetsLoaded(boolean assetsLoaded) {
		this.assetsLoaded = assetsLoaded;
	}

	// click listener for buttons that change to the main menu
	protected class MainMenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showMainMenuScreen();
		}
	}

	// click listener for buttons that change to the level packages screen
	protected class PackagesScreenClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showLevelPackagesScreen();
		}
	}
}
