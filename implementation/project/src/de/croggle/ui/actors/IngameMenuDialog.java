package de.croggle.ui.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.game.GameController;
import de.croggle.ui.StyleHelper;

public class IngameMenuDialog extends Dialog {

	private AlligatorApp game;
	private GameController gameController;

	public IngameMenuDialog(AlligatorApp game, GameController gameController) {
		super("", StyleHelper.getInstance().getDialogStyle());
		this.game = game;
		this.gameController = gameController;

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();

		ImageTextButton continueGame = new ImageTextButton("Continue",
				helper.getImageTextButtonStyle("widgets/icon-next"));
		ImageTextButton reset = new ImageTextButton("Reset",
				helper.getImageTextButtonStyle("widgets/dummy-icon"));
		ImageTextButton settings = new ImageTextButton("Settings",
				helper.getImageTextButtonStyle("widgets/icon-settings"));
		ImageTextButton levelOverview = new ImageTextButton("Level Overview",
				helper.getImageTextButtonStyle("widgets/dummy-icon"));
		ImageTextButton achievements = new ImageTextButton("Achievements",
				helper.getImageTextButtonStyle("widgets/icon-trophy"));
		ImageTextButton mainMenu = new ImageTextButton("Main Menu",
				helper.getImageTextButtonStyle("widgets/dummy-icon"));

		// hard code ALL the stuff!
		continueGame.getImageCell().padRight(50).padLeft(100).size(80);
		reset.getImageCell().padRight(50).padLeft(100).size(80);
		settings.getImageCell().padRight(50).padLeft(100).size(80);
		levelOverview.getImageCell().padRight(50).padLeft(100).size(80);
		achievements.getImageCell().padRight(50).padLeft(100).size(80);
		mainMenu.getImageCell().padRight(50).padLeft(100).size(80);

		continueGame.getLabelCell().expandX().left();
		reset.getLabelCell().expandX().left();
		settings.getLabelCell().expandX().left();
		levelOverview.getLabelCell().expandX().left();
		achievements.getLabelCell().expandX().left();
		mainMenu.getLabelCell().expandX().left();

		// add listeners
		continueGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				hide();
			}
		});
		reset.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO notification?
				gameController.reset();
				game.showPlacementModeScreen(gameController);
				hide();
			}
		});
		settings.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.showSettingsScreen();
			}
		});
		levelOverview.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.showLevelPackagesScreen();
			}
		});
		achievements.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.showAchievementScreen();
			}
		});
		mainMenu.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.showMainMenuScreen();
			}
		});

		clear();

		defaults().width(500).height(90).padLeft(100).padRight(100);
		add(continueGame).padTop(20).row();
		add(reset).row();
		add(settings).row();
		add(levelOverview).row();
		add(achievements).row();
		add(mainMenu).padBottom(20).row();
	}
}
