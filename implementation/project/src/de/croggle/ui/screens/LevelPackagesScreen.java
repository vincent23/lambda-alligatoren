package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.croggle.AlligatorApp;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.ui.PagedScrollPane;
import de.croggle.ui.StyleHelper;

/**
 * Screen, in which one can select the levelpackage. For reference see
 * ``Pflichtenheft 10.5.2 / Abbildung 10''.
 */
public class LevelPackagesScreen extends AbstractScreen {

	LevelPackagesController packagesController;

	/**
	 * Creates the level package overview screen that uses the level package
	 * controller to display the different level packages.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the level package controller
	 */
	public LevelPackagesScreen(AlligatorApp game) {
		super(game);
		packagesController = game.getLevelPackagesController();

		fillTable();
	}

	private void fillTable() {
		PagedScrollPane pager = new PagedScrollPane();

		ImageButton home = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/dummy-icon"));

		home.addListener(new MainMenuClickListener());

		// TODO
		// packagesController.getLevelPackages();

		// just some test stuff
		Image page1 = new Image(StyleHelper.getInstance().getSkin()
				.getDrawable("widgets/dummy-icon"));
		Image page2 = new Image(StyleHelper.getInstance().getSkin()
				.getDrawable("widgets/icon-play"));
		Table table1 = new Table();
		table1.add(page1);
		table1.add(page1);
		table1.debug();
		Table table2 = new Table();
		table2.add(page2);
		table2.add(page2);
		table2.debug();
		pager.addPage(page1);
		pager.addPage(page2);
		pager.addPage(home);

		table.add(home).expandX().left().width(screenWidth / 10);
		table.row();
		table.add(pager).expand().fill();

		table.pad(30);

		pager.setFlingTime(0.1f);
		pager.setPageSpacing(25);
		pager.setWidth(screenWidth * 0.7f);
	}
}
