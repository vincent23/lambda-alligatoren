package de.croggle.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Interpolation;

import de.croggle.AlligatorApp;
import de.croggle.data.AssetManager;

/**
 * 
 * 
 * Code mostly adapted from https://github.com/Matsemann/libgdx-loading-screen/blob/master/Main/src/com/matsemann/libgdxloadingscreen/screen/LoadingScreen.java
 */
public class LoadingScreen extends AbstractScreen {
	
	private final Screen nextScreen;
	
	private float percent;

	public LoadingScreen(AlligatorApp game, Screen nextScreen) {
		super(game);
		this.nextScreen = nextScreen;
	}

    @Override
    public void resize(int width, int height) {
       
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        if (AssetManager.getInstance().update()) { // Load some, will return true if done loading
            if (Gdx.input.isTouched()) { // If the screen is touched after the game is done loading, go to the next screen
                game.setScreen(nextScreen);
            }
        }

        // Interpolate the percentage to make it more smooth
        percent = Interpolation.linear.apply(percent, AssetManager.getInstance().getProgress(), 0.1f);

        // Show the loading screen
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        
    }

}
