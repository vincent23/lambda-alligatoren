package de.croggle.ui.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import de.croggle.game.profile.Profile;
import de.croggle.ui.StyleHelper;

/**
 * An actor for the main menu's profile button. Shows the currently selected
 * profile's name and avatar picture.
 * 
 */
public class ProfileButton extends Button {
	private final Profile user;

	/**
	 * Creates a new profile button displaying information about the given
	 * profile.
	 * 
	 * @param user
	 *            the user profile to display as currently active
	 */
	public ProfileButton(Profile user) {
		super(StyleHelper.getInstance().getButtonStyle());
		StyleHelper helper = StyleHelper.getInstance();

		this.user = user;

		if (user != null) {
			Label userName = new Label(user.getName(), helper.getLabelStyle(50));
			userName.setWrap(true);
			userName.setAlignment(Align.center);

			ImageButton avatar = new ImageButton(helper.getDrawable(user
					.getPicturePath()));

			add(userName).width(280).pad(30);
			row();
			add(avatar).fill().expand();
			layout();
		}
	}
}
