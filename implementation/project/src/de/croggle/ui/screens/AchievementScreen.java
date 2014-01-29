package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.croggle.AlligatorApp;
import de.croggle.game.achievement.Achievement;
import de.croggle.game.achievement.AchievementController;
import de.croggle.ui.StyleHelper;

/**
 * Screen listing the achievements, both achieved and unachieved, in a sorted
 * way. For reference see ``Pflichtenheft 10.5.8 / Abbildung 17''.
 */
public class AchievementScreen extends AbstractScreen {

	private ScrollPane scrollPane;
	private Table currentTable;
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
		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		ImageButton back = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-back"));
		Label achievements = new Label(_("screen_title_achievements"),
				helper.getLabelStyle());
		ImageButton next = new ImageButton(helper.getDrawable("widgets/icon-next"));
		ImageButton previous = new ImageButton(helper.getDrawable("widgets/icon-back"));
		
		List<Achievement> availableAchievements = achievementController.getAvailableAchievements();
		currentTable = new Table();
		for( Achievement achievement : availableAchievements) {
			for( int i = 1; i < achievement.getNumberOfStages(); i++) {
				ImageButton achievementButton;
				try {
					if (i <= achievement.getIndex()) {
						achievementButton = new ImageButton(helper.getDrawable(achievement.getEmblemPathachieved(i)));
					}
					else {
						achievementButton = new ImageButton(helper.getDrawable(achievement.getEmblemPathnotachieved(i)));
					}
				} catch(IllegalArgumentException e) {
					achievementButton = new ImageButton(helper.getDrawable("widgets/dummy-icon"));
				} catch(GdxRuntimeException e) {
					achievementButton = new ImageButton(helper.getDrawable("widgets/dummy-icon"));
				}
				if ( (i - 1)  % 5 == 4) {
					currentTable.row();
				}
				
				currentTable.add(achievementButton);
				Log.d("AchievementScreen", "Number of Stages: "+ achievement.getNumberOfStages());
				
			}
			currentTable.row();
			Log.d("AchievementScreen", "Umbruch wegen neuem Achievement");
		}
		

		// add listeners
		back.addListener(new LogicalPredecessorListener());

		scrollPane = new ScrollPane(currentTable);
		scrollPane.setWidth(1000);
		table.defaults().width(1000).height(800).space(50);
		table.add(scrollPane);
		table.pad(30);

		
		
		
	}
}
