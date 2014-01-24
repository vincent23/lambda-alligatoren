package de.croggle.ui.screens;

import java.io.IOException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.croggle.AlligatorApp;
import de.croggle.game.level.LevelPackage;
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
				.getImageButtonStyleRound("widgets/icon-back"));

		home.addListener(new MainMenuClickListener());

		for (LevelPackage pack : packagesController.getLevelPackages()) {
			Table pageTable = new Table();
			Image levelImage;

			try {
				levelImage = new Image(new Texture(pack.getDesign()));
			} catch (GdxRuntimeException ex) {
				levelImage = new Image(new Texture("textures/swamp.png"));
			}

			levelImage.addListener(new OpenPackageListener(pack
					.getLevelPackageId()));

			pageTable.add(levelImage).height(440);
			pager.addPage(pageTable);
		}

		// test scrolling
		Table page2 = new Table();
		Image image2 = new Image(new Texture("textures/swamp.png"));
		page2.add(image2).height(400);
		pager.addPage(page2);
		Table page3 = new Table();
		Image image3 = new Image(new Texture("textures/swamp.png"));
		page3.add(image3).height(440);
		pager.addPage(page3);

		table.add(home).expandX().left().size(100);
		table.row();
		table.add(pager).expand().fill();

		table.pad(30);

		pager.setFlingTime(0.3f);
		pager.setPageSpacing(75);
		pager.setWidth(screenWidth * 0.7f);
		pager.setScrollingDisabled(false, true);
	}

	private class OpenPackageListener extends ClickListener {

		private int packageId;

		public OpenPackageListener(int packageId) {
			this.packageId = packageId;
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO remove catch block when possible
			try {
				game.showLevelOverviewScreen(LevelPackagesScreen.this,
						packagesController.getLevelController(packageId));
			} catch (IOException ex) {

			}
		}
	}
}
