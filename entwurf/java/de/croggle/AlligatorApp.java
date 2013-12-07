package de.croggle;

import de.croggle.game.GameController;
import de.croggle.game.LevelController;
import de.croggle.game.LevelBoxesController;
import de.croggle.game.AchievementController;
import de.croggle.data.PersistenceManager;
import de.croggle.data.ProfileController;
import de.croggle.data.AssetManager;
import de.croggle.data.DataManager;
import de.croggle.data.LocalizationManager;

/**
 * @navassoc 1 - 1 TimeService
 * @navassoc 1 - 1 de.croggle.game.GameController
 * @navassoc 1 - 1 de.croggle.game.LevelController
 * @navassoc 1 - 1 de.croggle.game.LevelBoxesController
 * @navassoc 1 - 1 de.croggle.game.AchievementController
 * @navassoc 1 - 1 de.croggle.data.PersistenceManager
 * @navassoc 1 - 1 de.croggle.data.ProfileController
 * @navassoc 1 - 1 de.croggle.data.AssetManager
 * @navassoc 1 - 1 de.croggle.data.DataManager
 * @navassoc 1 - 1 de.croggle.data.LocalizationManager
 * 
 * TODO: extends should be implments since com.badlogic.gdx.ApplicationListener is an interface
 */
public class AlligatorApp extends com.badlogic.gdx.ApplicationListener {
	private TimeService timeService;
	private GameController gameController;
	private AchievementController achievementController;
	private LevelBoxesController levelBoxesController;
	private LevelController levelController;
	private PersistenceManager persistenceManager;
	private AssetManager assetManager;
	private ProfileController profileController;
	private LocalizationManager localizationMAnager;
	
	public TimeService getTimeService () {
		return null;
	}
	
	public GameController getGameController() {
		return null;
	}
	
	public LevelBoxesController getLevelBoxesController() {
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
