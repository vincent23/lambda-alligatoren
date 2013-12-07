package de.croggle.data;

import de.croggle.data.media.Animation;
import de.croggle.data.media.BitmapFont;
import de.croggle.data.media.Texture;
import de.croggle.data.media.Sound;
import de.croggle.data.media.Music;

/**
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
	 * @return
	 */
	public Animation getAnimation() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public Image getTexture() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public BitmapFont getFont() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public Sound getSound() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public Music getMusic() {
		return null;
	}
	
}
