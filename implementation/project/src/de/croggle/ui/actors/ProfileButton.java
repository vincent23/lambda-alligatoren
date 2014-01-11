package de.croggle.ui.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

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
		this.user = user;

		add(user.getName()).row();
		add(new Image(new Texture(user.getPicturePath())));

	}
}
