package de.croggle.data;

/**
 * Proxy class to enforce singleton pattern on libgdx' AssetManager. Will be
 * automatically initialized with a new AssetManager (standard constructor).
 */
public class AssetManager extends com.badlogic.gdx.assets.AssetManager {
	static {
		assetManager = new AssetManager();
	}
	private static final AssetManager assetManager;

	private AssetManager() {
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
}
