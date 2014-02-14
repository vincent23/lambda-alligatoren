package de.croggle.game.sound;

import static de.croggle.backends.BackendHelper.getAssetDirPath;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import de.croggle.data.AssetManager;

/**
 * Singleton to manage sounds.
 * 
 */
public class SoundHelper {

	private static SoundHelper instance;

	private static final String pathMusic = getAssetDirPath() + "sound/music";
	private static final String pathEffects = getAssetDirPath()
			+ "sound/effects";

	private final Map<String, Sound> effects = new HashMap<String, Sound>();
	private final Map<String, Music> music = new HashMap<String, Music>();

	private SoundHelper() {
		AssetManager manager = AssetManager.getInstance();
		FileHandle folder = Gdx.files.internal(pathMusic);
		for (FileHandle entry : folder.list()) {
			manager.load(pathMusic + "/" + entry.name(), Music.class);
		}
		folder = Gdx.files.internal(pathEffects);
		for (FileHandle entry : folder.list()) {
			manager.load(pathEffects + "/" + entry.name(), Sound.class);
		}
	}

	/**
	 * Returns a sound that is stored in "assets/sound/music".
	 * 
	 * @param name
	 *            the name of the effect
	 * @return the music
	 */
	public Music getMusic(String name) {
		return music.get(name);
	}

	/**
	 * Returns a sound that is stored in "assets/sound/effects".
	 * 
	 * @param name
	 *            the name of the effect
	 * @return the sound
	 */
	public Sound getEffect(String name) {
		return effects.get(name);
	}

	/**
	 * Gets the current instance.
	 * 
	 * @return the current instance
	 */
	public static SoundHelper getInstance() {
		if (instance == null) {
			throw new IllegalStateException(
					"SoundHelper must be initialized before first usage");
		}
		if (instance.effects.size() == 0 || instance.music.size() == 0) {
			AssetManager manager = AssetManager.getInstance();
			manager.finishLoading();
			FileHandle folder = Gdx.files.internal(pathMusic);
			for (FileHandle entry : folder.list()) {
				instance.music.put(entry.name(), manager.get(pathMusic + "/"
						+ entry.name(), Music.class));
			}
			folder = Gdx.files.internal(pathEffects);
			for (FileHandle entry : folder.list()) {
				instance.effects.put(entry.name(), manager.get(pathEffects
						+ "/" + entry.name(), Sound.class));
			}
		}
		return instance;
	}

	/**
	 * Creates a new SoundHelper that will from now on be returned by
	 * getInstance
	 */
	public static void initialize() {
		instance = new SoundHelper();
	}

}
