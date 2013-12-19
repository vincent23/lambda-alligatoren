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
	 * @param identifier A path resolvable by the assetmanager to the requested Animation
	 * @return The Animation denoted by the given identifier
	 */
	public Animation getAnimation(String identifier) {
		return null;
	}
	
	/**
	 * @param identifier A path resolvable by the assetmanager to the requested Texture
	 * @return The Texture denoted by the given identifier
	 */
	public Texture getTexture(String identifier) {
		return null;
	}
	
	/**
	 * @param identifier A path resolvable by the assetmanager to the requested BitmapFont
	 * @return The BitmapFont denoted by the given identifier
	 */
	public BitmapFont getFont(String identifier) {
		return null;
	}
	
	/**
	 * @param identifier A path resolvable by the assetmanager to the requested Sound
	 * @return The Sound denoted by the given identifier
	 */
	public Sound getSound(String identifier) {
		return null;
	}
	
	/**
	 * @param identifier A path resolvable by the assetmanager to the requested Music
	 * @return The Music denoted by the given identifier
	 */
	public Music getMusic(String identifier) {
		return null;
	}
	
}
