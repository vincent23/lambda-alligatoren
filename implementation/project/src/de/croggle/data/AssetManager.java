package de.croggle.data;

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

	public static void initialize() {
		AssetManager.assetManager = new AssetManager();
	}
}
