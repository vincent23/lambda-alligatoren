package de.croggle;

import de.croggle.controller.SettingController;
import de.croggle.controller.StatisticController;
import de.croggle.data.AssetManager;
import de.croggle.data.LocalizationManager;
import de.croggle.data.persistence.manager.PersistenceManager;
import de.croggle.game.achievement.AchievementController;
import de.croggle.game.level.LevelPackagesController;

/**
 * @navassoc 1 - 1 de.croggle.game.level.LevelPackagesController
 * @navassoc 1 - 1 de.croggle.game.achievement.AchievementController
 * @navassoc 1 - 1 de.croggle.controller.StatisticController
 * @navassoc 1 - 1 de.croggle.data.persistence.manager.PersistenceManager
 * @navassoc 1 - 1 de.croggle.data.AssetManager
 * @navassoc 1 - 1 de.croggle.data.LocalizationManager
 * @assoc 1 - * de.croggle.ui.screens.AbstractScreen
 */
public class AlligatorApp extends com.badlogic.gdx.Game {
	private AchievementController achievementController;
	private StatisticController statisticController;
	private SettingController settingController;
	private LevelPackagesController levelPackagesController;
	private PersistenceManager persistenceManager;
	private AssetManager assetManager;
	private LocalizationManager localizationManager;

    
	public LevelPackagesController getLevelPackagesController() {
		return null;
	}

	public AchievementController getAchievementController() {
		return null;
	}

	public StatisticController getStatisticController() {
		return null;
	}

	public PersistenceManager getPersistenceManager() {
		return null;
	}

	public AssetManager getAssetManager() {
		return null;
	}

	public LocalizationManager getLocalizationManager() {
		return null;
	}
	
	public SettingController getSettingController() {
		return null;
	}

	@Override
	public void create() {
	}

	@Override
	public void render() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
