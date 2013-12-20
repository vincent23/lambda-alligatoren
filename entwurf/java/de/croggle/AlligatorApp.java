package de.croggle;

import de.croggle.controller.SettingController;
import de.croggle.controller.StatisticController;
import de.croggle.data.AssetManager;
import de.croggle.data.LocalizationManager;
import de.croggle.data.persistence.manager.PersistenceManager;
import de.croggle.game.achievement.AchievementController;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.game.profile.ProfileController;
import android.content.Context;

/**
 * @assoc 1 - 1 de.croggle.game.profile.ProfileController
 * @assoc 1 - 1 de.croggle.game.level.LevelPackagesController
 * @assoc 1 - 1 de.croggle.game.achievement.AchievementController
 * @assoc 1 - 1 de.croggle.controller.StatisticController
 * @assoc 1 - 1 de.croggle.data.persistence.manager.PersistenceManager
 * @navassoc 1 - 1 de.croggle.data.AssetManager
 * @navassoc 1 - 1 de.croggle.data.LocalizationManager
 * @assoc 1 - * de.croggle.ui.screens.AbstractScreen
 */
public class AlligatorApp extends com.badlogic.gdx.Game {

	//the android context
	private Context context;

	private ProfileController profileController;
	private AchievementController achievementController;
	private StatisticController statisticController;
	private SettingController settingController;
	private LevelPackagesController levelPackagesController;
	private PersistenceManager persistenceManager;
	private AssetManager assetManager;
	private LocalizationManager localizationManager;

	
	public Context getContext(){
		return null;
	}
	
	public ProfileController getProfileController(){
		return null;
	}
    
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
