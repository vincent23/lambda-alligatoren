package de.croggle.ui.screens;

import java.util.List;

import android.util.Log;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;




import de.croggle.AlligatorApp;
import de.croggle.data.persistence.Statistic;
import de.croggle.data.persistence.StatisticController;
import de.croggle.game.profile.OnProfileChangeListener;
import de.croggle.game.profile.Profile;
import de.croggle.game.profile.ProfileController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.actors.PagedScrollPane;

/**
 * Screen which enables the teacher or parent to control the progress of every
 * user. For reference see ``Pflichtenheft 10.5.11 / Abbildung 20''.
 */
public class StatisticScreen extends AbstractScreen implements OnProfileChangeListener {

	private StatisticController statisticController;
	private ProfileController profileController;
	private SelectBox profileList;
	private Table content = new Table();
	
	private TextButton actionsButton;
	private TextButton progressButton;
	private TextButton gameButton;


	/**
	 * Creates the screen within which a parent or teacher can control the
	 * player's progress and statistics.
	 * 
	 * @param game
	 *            the backreference to the central game
	 */
	public StatisticScreen(AlligatorApp game) {
		super(game);
		statisticController = game.getStatisticController();
		profileController = game.getProfileController();
		
		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		
		Label profiles = new Label("Selected profile : ", helper.getLabelStyle());
		String[] items = { "No userers available yet" };
		profileList = new SelectBox(items, helper.getSelectBoxStyle());
		profileList.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
					showProgressStatistic();
			}
		});
		Table table1 = new Table();
		table1.add(profiles).spaceRight(20);
		table1.add(profileList).width(getViewportWidth() / 4);
			
		PagedScrollPane pager = new PagedScrollPane();
		actionsButton = new TextButton("ACTIONS",
				helper.getTextButtonStyle());
		progressButton = new TextButton("PROGRESS",
				helper.getTextButtonStyle());
		gameButton = new TextButton("GAME",
				helper.getTextButtonStyle());
		
		
		MyClickListener listener = new MyClickListener();
		actionsButton.addListener(listener);
		progressButton.addListener(listener);
		gameButton.addListener(listener);
		
		pager.addPage(progressButton);
		pager.addPage(actionsButton);
		pager.addPage(gameButton);
	
	
		pager.setFlingTime(0.9f);
		pager.setPageSpacing(25);
		pager.setWidth(getViewportWidth() * 0.7f);
		
		table.pad(30);
		table.add(table1);
		
		table.row();
		table.add(pager).fillX().top();
		table.row();
	
		
		ScrollPane pane = new ScrollPane(content);
		table.add(pane).expand().top().left().fill();
		table.row().expandY();
		


		
		
	}

	@Override
	public void onProfileChange() {
		List<Profile> profiles = profileController.getAllProfiles();
		String[] profileNames;
		if (profiles.size() == 0) {
			profileNames = new String[] { "No userers available yet" };
		} else {
			profileNames = new String[profiles.size()];
			for (int i = 0; i < profiles.size(); i++) {
				String profileName = profiles.get(i).getName();
				profileNames[i] = profileName;
			}
		}
		profileList.setItems(profileNames);
		
	}
	
	
	private void showActionStatistics() {
		content.clear();
		LabelStyle style = StyleHelper.getInstance().getLabelStyle();
		Statistic statistic = statisticController.getStatistic(profileList.getSelection());
		if (statistic != null) {
			Label recolorings = new Label("Recolorings done : " + statistic.getRecolorings(), style);
			Label resets = new Label("Resets used : " + statistic.getResetsUsed(), style);
			Label hints = new Label("Hints used : " + statistic.getUsedHints(), style);
			content.add(recolorings).height(100).left().padLeft(10);
			content.add().expandX();
			content.row();
			content.add(resets).height(100).left().padLeft(10);
			content.row();
			content.add(hints).height(100).left().padLeft(10);
			content.row();
			content.add().expandY();
		}
	}
	
	private void showProgressStatistic() {
		content.clear();
		LabelStyle style = StyleHelper.getInstance().getLabelStyle();
		Statistic statistic = statisticController.getStatistic(profileList.getSelection());
		if (statistic != null) {
			Label time = new Label("Time played : " + statistic.getPlaytime(), style);
			Label packages = new Label("Packages complete : " + statistic.getPackagesComplete(), style);
			Label levels = new Label("Levels complete : " + statistic.getLevelsComplete(), style);
			content.add(time).height(100).left().padLeft(10);
			content.add().expandX();
			content.row();
			content.add(packages).height(100).left().padLeft(10);
			content.row();
			content.add(levels).height(100).left().padLeft(10);
			content.row();
			content.add().expandY();
		}
		
	}
	
	private void showGameStatistics() {
		content.clear();
		LabelStyle style = StyleHelper.getInstance().getLabelStyle();
		Statistic statistic = statisticController.getStatistic(profileList.getSelection());
		if (statistic != null) {
			Label alligatorsEaten = new Label("Alligators eaten : " + statistic.getAlligatorsEaten(), style);
			Label alligatorsPlaced = new Label("Alligators placed : " + statistic.getAlligatorsPlaced(), style);
			Label eggsHatched = new Label("Eggs hatched : " + statistic.getEggsHatched(), style);
			Label eggsPlaced = new Label("Eggs placed : " + statistic.getEggsPlaced(), style);
					
			content.add(alligatorsEaten).height(100).left().padLeft(10);
			content.add().expandX();
			content.row();
			content.add(alligatorsPlaced).height(100).left().padLeft(10);
			content.row();
			content.add(eggsHatched).height(100).left().padLeft(10);
			content.row();
			content.add(eggsPlaced).height(100).left().padLeft(10);
			content.row();
			
			content.add().expandY();

		}
		
	}
	
	 private class MyClickListener extends ClickListener {
		 
		@Override
		public void clicked(InputEvent event, float x, float y) {
			TextButton source = (TextButton) event.getListenerActor();
			if (source == actionsButton) {
				showActionStatistics();
			} else if (source == gameButton) {
				showGameStatistics();
			} else if (source == progressButton) {
				showProgressStatistic();
			}
		}
	 }
	 
	 @Override
	protected void onShow() {
		showProgressStatistic();
		super.onShow();
	}
	 
	 
}
