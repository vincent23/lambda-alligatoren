package de.croggle;

import java.util.List;

import android.content.Context;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.croggle.data.persistence.SettingController;
import de.croggle.data.persistence.StatisticController;
import de.croggle.data.persistence.manager.PersistenceManager;
import de.croggle.game.achievement.AchievementController;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.game.profile.ProfileController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.screens.AbstractScreen;
import de.croggle.ui.screens.MainMenuScreen;

/**
 * The central unit controlling the game. Manages the application lifecycle and
 * is responsible for managing screens as well as the minor controllers.
 */
public class AlligatorApp extends Game {

	// the Android Context
	private final Context context;

	private ProfileController profileController;
	private AchievementController achievementController;
	private StatisticController statisticController;
	private SettingController settingController;
	private LevelPackagesController levelPackagesController;
	private PersistenceManager persistenceManager;
	private List<AbstractScreen> screens;

	public SpriteBatch batch;

	/**
	 * Creates the game using the given context and initializes all controllers
	 * and screens.
	 * 
	 * @param context
	 *            the Android Activity's context
	 */
	public AlligatorApp(Context context) {
		this.context = context;
	}

	/**
	 * Returns the Android Context the game operates in.
	 * 
	 * @return the Android Context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * Returns the profile controller which controls the information about the
	 * currently active profile.
	 * 
	 * @return the profile controller
	 */
	public ProfileController getProfileController() {
		return profileController;
	}

	/**
	 * Returns the level packages controller for level management purposes.
	 * 
	 * @return the level packages controller
	 */
	public LevelPackagesController getLevelPackagesController() {
		return levelPackagesController;
	}

	/**
	 * Returns the achievement controller which holds the information about
	 * achievements associated with the current profile.
	 * 
	 * @return the achievement controller
	 */
	public AchievementController getAchievementController() {
		return achievementController;
	}

	/**
	 * Returns the statistic controller which contains all information about the
	 * statistics of the active profile.
	 * 
	 * @return the statistic controller
	 */
	public StatisticController getStatisticController() {
		return statisticController;
	}

	/**
	 * Returns the persistence manager which is responsible for all database
	 * operations.
	 * 
	 * @return the persistence manager
	 */
	public PersistenceManager getPersistenceManager() {
		return persistenceManager;
	}

	/**
	 * Returns the asset manager which controls all kinds of game media, e.g.
	 * graphics.
	 * 
	 * @return the asset manager
	 */
	public AssetManager getAssetManager() {
		return de.croggle.data.AssetManager.getInstance();
	}

	/**
	 * Returns the setting controller that holds all profile-specific settings.
	 * 
	 * @return the setting controller
	 */
	public SettingController getSettingController() {
		return settingController;
	}

	/**
	 * Is called by the application lifecycle on creation. Does all the
	 * initialization that hasn't been done by the constructor.
	 */
	@Override
	public void create() {
		this.batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	/**
	 * Is called by the application lifecycle repeatedly and should update the
	 * game logic, as well as redraw the user interface.
	 */
	@Override
	public void render() {
		super.render();
	}

	/**
	 * Is called by the application lifecycle on resize.
	 * 
	 * @param width
	 *            the width that the screen will have afterwards.
	 * @param height
	 *            the height that the screen will have afterwards.
	 */
	@Override
	public void resize(int width, int height) {
	}

	/**
	 * Is called by the application lifecycle when the game is paused. Should
	 * save everything that has not been saved yet - such as the level progress
	 * - in case the game is shut down.
	 */
	@Override
	public void pause() {
	}

	/**
	 * Is called by the application lifecycle when the game returns from the
	 * pause state. Should rebuild the game the way it was before pausing (as
	 * far as possible).
	 */
	@Override
	public void resume() {
	}

	/**
	 * Is called by the application lifecycle when the game is shut down. Should
	 * dispose everything that was allocated.
	 */
	@Override
	public void dispose() {
		batch.dispose();
		StyleHelper.getInstance().dispose();
	}
}
