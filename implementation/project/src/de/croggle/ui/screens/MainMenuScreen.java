package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.Profile;
import de.croggle.game.profile.ProfileChangeListener;
import de.croggle.game.profile.ProfileController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.ProfileButton;

/**
 * Screen, which shows the central menu one uses to navigate into every other
 * point of the application. For reference see ``Pflichtenheft 10.5.1 /
 * Abbildung 9''.
 */
public class MainMenuScreen extends AbstractScreen implements
		ProfileChangeListener {

	private ProfileController profileController;

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
		profileController = app.getProfileController();

		setBackground("textures/swamp.png");

		// do all the button stuff
		fillTable();
	}

	@Override
	public void onProfileChange(Profile profile) {
		table.clearChildren();
		fillTable();
	}

	private void fillTable() {
		Table profileButtonTable = new Table();
		Table leftTable = new Table();

		ImageButton credits = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/icon-hint"));
		ImageButton play = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/icon-play"));
		ImageButton stats = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/icon-stats"));
		ImageButton settings = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/icon-settings"));
		ImageButton achievements = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/icon-trophy"));

		ProfileButton profileButton = new ProfileButton(
				profileController.getCurrentProfile());

		// add listeners
		credits.addListener(new CreditsScreenClickListener());
		play.addListener(new PackagesScreenClickListener());
		stats.addListener(new StatisticScreenClickListener());
		settings.addListener(new SettingsScreenClickListener());
		achievements.addListener(new AchievementScreenClickListener());
		profileButton.addListener(new SelectProfileScreenClickListener());

		play.getImageCell().pad(20);

		leftTable.pad(30);
		leftTable.defaults().size(100);
		leftTable.add(credits).left();
		leftTable.row();
		leftTable.add(play).expandY().colspan(3).size(200);
		leftTable.row();
		leftTable.add(settings).bottom().space(20);
		leftTable.add(stats).bottom().space(20);
		leftTable.add(achievements).right().bottom().expandX().size(150);

		profileButtonTable.add(profileButton).padRight(50).width(300)
				.height(400);

		table.add(leftTable).expand().fill();
		table.add(profileButtonTable);
	}
}
