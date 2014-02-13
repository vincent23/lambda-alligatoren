package de.croggle;

import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.croggle.data.persistence.SettingController;
import de.croggle.data.persistence.StatisticController;
import de.croggle.data.persistence.manager.PersistenceManager;
import de.croggle.game.GameController;
import de.croggle.game.achievement.AchievementController;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.level.LevelController;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.game.profile.ProfileController;
import de.croggle.game.sound.SoundController;
import de.croggle.game.sound.SoundHelper;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.screens.AbstractScreen;
import de.croggle.ui.screens.AchievementScreen;
import de.croggle.ui.screens.CreditsScreen;
import de.croggle.ui.screens.LevelPackagesScreen;
import de.croggle.ui.screens.LevelTerminatedScreen;
import de.croggle.ui.screens.LevelsOverviewScreen;
import de.croggle.ui.screens.LoadingScreen;
import de.croggle.ui.screens.MainMenuScreen;
import de.croggle.ui.screens.ProfileSetAvatarScreen;
import de.croggle.ui.screens.ProfileSetNameScreen;
import de.croggle.ui.screens.QuitGameOverlay;
import de.croggle.ui.screens.SelectProfileScreen;
import de.croggle.ui.screens.SettingsScreen;
import de.croggle.ui.screens.SimulationModeScreen;
import de.croggle.ui.screens.StatisticScreen;

/**
 * The central unit controlling the game. Manages the application lifecycle and
 * is responsible for managing screens as well as the minor controllers.
 */
public class AlligatorApp extends Game {

	public static final boolean DEBUG = false;

	private ProfileController profileController;
	private AchievementController achievementController;
	private StatisticController statisticController;
	private SettingController settingController;
	private LevelPackagesController levelPackagesController;
	private SoundController soundController;
	private PersistenceManager persistenceManager;

	private MainMenuScreen mainMenuScreen;
	private LevelPackagesScreen levelPackagesScreen;
	// private LevelsOverviewScreen levelsOverviewScreen;
	// private PlacementModeScreen placementModeScreen;
	// private SimulationModeScreen simulationModeScreen;
	private AchievementScreen achievementScreen;
	private SettingsScreen settingsScreen;
	private StatisticScreen statisticScreen;
	private SelectProfileScreen selectProfileScreen;
	private ProfileSetNameScreen profileSetNameScreen;
	private ProfileSetAvatarScreen profileSetAvatarScreen;
	private CreditsScreen creditsScreen;

	private final Stack<Screen> screenStack;

	public SpriteBatch batch;

	/**
	 * Creates the game using the given context and initializes all controllers
	 * and screens.
	 * 
	 * @param context
	 *            the Android Activity's context
	 */
	public AlligatorApp() {
		screenStack = new Stack<Screen>();
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
	 * Returns the sound controller which is used to play sounds and music.
	 * 
	 * @return the sound controller
	 */
	public SoundController getSoundController() {
		return soundController;
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
		de.croggle.data.AssetManager.initialize();
		StyleHelper.initialize();
		SoundHelper.initialize();
		this.batch = new SpriteBatch();

		// catch android back key
		Gdx.input.setCatchBackKey(true);

		// initialize Controllers
		persistenceManager = new PersistenceManager(this);
		statisticController = new StatisticController(this);
		settingController = new SettingController(this);
		profileController = new ProfileController(this);
		soundController = new SoundController();
		levelPackagesController = new LevelPackagesController(this);

		// Not sure how to initialize those.
		achievementController = new AchievementController(this);
		levelPackagesController = new LevelPackagesController(this);

		// / initialize screens
		mainMenuScreen = new MainMenuScreen(this);
		levelPackagesScreen = new LevelPackagesScreen(this);
		// levelsOverviewScreen = new LevelsOverviewScreen(this, null);
		// placementModeScreen = new PlacementModeScreen(this, null);
		// simulationModeScreen = new SimulationModeScreen(this, null);
		achievementScreen = new AchievementScreen(this);
		settingsScreen = new SettingsScreen(this);
		statisticScreen = new StatisticScreen(this);
		selectProfileScreen = new SelectProfileScreen(this);
		profileSetNameScreen = new ProfileSetNameScreen(this);
		profileSetAvatarScreen = new ProfileSetAvatarScreen(this);
		creditsScreen = new CreditsScreen(this);

		// add onProfileChangeListener
		settingController.addSettingChangeListener(soundController);
		profileController.addProfileChangeListener(settingsScreen);
		profileController.addProfileChangeListener(selectProfileScreen);
		profileController.addProfileChangeListener(mainMenuScreen);
		profileController.addProfileChangeListener(statisticScreen);

		if (profileController.getAllProfiles().isEmpty()) {
			profileSetNameScreen.showBackButton(false);
			setScreen(new LoadingScreen(this, profileSetNameScreen));
		} else {
			setScreen(new LoadingScreen(this, mainMenuScreen));
		}

	}

	/**
	 * Is called after create() is done, means after the asset manager is done
	 * loading. TODO is called by the loading screen, which is ugly
	 */
	public void created() {
		soundController.addToPlaylist("music1.mp3");
		soundController.startPlaylist();

		profileController.loadLastActiveProfile();

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
		super.resume();
		this.setScreen(new LoadingScreen(this, this.getScreen()));
	}

	/**
	 * Is called by the application lifecycle when the game is shut down. Should
	 * dispose everything that was allocated.
	 */
	@Override
	public void dispose() {
		batch.dispose();
		StyleHelper.getInstance().dispose();

		// release catching of back key (no idea if necessary)
		Gdx.input.setCatchBackKey(false);
	}

	public void returnToPreviousScreen() {
		if (screenStack.isEmpty()) {
			setScreen(new QuitGameOverlay(this, getScreen()));
		} else {
			setScreen(screenStack.pop());
		}
	}

	public void showPreviousScreen() {
		Screen previous = screenStack.peek();
		switchScreen();
		setScreen(previous);
	}

	public void showMainMenuScreen(boolean putOnStack) {
		if (putOnStack) {
			switchScreen();
		}
		setScreen(mainMenuScreen);
	}

	public void showLevelPackagesScreen() {
		switchScreen();
		setScreen(levelPackagesScreen);
	}

	public void showLevelOverviewScreen(LevelController levelController) {
		switchScreen();
		AbstractScreen newScreen = new LevelsOverviewScreen(this,
				levelController);
		setScreen(newScreen);
	}

	public void showAchievementScreen() {
		switchScreen();
		setScreen(achievementScreen);
	}

	public void showSettingsScreen(boolean putOnStack) {
		if (putOnStack) {
			switchScreen();
		}
		setScreen(settingsScreen);
	}

	public void showStatisticScreen() {
		switchScreen();
		setScreen(statisticScreen);
	}

	public void showSelectProfileScreen() {
		switchScreen();
		setScreen(selectProfileScreen);
	}

	public void showProfileSetNameScreen(boolean putOnStack) {
		if (putOnStack) {
			switchScreen();
		}
		setScreen(profileSetNameScreen);
	}

	public void showProfileSetAvatarScreen(String name, boolean putOnStack) {
		if (putOnStack) {
			switchScreen();
		}
		profileSetAvatarScreen.setProfileName(name);
		setScreen(profileSetAvatarScreen);
	}

	public void showPlacementModeScreen(GameController gameController) {
		switchScreen();
		setScreen(gameController.createPlacementScreen(this));
	}

	public void showSimulationModeScreen(GameController gameController)
			throws IllegalBoardException {
		switchScreen();
		setScreen(new SimulationModeScreen(this, gameController));
	}

	public void showCreditsScreen() {
		switchScreen();
		setScreen(creditsScreen);
	}

	public void showLevelTerminatedScreen(GameController gameController) {
		switchScreen();
		setScreen(new LevelTerminatedScreen(this, gameController));
	}

	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public ProfileSetNameScreen getProfileSetNameScreen() {
		return profileSetNameScreen;
	}

	public ProfileSetAvatarScreen getProfileSetAvatarScreen() {
		return profileSetAvatarScreen;
	}

	public LevelPackagesScreen getLevelPackagesScreen() {
		return levelPackagesScreen;
	}

	private void switchScreen() {
		if (getScreen() != null) {
			screenStack.push(getScreen());
		}
	}

	/**
	 * Clears the stack of screens used to offer "showPreviousScreen". Used, for
	 * example, if a game was won and returning to any previous screens would be
	 * inconsistent.
	 */
	public void clearScreenStack() {
		screenStack.clear();
	}

	public void clearScreenStackAfterSimulation() {
		screenStack.push(mainMenuScreen);
		screenStack.push(getLevelPackagesScreen());
	}
}
