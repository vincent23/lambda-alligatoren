package de.croggle;

import java.util.List;

import android.content.Context;
import de.croggle.data.AssetManager;
import de.croggle.data.LocalizationManager;
import de.croggle.data.persistence.SettingController;
import de.croggle.data.persistence.StatisticController;
import de.croggle.data.persistence.manager.PersistenceManager;
import de.croggle.game.achievement.AchievementController;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.game.profile.ProfileController;
import de.croggle.ui.screens.AbstractScreen;

/**
 * The central controlling unit. Receives the application lifecycle and is
 * responsible for managing screens as well as the smaller controllers.
 * 
 * @assoc 1 - 1 de.croggle.game.profile.ProfileController
 * @assoc 1 - 1 de.croggle.game.level.LevelPackagesController
 * @assoc 1 - 1 de.croggle.game.achievement.AchievementController
 * @assoc 1 - 1 de.croggle.data.persistence.StatisticController
 * @assoc 1 - 1 de.croggle.data.persistence.SettingController
 * @assoc 1 - 1 de.croggle.data.persistence.manager.PersistenceManager
 * @navassoc 1 - 1 de.croggle.data.AssetManager
 * @navassoc 1 - 1 de.croggle.data.LocalizationManager
 * @assoc 1 - * de.croggle.ui.screens.AbstractScreen
 */
public class AlligatorApp extends com.badlogic.gdx.Game {

	// the android context
	private Context context;

	private ProfileController profileController;
	private AchievementController achievementController;
	private StatisticController statisticController;
	private SettingController settingController;
	private LevelPackagesController levelPackagesController;
	private PersistenceManager persistenceManager;
	private AssetManager assetManager;
	private LocalizationManager localizationManager;
	private List<AbstractScreen> screens;

	/**
	 * Creates the game with the given context and initializes all controllers
	 * and screens.
	 * 
	 * @param context
	 *            the android Activity's context
	 */
	public AlligatorApp(Context context) {

	}

	/**
	 * Returns the android context the game operates on.
	 * 
	 * @return the android context
	 */
	public Context getContext() {
		return null;
	}

	/**
	 * Returns the profile controller which controls the information about the
	 * currently active profile.
	 * 
	 * @return the profile controller
	 */
	public ProfileController getProfileController() {
		return null;
	}

	/**
	 * Returns the level packages controller for level management purposes.
	 * 
	 * @return the level packages controller
	 */
	public LevelPackagesController getLevelPackagesController() {
		return null;
	}

	/**
	 * Returns the achievement controller which holds the information about
	 * achievements associated with the current profile.
	 * 
	 * @return the achievement controller
	 */
	public AchievementController getAchievementController() {
		return null;
	}

	/**
	 * Returns the statistic controller which contains all information about the
	 * statistics of the active profile.
	 * 
	 * @return the statistic controller
	 */
	public StatisticController getStatisticController() {
		return null;
	}

	/**
	 * Returns the persistence manager which is responsible for all database
	 * operations.
	 * 
	 * @return the persistence manager
	 */
	public PersistenceManager getPersistenceManager() {
		return null;
	}

	/**
	 * Returns the asset manager which controls all kind of game media, e.g.
	 * graphics.
	 * 
	 * @return the asset manager
	 */
	public AssetManager getAssetManager() {
		return null;
	}

	/**
	 * Returns the localization manager which is used for translating strings to
	 * the appropriate language.
	 * 
	 * @return the localization manager
	 */
	public LocalizationManager getLocalizationManager() {
		return null;
	}

	/**
	 * Returns the setting controller that holds all profile specific settings.
	 * 
	 * @return the setting controller
	 */
	public SettingController getSettingController() {
		return null;
	}

	/**
	 * Is called by the application lifecycle on creation. Does all
	 * initialization that hasn't been done by the constructor.
	 */
	@Override
	public void create() {
	}

	/**
	 * Is called by the application lifecycle repeatedly and should update the
	 * game logic.
	 */
	@Override
	public void render() {
	}

	/**
	 * Is called by the application lifecycle on resize.
	 */
	@Override
	public void resize(int width, int height) {
	}

	/**
	 * Is called by the application lifecycle when the game is paused. Should
	 * save everything that has not been saved yet, like the level progress, for
	 * the possibility of the game being shut down.
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
	}
}
