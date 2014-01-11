package de.croggle.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
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
	}

	/**
	 * Called in order to cause the screen to release all resources held.
	 */
	public void dispose() {
		stage.dispose();
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
		stage.draw();
		stage.act(delta);
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
}
