package de.croggle;

import de.croggle.game.GameController;
import de.croggle.game.LevelController;
import de.croggle.game.LevelPackagesController;
import de.croggle.game.AchievementController;
import de.croggle.data.PersistenceManager;
import de.croggle.data.ProfileController;
import de.croggle.data.AssetManager;
import de.croggle.data.LocalizationManager;

/**
 * @navassoc 1 - 1 de.croggle.game.GameController
 * @navassoc 1 - 1 de.croggle.game.LevelController
 * @navassoc 1 - 1 de.croggle.game.LevelPackagesController
 * @navassoc 1 - 1 de.croggle.game.AchievementController
 * @navassoc 1 - 1 de.croggle.data.PersistenceManager
 * @navassoc 1 - 1 de.croggle.data.ProfileController
 * @navassoc 1 - 1 de.croggle.data.SettingController
 * @navassoc 1 - 1 de.croggle.data.StatisticController
 * @navassoc 1 - 1 de.croggle.data.AssetManager
 * @navassoc 1 - 1 de.croggle.data.LocalizationManager
 * @assoc 1 - * de.croggle.ui.screens.AbstractScreen
 */
public class AlligatorApp extends com.badlogic.gdx.Game {
	private GameController gameController;
	private AchievementController achievementController;
	private LevelPackagesController levelPackagesController;
	private LevelController levelController;
	private PersistenceManager persistenceManager;
	private AssetManager assetManager;
	private ProfileController profileController;
	private LocalizationManager localizationMAnager;
	
		
	public GameController getGameController() {
		return null;
	}
	
	public LevelPackagesController getLevelPackagesController() {
		return null;
	}
	
	public LevelController getLevelController() {
		return null;
	}
	
	public AchievementController getAchievementController() {
		return null;
	}
	
	public PersistenceManager getPersistenceManager() {
		return null;
	}
	
	public AssetManager getAssetManager() {
		return null;
	}
	
	public ProfileController getProfileController() {
		return null;
	}
	
	public LocalizationManager getLocalizationManager() {
		return null;
	}
	
	@Override
	public void create () {
	}

	@Override
	public void render () {
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
