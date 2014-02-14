package de.croggle.ui.screens;

import static de.croggle.backends.BackendHelper.getAssetDirPath;
import static de.croggle.data.LocalizationHelper._;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.Profile;
import de.croggle.game.profile.ProfileChangeListener;
import de.croggle.game.profile.ProfileController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.NotificationDialog;
import de.croggle.ui.actors.ProfileButton;

/**
 * Screen, which shows the central menu one uses to navigate into every other
 * point of the application. For reference see ``Pflichtenheft 10.5.1 /
 * Abbildung 9''.
 */
public class MainMenuScreen extends AbstractScreen implements
		ProfileChangeListener {

	private final ProfileController profileController;

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

		setBackground(getAssetDirPath() + "textures/swamp.png");
	}

	@Override
	protected void initializeWidgets() {
		super.initializeWidgets();
		fillTable();
	}

	@Override
	public void onProfileChange(Profile profile) {
		if (areWidgetsInitialized()) {
			table.clearChildren();
			fillTable();
		}
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();

		Table profileButtonTable = new Table();
		Table leftTable = new Table();

		ImageButton title = new ImageButton(helper.getDrawable("widgets/title"));
		// ImageButton credits = new ImageButton(
		// helper.getImageButtonStyleRound("widgets/icon-hint"));
		ImageButton play = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-play"));
		ImageButton stats = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-stats"));
		ImageButton settings = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-settings"));
		ImageButton achievements = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-trophy"));

		ProfileButton profileButton = new ProfileButton(
				profileController.getCurrentProfile());

		// add listeners
		title.addListener(new CreditsScreenClickListener());
		// credits.addListener(new CreditsScreenClickListener());
		play.addListener(new PlayClickListener());
		stats.addListener(new StatisticScreenClickListener());
		settings.addListener(new SettingsScreenClickListener());
		achievements.addListener(new AchievementScreenClickListener());
		profileButton.addListener(new SelectProfileScreenClickListener());

		play.getImageCell().pad(20);

		leftTable.pad(30);
		leftTable.defaults().size(100);
		// leftTable.add(credits).left();
		leftTable.add(title).colspan(3).size(700, 150);
		leftTable.row();
		leftTable.add(play).expandY().colspan(3).size(200);
		leftTable.row();
		leftTable.add(settings).bottom().space(20);
		leftTable.add(stats).bottom().space(20);
		leftTable.add(achievements).right().bottom().expandX().size(150);

		profileButtonTable.add(profileButton).padRight(30).width(300)
				.height(400);

		table.add(leftTable).expand().fill();
		table.add(profileButtonTable);
	}

	private class PlayClickListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (game.getProfileController().getCurrentProfile() == null) {
				NotificationDialog dialog = new NotificationDialog(
						_("warning_no_profile_selected"));
				dialog.show(stage);
			} else {
				game.showLevelPackagesScreen();
			}
		}
	}
}
