package de.croggle.game.profile;

/**
 * A listener that is to be implemented by all classes that need to be updated if the profile is changed.
 */
public interface ProfileChangeListener {

	/**
	 * Process the new profile.
	 * @param profile the new profile
	 */
	public void onProfileChange(Profile profile);
}
