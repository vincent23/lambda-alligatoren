package de.croggle.data;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * This class is responsible for managing the different kinds of assets the apps needs to work flawless.
 * 
 * @navassoc 1 - * Animation
 * @navassoc 1 - * BitmapFont
 * @navassoc 1 - * Music
 * @navassoc 1 - * Sound
 * @navassoc 1 - * Texture
 */
public class AssetManager extends com.badlogic.gdx.assets.AssetManager {
	
	/**
	 * 
	 * 
	 * @param identifier a path resolvable by the assetmanager to the requested Animation
	 * @return the animation denoted by the given identifier
	 */
	public Animation getAnimation(String identifier) {
		return null;
	}
	
	/**
	 * @param identifier a path resolvable by the assetmanager to the requested Texture
	 * @return the texture denoted by the given identifier
	 */
	public Texture getTexture(String identifier) {
		return null;
	}
	
	/**
	 * @param identifier a path resolvable by the assetmanager to the requested BitmapFont
	 * @return the bitmapFont denoted by the given identifier
	 */
	public BitmapFont getFont(String identifier) {
		return null;
	}
	
	/**
	 * @param identifier a path resolvable by the assetmanager to the requested Sound
	 * @return the sound denoted by the given identifier
	 */
	public Sound getSound(String identifier) {
		return null;
	}
	
	/**
	 * @param identifier a path resolvable by the assetmanager to the requested Music
	 * @return the music denoted by the given identifier
	 */
	public Music getMusic(String identifier) {
		return null;
	}
	
}
