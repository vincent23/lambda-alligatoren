package de.croggle.ui.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;

import de.croggle.game.profile.Profile;

/**
 * An actor for the main menu's profile button. Shows the currently selected
 * profile's name and avatar picture.
 * 
 */
public class ProfileButton extends Actor {
	private final Profile user;

	/**
	 * Creates a new profile button displaying information about the given
	 * profile.
	 * 
	 * @param user
	 *            the user profile to display as currently active
	 */
	public ProfileButton(Profile user) {
		this.user = user;
	}
}
