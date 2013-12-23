package de.croggle.data;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * This class is responsible for managing the different kinds of assets the apps
 * needs to work flawlessly.
 * 
 * @navassoc 1 - * Animation
 * @navassoc 1 - * BitmapFont
 * @navassoc 1 - * Music
 * @navassoc 1 - * Sound
 * @navassoc 1 - * Texture
 */
public class AssetManager extends com.badlogic.gdx.assets.AssetManager {

	/**
	 * Loads the animation specified by the identifier.
	 * 
	 * @param identifier
	 *            a path to the requested animation, resolvable by the asset
	 *            manager
	 * @return the animation denoted by the given identifier
	 */
	public Animation getAnimation(String identifier) {
		return null;
	}

	/**
	 * Loads the texture specified by the identifier.
	 * 
	 * @param identifier
	 *            a path to the requested texture, resolvable by the asset
	 *            manager
	 * @return the texture denoted by the given identifier
	 */
	public Texture getTexture(String identifier) {
		return null;
	}

	/**
	 * Loads the font specified by the identifier.
	 * 
	 * @param identifier
	 *            a path to the requested bitmap font, resolvable by the asset
	 *            manager
	 * @return the bitmap font denoted by the given identifier
	 */
	public BitmapFont getFont(String identifier) {
		return null;
	}

	/**
	 * Loads the sound specified by the identifier.
	 * 
	 * @param identifier
	 *            a path to the requested sound, resolvable by the asset manager
	 * @return the sound denoted by the given identifier
	 */
	public Sound getSound(String identifier) {
		return null;
	}

	/**
	 * Loads the music specified by the identifier.
	 * 
	 * @param identifier
	 *            a path to the requested music, resolvable by the asset manager
	 * @return the music denoted by the given identifier
	 */
	public Music getMusic(String identifier) {
		return null;
	}

}
