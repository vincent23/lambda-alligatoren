package de.croggle.ui.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.ui.StyleHelper;

public class IngameMenuDialog extends Dialog {

	private AlligatorApp game;

	public IngameMenuDialog(AlligatorApp game) {
		super("", StyleHelper.getInstance().getDialogStyle());
		this.game = game;

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();

		ImageTextButton continueGame = new ImageTextButton("Continue",
				helper.getImageTextButtonStyle("widgets/dummy-icon"));
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
		continueGame.getImageCell().padRight(30).padLeft(150);
		reset.getImageCell().padRight(30).padLeft(150);
		settings.getImageCell().padRight(30).padLeft(150);
		levelOverview.getImageCell().padRight(30).padLeft(150);
		achievements.getImageCell().padRight(30).padLeft(150);
		mainMenu.getImageCell().padRight(30).padLeft(150);

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

		clear();

		defaults().width(500).height(70).padLeft(100).padRight(100);
		add(continueGame).padTop(50).row();
		add(reset).row();
		add(settings).row();
		add(levelOverview).row();
		add(achievements).row();
		add(mainMenu).padBottom(50).row();
	}
}
