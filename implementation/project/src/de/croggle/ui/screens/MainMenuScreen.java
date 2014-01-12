package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

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
		Table profileButtonTable = new Table();
		Table leftTable = new Table();

		ImageButton play = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton stats = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton settings = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton achievements = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/dummy-icon"));
		Button profileButton = new Button(StyleHelper.getInstance()
				.getButtonStyle());

		leftTable.add(play).expandY().colspan(3);
		leftTable.row();
		leftTable.add(settings).pad(30).left().bottom();
		leftTable.add(stats).pad(30).left().bottom();
		leftTable.add(achievements).pad(30).right().bottom().expandX();

		// TODO this should be a ProfileButton
		profileButtonTable.add(profileButton).pad(50).width(200).height(200);

		table.add(leftTable).height(screenHeight).expand().fill();
		table.add(profileButtonTable).height(screenHeight);

		leftTable.debug();
	}
}
