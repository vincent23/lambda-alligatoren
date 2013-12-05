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
 */
public class AlligatorApp {
	private TimeService timeService;
	private de.croggle.game.GameController gameController;
	private de.croggle.data.PersistenceManager persistenceManager;
	private de.croggle.data.AchievementController achievementController;
	private de.croggle.data.AssetManager assetManager;
	private de.croggle.game.LevelBoxesController levelBoxesController;
	private de.croggle.data.LevelController levelController;
	private de.croggle.data.ProfileController profileController;
}
