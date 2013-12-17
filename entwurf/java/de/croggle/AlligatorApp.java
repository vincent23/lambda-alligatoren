package de.croggle;

import de.croggle.controller.AchievementController;
import de.croggle.controller.LoadLevelController;
import de.croggle.controller.LevelController;
import de.croggle.controller.LevelPackagesController;
import de.croggle.data.manager.AssetManager;
import de.croggle.data.manager.LocalizationManager;
import de.croggle.data.manager.PersistenceManager;

/**
 * @navassoc 1 - 1 de.croggle.controller.LevelController
 * @navassoc 1 - 1 de.croggle.controller.LevelPackagesController
 * @navassoc 1 - 1 de.croggle.controller.AchievementController
 * @navassoc 1 - 1 de.croggle.data.manager.PersistenceManager
 * @navassoc 1 - 1 de.croggle.data.manager.AssetManager
 * @navassoc 1 - 1 de.croggle.data.manager.LocalizationManager
 * @assoc 1 - * de.croggle.ui.screens.AbstractScreen
 */
public class AlligatorApp extends com.badlogic.gdx.Game {
	private AchievementController achievementController;
	private LevelPackagesController levelPackagesController;
	private LevelController levelController;
	private PersistenceManager persistenceManager;
	private AssetManager assetManager;
	private LocalizationManager localizationMAnager;

	
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
