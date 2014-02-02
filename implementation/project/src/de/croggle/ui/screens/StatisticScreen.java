package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.Statistic;
import de.croggle.data.persistence.StatisticController;
import de.croggle.game.profile.Profile;
import de.croggle.game.profile.ProfileChangeListener;
import de.croggle.game.profile.ProfileController;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.screens.AbstractScreen.LogicalPredecessorListener;

/**
 * Screen which enables the teacher or parent to control the progress of every
 * user. For reference see ``Pflichtenheft 10.5.11 / Abbildung 20''.
 */
public class StatisticScreen extends AbstractScreen implements
		ProfileChangeListener {

	private StatisticController statisticController;
	private ProfileController profileController;

	private SelectBox profileList;
	private Table content;

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

		content = new Table();

		Label profiles = new Label(_("statistic_screen_selected_profile"),
				helper.getLabelStyle());
		String[] items = { _("statistic_screen_no_users") };
		profileList = new SelectBox(items, helper.getSelectBoxStyle());
		profileList.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				showProgressStatistic();
			}
		});

		TextButtonStyle buttonStyle = helper.getTextButtonStyleSquare();
		actionsButton = new TextButton(_("statistic_tab_actions"), buttonStyle);
		progressButton = new TextButton(_("statistic_tab_progress"),
				buttonStyle);
		gameButton = new TextButton(_("statistic_tab_game"), buttonStyle);
		ImageButton back = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-back"));

		ChangeTabClickListener listener = new ChangeTabClickListener();
		actionsButton.addListener(listener);
		progressButton.addListener(listener);
		gameButton.addListener(listener);
		back.addListener(new LogicalPredecessorListener());

		// set up button group for always having at most one tab checked
		ButtonGroup group = new ButtonGroup(actionsButton, progressButton,
				gameButton);
		group.setMaxCheckCount(1);
		group.setUncheckLast(true);

		Table tabBarTable = new Table();
		tabBarTable.defaults().size(350, 100);
		tabBarTable.add(progressButton);
		tabBarTable.add(actionsButton);
		tabBarTable.add(gameButton);
		ScrollPane tabBar = new ScrollPane(tabBarTable);
		tabBar.setScrollingDisabled(false, true);

		table.pad(30);
		table.add(back).size(100).top().left();
		table.add(profiles).height(70).padRight(20).padLeft(200).right();
		table.add(profileList).height(70).width(300).expandX().left();

		table.row();
		table.add(tabBar).colspan(3).height(100);
		table.row();

		ScrollPane pane = new ScrollPane(content);
		pane.setScrollingDisabled(true, false);
		table.add(pane).expand().fill().colspan(3);
		
		onProfileChange(null);
	}

	@Override
	public void onProfileChange(Profile profile) {
		List<Profile> profiles = profileController.getAllProfiles();
		String[] profileNames;
		if (profiles.size() == 0) {
			profileNames = new String[] { _("statistic_screen_no_users") };
		} else {
			profileNames = new String[profiles.size()];
			for (int i = 0; i < profiles.size(); i++) {
				String profileName = profiles.get(i).getName();
				profileNames[i] = profileName;
			}
		}
		profileList.setItems(profileNames);
		if (profile != null) {
			profileList.setSelection(profile.getName());
			showProgressStatistic();
		}

	}

	private void showActionStatistics() {
		content.clear();
		LabelStyle style = StyleHelper.getInstance().getLabelStyle();
		Statistic statistic = statisticController.getStatistic(profileList
				.getSelection());
		if (statistic != null) {
			Label recolorings = new Label(
					_("statistic_label_action_recolorings")
							+ statistic.getRecolorings(), style);
			Label resets = new Label(_("statistic_label_action_resets")
					+ statistic.getResetsUsed(), style);
			Label hints = new Label(_("statistic_label_action_hints")
					+ statistic.getUsedHints(), style);

			content.defaults().height(100).expandX().left().padLeft(10);
			content.add(recolorings);
			content.row();
			content.add(resets);
			content.row();
			content.add(hints);
		}
	}

	private void showProgressStatistic() {
		content.clear();
		LabelStyle style = StyleHelper.getInstance().getLabelStyle();
		Statistic statistic = statisticController.getStatistic(profileList
				.getSelection());
		if (statistic != null) {
			Label time = new Label(_("statistic_label_progress_time")
					+ statistic.getPlaytime(), style);
			Label packages = new Label(_("statistic_label_progress_packages")
					+ statistic.getPackagesComplete(), style);
			Label levels = new Label(_("statistic_label_progress_levels")
					+ statistic.getLevelsComplete(), style);

			content.defaults().height(100).expandX().left().padLeft(10);
			content.add(time);
			content.row();
			content.add(packages);
			content.row();
			content.add(levels);
		}

	}

	private void showGameStatistics() {
		content.clear();
		LabelStyle style = StyleHelper.getInstance().getLabelStyle();
		Statistic statistic = statisticController.getStatistic(profileList
				.getSelection());
		if (statistic != null) {
			Label alligatorsEaten = new Label(
					_("statistic_label_game_alligators_eaten")
							+ statistic.getAlligatorsEaten(), style);
			Label alligatorsPlaced = new Label(
					_("statistic_label_game_alligators_placed")
							+ statistic.getAlligatorsPlaced(), style);
			Label eggsHatched = new Label(
					_("statistic_label_game_eggs_hatched")
							+ statistic.getEggsHatched(), style);
			Label eggsPlaced = new Label(_("statistic_label_game_eggs_placed")
					+ statistic.getEggsPlaced(), style);

			content.defaults().height(100).expandX().left().padLeft(10);
			content.add(alligatorsEaten);
			content.row();
			content.add(alligatorsPlaced);
			content.row();
			content.add(eggsHatched);
			content.row();
			content.add(eggsPlaced);

		}

	}

	private class ChangeTabClickListener extends ClickListener {

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
