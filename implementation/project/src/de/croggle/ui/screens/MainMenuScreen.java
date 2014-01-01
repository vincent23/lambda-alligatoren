package de.croggle.ui.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import de.croggle.AlligatorApp;

/**
 * Screen, which shows the central menu one uses to navigate into every other
 * point of the application. For reference see ``Pflichtenheft 10.5.1 /
 * Abbildung 9''.
 */
public class MainMenuScreen extends AbstractScreen {
	
	Texture background;;
	OrthographicCamera camera;
	
	/**
	 * Creates the main menu screen from whom the player can navigate into the
	 * different parts of the app.
	 * 
	 * @param app
	 *            the instance of alligator app, from which everything is
	 *            connected
	 */
	public MainMenuScreen(AlligatorApp app) {
		super(app);
		AssetManager manager = app.getAssetManager();
		TextureLoader.TextureParameter backgroundParams = new TextureLoader.TextureParameter();
//		backgroundParams.minFilter = TextureFilter.MipMapLinearLinear;
//		backgroundParams.magFilter = TextureFilter.MipMapLinearLinear;
		backgroundParams.genMipMaps = true;
		manager.load("textures/swamp.png", Texture.class, backgroundParams);
		manager.finishLoading();
		background = game.getAssetManager().get("textures/swamp.png", Texture.class);
		background.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.MipMapLinearLinear);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 600);
	}
	
	@Override
	public void render(float delta) {
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the background
		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.end();
	}
	
	@Override
	public void dispose() {
		background.dispose();
	}
}
