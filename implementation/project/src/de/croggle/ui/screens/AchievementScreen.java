package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.croggle.AlligatorApp;
import de.croggle.game.achievement.Achievement;
import de.croggle.game.achievement.AchievementController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.NewAchievementDialog;
import de.croggle.ui.actors.NotificationDialog;

/**
 * Screen listing the achievements, both achieved and unachieved, in a sorted
 * way. For reference see ``Pflichtenheft 10.5.8 / Abbildung 17''.
 */
public class AchievementScreen extends AbstractScreen {

	private static final int ICONS_PER_ROW = 5;

	private ScrollPane scrollPane;
	private Table achievementTable = new Table();
	ArrayList<Table> block;
	private AchievementController achievementController;

	/**
	 * Creates the achievement overview screen that uses the given achievement
	 * controller to display the current achievement progress.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param achievment
	 *            the achievement controller
	 */
	public AchievementScreen(AlligatorApp game) {
		super(game);
		achievementController = game.getAchievementController();
		block = new ArrayList<Table>();
	}

	@Override
	protected void initializeWidgets() {
		super.initializeWidgets();
		fillTable();
	}
	

		@Override
		protected void onShow() {
			displayAchievements();
			super.onShow();
		}

		private void displayAchievements() {
			achievementTable.clear();
			StyleHelper helper = StyleHelper.getInstance();

			List<Achievement> availableAchievements = achievementController
					.getAvailableAchievements();
		
		
			
			for (final Achievement achievement : availableAchievements) {
				for (int i = 1; i < achievement.getNumberOfStages(); i++) {
					ImageButton achievementButton;
				
					try {
						if (i <= achievement.getIndex()) {
							achievementButton = new ImageButton(
									helper.getDrawable(achievement
											.getEmblemPathAchieved(i)));
							achievementButton
									.addListener(new AchievementDetailViewListener(
											achievement, i));
						} else if (i == achievement.getNumberOfStages() - 1) {
							achievementButton = new ImageButton(
									helper.getDrawable(achievement
											.getEmblemPathNotAchieved(i)));
							final int index = i;
							achievementButton.addListener(new ClickListener() {
								@Override
								public void clicked(InputEvent event, float x,
										float y) {
									Dialog dialog = new NotificationDialog(
											_("achievement_dialog_last") 
													+ "???");
									dialog.show(stage);
								}
							});

						} else {
							achievementButton = new ImageButton(
									helper.getDrawable(achievement
											.getEmblemPathNotAchieved(i)));
							final int index = i;
							achievementButton.addListener(new ClickListener() {
								@Override
								public void clicked(InputEvent event, float x,
										float y) {
									Dialog dialog = new NotificationDialog(
											_("achievement_dialog_standard")
													+ achievement
															.getDescription(index));
									dialog.show(stage);
								}
							});
						}
					} catch (IllegalArgumentException e) {
						achievementButton = new ImageButton(
								helper.getDrawable("widgets/button"));
					} catch (GdxRuntimeException e) {
						achievementButton = new ImageButton(
								helper.getDrawable("widgets/button"));
					}

					achievementTable.add(achievementButton).size(120).space(50);
					if ((i - 1) % ICONS_PER_ROW == ICONS_PER_ROW - 1) {
						achievementTable.row();
					}

				}
				achievementTable.row();
			}
		}

		private void fillTable() {
			StyleHelper helper = StyleHelper.getInstance();
			ImageButton back = new ImageButton(
					helper.getImageButtonStyleRound("widgets/icon-back"));
			Label achievements = new Label(_("screen_title_achievements"),
					helper.getLabelStyle(50));
		
			back.addListener(new LogicalPredecessorListener());
			
			displayAchievements();

			scrollPane = new ScrollPane(achievementTable);
			table.add(back).size(100).spaceRight(30);
			table.add(achievements).height(100).expandX().left();
			table.row();
			table.add(scrollPane).expand().fill().colspan(2);
			table.pad(30, 30, 0, 30);


	}


	

	private class AchievementDetailViewListener extends ClickListener {

		Achievement achievement;
		int index;

		public AchievementDetailViewListener(Achievement achievement, int index) {
			this.achievement = achievement;
			this.index = index;
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			Dialog dialog = new NewAchievementDialog(achievement, index, false);
			dialog.show(stage);
		}
	}
}
