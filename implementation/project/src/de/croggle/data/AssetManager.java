package de.croggle.data;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

import de.croggle.game.Color;
import de.croggle.util.PatternBuilder;

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
		patterns = new Pixmap[Color.MAX_COLORS];
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
		int n = 0;
		patterns[n++] = PatternBuilder.generateHorizontalLines(8, 4);
		patterns[n++] = PatternBuilder.generateVerticalLines(8, 4);
		patterns[n++] = PatternBuilder.generateCheckerboard(16, 8);
		patterns[n++] = PatternBuilder.generateCircle(256, false);
		patterns[n++] = PatternBuilder.generateRhombus(256, 160, 200, false);
		patterns[n++] = PatternBuilder.generateCircle(256, true);
		patterns[n++] = PatternBuilder.generateRhombus(256, 160, 200, true);
		patterns[n++] = PatternBuilder.generateFilled(1);
		patterns[n++] = PatternBuilder.generateTriangleStrip(256, 1);
		for (int i = n; i < patterns.length; i++) {
			patterns[i] = PatternBuilder.generateHorizontalLines(8, 4);
		}
	}

	public Texture getColorTexture(Color c) {
		if (c.equals(Color.uncolored())) {
			return new Texture(uncoloredColor);
		}
		return new Texture(colors[c.getId()]);
	}

	public Texture getPatternTexture(Color c) {
		if (c.equals(Color.uncolored())) {
			return new Texture(uncoloredColor);
		}
		final Texture texture = new Texture(patterns[c.getId()], true);
		// apparently texture size has to be a power of two for this to work
		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		texture.setFilter(TextureFilter.MipMapLinearLinear,
				TextureFilter.MipMapLinearLinear);
		return texture;
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
