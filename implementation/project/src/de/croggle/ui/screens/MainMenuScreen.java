package de.croggle.ui.screens;

import com.badlogic.gdx.graphics.Texture;

import de.croggle.AlligatorApp;

/**
 * Screen, which shows the central menu one uses to navigate into every other
 * point of the application. For reference see ``Pflichtenheft 10.5.1 /
 * Abbildung 9''.
 */
public class MainMenuScreen extends AbstractScreen {
	
	Texture background;;
	
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
	}
	
	@Override
	public void render(float delta) {
		
	}
}
