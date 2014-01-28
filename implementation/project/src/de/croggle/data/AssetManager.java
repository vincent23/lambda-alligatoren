package de.croggle.data;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import de.croggle.game.Color;

/**
 * Proxy class to enforce singleton pattern on libgdx' AssetManager. Needs to be
 * initialized before first usage using {@link AssetManager#initialize()}. This
 * is due to Android behaviour, leading to static variables surviving app
 * restarts, while the AssetManager's managed assets are lost.
 */
public class AssetManager extends com.badlogic.gdx.assets.AssetManager {
	/*
	 * Static initialization will cause problems on app resume static {
	 * assetManager = new AssetManager(); }
	 */
	private static AssetManager assetManager;
	private Pixmap uncoloredColor;
	private Pixmap uncoloredPattern;
	private Pixmap[] colors;
	private Pixmap[] patterns;

	private AssetManager() {
		colors = new Pixmap[Color.MAX_COLORS];
		buildColors();
		buildPatterns();
	}

	private void buildColors() {
		uncoloredColor = new Pixmap(1, 1, Pixmap.Format.RGB888);
		uncoloredColor.setColor(com.badlogic.gdx.graphics.Color.WHITE);
		uncoloredColor.fill();
		
		com.badlogic.gdx.graphics.Color[] reps = Color.getRepresentations();
		for (int i = 0; i < colors.length; i++) {
			colors[i] = new Pixmap(1, 1, Pixmap.Format.RGB888);
			colors[i].setColor(reps[i]);
			colors[i].fill();
		}
	}

	private void buildPatterns() {

	}

	public Texture getColorTexture(Color c) {
		if (c.equals(Color.uncolored())) {
			return new Texture(uncoloredColor);
		}
		return new Texture(colors[c.getId()]);
	}

	public Texture getPatternTexture(Color c) {
		// TODO implement buildPatterns first
		throw new UnsupportedOperationException("Not implemented yet");
		/*
		if (c.equals(Color.uncolored())) {
			return new Texture(uncolordPattern);
		}
		return new Texture(patterns[c.getId()]);
		*/
	}

	@Override
	public void dispose() {
		super.dispose();
		uncoloredColor.dispose();
		uncoloredPattern.dispose();
		for (Pixmap c : colors) {
			c.dispose();
		}
		for (Pixmap p : patterns) {
			p.dispose();
		}
	}

	/**
	 * Returns the globally unique (singleton) libgdx
	 * {@link com.badlogic.gdx.assets.AssetManager} used to manage all assets.
	 * 
	 * @return the app's {@link com.badlogic.gdx.assets.AssetManager}
	 */
	public static AssetManager getInstance() {
		return assetManager;
	}

	public static void initialize() {
		AssetManager.assetManager = new AssetManager();
	}
}
