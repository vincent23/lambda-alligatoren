package de.croggle.game.sound;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.audio.Sound;

import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingChangeListener;

/**
 * This class is used to play music pieces and sound effects.
 * 
 */
public class SoundController implements SettingChangeListener {

	private float volumeMusic = 0.5f;
	private float volumeEffects = 0.5f;

	private Music mMusic;

	private List<Music> mPlaylist = new ArrayList<Music>();
	private int mPosition = 0;

	/**
	 * Play a song located in "assets/sound/music"
	 * 
	 * @param name
	 *            the name of the song ("music.mp3).
	 * @param loop
	 *            weather the music should loop or not
	 */
	public void playMusic(String name, boolean loop) {
		mMusic = SoundHelper.getInstance().getMusic(name);
		mMusic.setLooping(loop);
		mMusic.setVolume(volumeMusic);
		mMusic.play();

	}

	/**
	 * Adds the song located in "assets/sound/music" at the end of the
	 * play list.
	 * 
	 * @param name
	 *            the name of the song
	 */

	public void addToPlaylist(String name) {
		Music music = SoundHelper.getInstance().getMusic(name);
		music.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(Music music) {
				mPosition = (mPosition + 1) % mPlaylist.size();
				mMusic = mPlaylist.get(mPosition);
				mMusic.setVolume(volumeMusic);
				mMusic.play();

			}
		});
		mPlaylist.add(music);

	}

	/**
	 * Stops the music
	 */
	public void stopMusic() {
		if (mMusic != null) {
			mMusic.stop();
		}
	}

	/**
	 * Resumes the music
	 */
	public void resumeMusic() {
		if (mMusic != null && !mMusic.isPlaying()) {
			mMusic.play();
		}
	}

	/**
	 * Starts the play list.
	 */
	public void startPlaylist() {
		if (!mPlaylist.isEmpty()) {
			stopMusic();
			mPosition = 0;
			mMusic = mPlaylist.get(mPosition);
			mMusic.setVolume(volumeMusic);
			mMusic.play();
		}
	}

	/**
	 * Deletes the play list.
	 */
	public void deletePlaylist() {
		stopMusic();
		mMusic = null;
		mPlaylist.clear();
	}

	/**
	 * Play a sound located in "assets/sound/effects"
	 * 
	 * @param name
	 *            the name of the sound ("effect.mp3).
	 */
	public void playSound(String name) {
		Sound sound = SoundHelper.getInstance().getEffect(name);
		sound.play(volumeEffects);

	}

	@Override
	public void onSettingChange(Setting setting) {
		this.volumeEffects = setting.getVolumeEffects();
		this.volumeMusic = setting.getVolumeMusic();
		if (mMusic != null) {
			mMusic.setVolume(volumeMusic);
		}

	}

}
