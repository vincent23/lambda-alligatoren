package de.croggle.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Interpolation;

import de.croggle.AlligatorApp;
import de.croggle.data.AssetManager;

/**
 * 
 * 
 * Code mostly adapted from
 * https://github.com/Matsemann/libgdx-loading-screen/blob
 * /master/Main/src/com/matsemann/libgdxloadingscreen/screen/LoadingScreen.java
 */
public class LoadingScreen extends AbstractScreen {

	private final Screen nextScreen;

	private float percent;
	private NinePatch bar;
	private NinePatch barEmpty;

	public LoadingScreen(AlligatorApp game, Screen nextScreen) {
		super(game);
		this.nextScreen = nextScreen;

		// prepare loading bar graphics without using AssetManager
		Texture barTexture = new Texture(
				Gdx.files.internal("textures/loading-bar.png"));
		Texture barEmptyTexture = new Texture(
				Gdx.files.internal("textures/loading-bar-empty.png"));
		bar = new NinePatch(barTexture, 8, 8, 8, 8);
		barEmpty = new NinePatch(barEmptyTexture, 8, 8, 8, 8);
	}

	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if (AssetManager.getInstance().update()) { // Load some, will return
													// true if done loading
			game.setScreen(nextScreen);
		}

		// Interpolate the percentage to make it more smooth
		percent = Interpolation.linear.apply(percent, AssetManager
				.getInstance().getProgress(), 0.1f);

		// draw loading bar
		game.batch.begin();
		barEmpty.draw(game.batch, screenWidth / 10, screenHeight / 6,
				screenWidth * 8 / 10, screenHeight / 5);
		bar.draw(game.batch, screenWidth / 10, screenHeight / 6, screenWidth
				* 8 * percent / 10, screenHeight / 5);
		game.batch.end();

		// Show the loading screen
		stage.act();
		stage.draw();
	}

	@Override
	public void hide() {

	}

}
