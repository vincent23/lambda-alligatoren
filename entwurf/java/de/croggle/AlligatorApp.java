package de.croggle;

/**
 * @navassoc 1 - 1 TimeService
 * @navassoc 1 - 1 de.croggle.game.GameController
 * @navassoc 1 - 1 de.croggle.game.LevelController
 * @navassoc 1 - 1 de.croggle.data.PersistenceManager
 * @navassoc 1 - 1 de.croggle.data.AchievementController
 * @navassoc 1 - 1 de.croggle.data.AssetManager
 * @navassoc 1 - 1 de.croggle.game.LevelBoxesController
 * @navassoc 1 - 1 de.croggle.data.DataManager
 * @navassoc 1 - 1 de.croggle.data.LocalizationManager
 */
public class AlligatorApp {
	private TimeService timeService;
	private de.croggle.game.GameController gameController;
	private de.croggle.game.AchievementController achievementController;
	private de.croggle.game.LevelBoxesController levelBoxesController;
	private de.croggle.game.LevelController levelController;
	private de.croggle.data.PersistenceManager persistenceManager;
	private de.croggle.data.AssetManager assetManager;
	private de.croggle.data.ProfileController profileController;
	private de.croggle.data.LocalizationManager localizationMAnager;
	
	public TimeService getTimeService () {
		return null;
	}
	
	public de.croggle.game.GameController getGameController() {
		return null;
	}
	
	public de.croggle.game.LevelBoxesController getLevelBoxesController() {
		return null;
	}
	
	public de.croggle.game.LevelController getLevelController() {
		return null;
	}
	
	public de.croggle.game.AchievementController getAchievementController() {
		return null;
	}
	
	public de.croggle.data.PersistenceManager getPersistenceManager() {
		return null;
	}
	
	public de.croggle.data.AssetManager getAssetManager() {
		return null;
	}
	
	public de.croggle.data.ProfileController getProfileController() {
		return null;
	}
	
	public de.croggle.data.LocalizationManager getLocalizationManager() {
		return null;
	}
}
