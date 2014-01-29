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
			currentTable.row();
			for( int i = 0; i < achievement.getNumberOfStages(); i++) {
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
				if ( i  % 7 == 6) {
					currentTable.row();
				}
				
				currentTable.add(achievementButton);
				
			}
			currentTable.row();
			Log.d("AchievementScreen", "Umbruch wegen neuem Achievement");
		}
		

		// add listeners
		back.addListener(new LogicalPredecessorListener());

		table.defaults().width(500).height(100).space(10);
		table.pad(30);
		table.add(achievements);
		table.row();
		ScrollPane scrollPane = new ScrollPane(currentTable);
		table.add(scrollPane);
		
		
		
	}
}
