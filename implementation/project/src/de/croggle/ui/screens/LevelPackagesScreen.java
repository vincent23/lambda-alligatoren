package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.croggle.AlligatorApp;
import de.croggle.game.level.LevelPackage;
import de.croggle.game.level.LevelPackagesController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.MaskedImage;
import de.croggle.ui.actors.PagedScrollPane;

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
	}

	@Override
	protected void initializeWidgets() {
		super.initializeWidgets();
		fillTable();
	}

	private void fillTable() {
		PagedScrollPane pager = new PagedScrollPane();

		ImageButton home = new ImageButton(StyleHelper.getInstance()
				.getImageButtonStyleRound("widgets/icon-back"));

		home.addListener(new LogicalPredecessorListener());

		for (LevelPackage pack : packagesController.getLevelPackages()) {
			Table pageTable = new Table();
			MaskedImage levelImage;

			try {
				levelImage = new MaskedImage(pack.getDesign(),
						"textures/package-mask.png");
			} catch (GdxRuntimeException ex) {
				levelImage = new MaskedImage("textures/swamp.png",
						"textures/package-mask.png");
			}

			levelImage.addListener(new OpenPackageListener(pack
					.getLevelPackageId()));

			pageTable.add(levelImage).height(440);
			pager.addPage(pageTable);
		}

		table.add(home).expandX().left().size(100);
		table.row();
		table.add(pager).expand().fill();

		table.pad(30);

		pager.setFlingTime(0.3f);
		pager.setPageSpacing(75);
		pager.setWidth(getViewportWidth() * 0.7f);
		pager.setScrollingDisabled(false, true);
	}

	private class OpenPackageListener extends ClickListener {

		private final int packageId;

		public OpenPackageListener(int packageId) {
			this.packageId = packageId;
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.showLevelOverviewScreen(packagesController
					.getLevelController(packageId));
		}
	}

	@Override
	protected void showLogicalPredecessor() {
		game.showMainMenuScreen(true);
	}
}
