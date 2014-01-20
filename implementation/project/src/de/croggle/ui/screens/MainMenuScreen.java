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
				.getImageButtonStyleRound("widgets/icon-play"));
		ImageButton stats = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton settings = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/dummy-icon"));
		ImageButton achievements = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/dummy-icon"));
		// TODO this should be a ProfileButton
		Button profileButton = new Button(StyleHelper.getInstance()
				.getButtonStyle());

		// add listeners
		play.addListener(new PackagesScreenClickListener());
		stats.addListener(new StatisticScreenClickListener());
		settings.addListener(new SettingsScreenClickListener());
		achievements.addListener(new AchievementScreenClickListener());

		play.getImageCell().pad(20);

		leftTable.pad(30);
		leftTable.defaults().size(100);
		leftTable.add(play).expandY().colspan(3).size(200);
		leftTable.row();
		leftTable.add(settings).bottom().space(20);
		leftTable.add(stats).bottom().space(20);
		leftTable.add(achievements).right().bottom().expandX().size(150);

		profileButtonTable.add(profileButton).padRight(50).size(200);

		table.add(leftTable).expand().fill();
		table.add(profileButtonTable);
	}
}
