package de.croggle.ui.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.croggle.AlligatorApp;
import de.croggle.ui.StyleHelper;

/**
 * Screen, which shows the central menu one uses to navigate into every other
 * point of the application. For reference see ``Pflichtenheft 10.5.1 /
 * Abbildung 9''.
 */
public class MainMenuScreen extends AbstractScreen {

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

		setBackground("textures/swamp.png");

		// do all the button stuff
		fillTable();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	private void fillTable() {
		StyleHelper.getInstance();
		AssetManager manager = de.croggle.data.AssetManager.getInstance();
		Table profileButtonTable = new Table();
		Table leftTable = new Table();

		ImageButton play = new ImageButton(new TextureRegionDrawable(manager
				.get("textures/pack.atlas", TextureAtlas.class).findRegion(
						"widgets/dummy-icon")));
		// ImageButton stats = new ImageButton(manager.get("widgets/dummy-icon",
		// Drawable.class));
		// ImageButton settings = new ImageButton(manager.get(
		// "widgets/dummy-icon", Drawable.class));
		// ImageButton achievements = new ImageButton(manager.get(
		// "widgets/dummy-icon", Drawable.class));

		leftTable.add(play);

		// TODO this should be a ProfileButton
		profileButtonTable
				.add(new Button(StyleHelper.getInstance().getButtonStyle()))
				.space(20).width(200).height(200);

		table.add(leftTable);
		table.add(profileButtonTable);
	}
}
