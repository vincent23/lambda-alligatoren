package de.croggle.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.croggle.AlligatorApp;

/**
 * Abstract screen, with all the basic things a screen needs.
 */
public abstract class AbstractScreen implements Screen {

	protected AlligatorApp game;
	protected final Stage stage;
	protected final Table table;
	private Texture background;
	private OrthographicCamera camera;

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

		Gdx.input.setInputProcessor(stage);

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
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

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
	 * Called when this screen should be the game's current screen.
	 */
	public void show() {
		
	}

	public void setBackground(String backgroundPath) {
		AssetManager manager = de.croggle.data.AssetManager.getInstance();
		TextureLoader.TextureParameter backgroundParams = new TextureLoader.TextureParameter();
		// backgroundParams.minFilter = TextureFilter.MipMapLinearLinear;
		// backgroundParams.magFilter = TextureFilter.MipMapLinearLinear;
		backgroundParams.genMipMaps = true;

		manager.load(backgroundPath, Texture.class, backgroundParams);
		manager.finishLoading();
		background = game.getAssetManager().get(backgroundPath, Texture.class);
		background.setFilter(TextureFilter.MipMapLinearLinear,
				TextureFilter.MipMapLinearLinear);
	}
}
