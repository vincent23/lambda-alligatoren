package de.croggle.ui.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.croggle.AlligatorApp;

/**
 * Abstract screen, with all the basic things a screen needs.
 */
public abstract class AbstractScreen implements Screen {

	private AlligatorApp game;
	private Stage stage;
	private Table table;

	/**
	 * Called in order to cause the screen to release all resources held.
	 */
	public void dispose() {

	}

	/**
	 * Called when this screen should no longer be the game's current screen.
	 */
	public void hide() {

	}

	/**
	 * Called when this screen is paused. A screen is paused before it is
	 * destroyed, when the user pressed the Home button or an incoming call
	 * happend
	 */
	public void pause() {

	}

	/**
	 * Called when the screen should render itself.
	 */
	public void render(float delta) {

	}

	/**
	 * Called in order to cause the screen to resize itself into the given size.
	 * 
	 * @param width
	 *            the width, which the newly resized screen will have.
	 * @param height
	 *            the height, which the newly resized screen will have.
	 */
	public void resize(int width, int height) {

	}

	/**
	 * Called in order to move the screen back from its paused state
	 */
	public void resume() {

	}

	/**
	 * Called when this screen should be the game's current screen.
	 */
	public void show() {

	}
}
