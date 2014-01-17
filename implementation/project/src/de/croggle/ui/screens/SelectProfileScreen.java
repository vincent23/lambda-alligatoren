package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.Profile;
import de.croggle.game.profile.ProfileController;
import de.croggle.ui.StyleHelper;

/**
 * Screen within which the player can change the name of his profile. For
 * reference see ``Pflichtenheft 10.5.12 / Abbildung 21''.
 */
public class SelectProfileScreen extends AbstractScreen {

	private ProfileController profileController;

	/**
	 * Creates the screen that is shown to the player while changing his
	 * profile.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the profile controller, which is responsible for the profiles
	 */
	public SelectProfileScreen(AlligatorApp game) {
		super(game);
		profileController = game.getProfileController();

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		ImageButton newProfile = new ImageButton(
				helper.getImageButtonStyle("widgets/dummy-icon"));

		for (Profile profile : profileController.getAllProfiles()) {
			TextButton profileButton = new TextButton(profile.getName(),
					helper.getTextButtonStyle());
			table.add(profileButton).width(300).uniform();
		}
		table.add(newProfile).uniform();
	}
}
