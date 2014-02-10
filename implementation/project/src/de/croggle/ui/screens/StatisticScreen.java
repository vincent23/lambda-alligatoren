package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
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
import de.croggle.ui.actors.SelectBox;

/**
 * Screen which enables the teacher or parent to control the progress of every
 * user. For reference see ``Pflichtenheft 10.5.11 / Abbildung 20''.
 */
public class StatisticScreen extends AbstractScreen implements
		ProfileChangeListener {

	private final StatisticController statisticController;
	private final ProfileController profileController;

	private SelectBox profileList;
	private Table content;

	private enum Category {
		ACTION, PROGRESS, GAME
	}

	private Category lastCategory = Category.PROGRESS;

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
	}

	@Override
	protected void initializeWidgets() {
		super.initializeWidgets();
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
				showCategory(lastCategory);
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
		progressButton.setChecked(true);

		Table tabBarTable = new Table();
		tabBarTable.defaults().size(325, 100);
		tabBarTable.add(progressButton);
		tabBarTable.add(actionsButton);
		tabBarTable.add(gameButton);
		ScrollPane tabBar = new ScrollPane(tabBarTable);
		tabBar.setScrollingDisabled(true, true);

		table.pad(30);
		table.add(back).size(100).top().left();
		table.add(profiles).height(70).padRight(20).padLeft(200).right();
		table.add(profileList).height(40).width(300).expandX().left();

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
		if (areWidgetsInitialized()) {
			profileList.setItems(profileNames);
		}
	}

	private void showCategory(Category category) {
		content.clear();
		LabelStyle style = StyleHelper.getInstance().getLabelStyle();
		Statistic statistic = statisticController.getStatistic(profileList
				.getSelection());
		/*
		 * TODO make this nicer (e.g. special Category class, that can layout
		 * itself)
		 */
		content.defaults().height(100).expandX().left().padLeft(10);
		if (statistic != null) {
			switch (category) {
			case PROGRESS:
				int sec = statistic.getPlaytime();
				int hours = sec / 3600;
				sec = sec % 3600;
				int min = sec / 60;
				sec = sec % 60;

				Label timeText = new Label(_("statistic_label_progress_time"),
						style);
				Label timeVal = new Label(hours + " h  "
						+ String.format("%02d", min) + " min  "
						+ String.format("%02d", sec) + " s", style);

				Label packagesText = new Label(
						_("statistic_label_progress_packages"), style);
				Label packagesVal = new Label(""
						+ statistic.getPackagesComplete(), style);

				Label levelsText = new Label(
						_("statistic_label_progress_levels"), style);
				Label levelsVal = new Label("" + statistic.getLevelsComplete(),
						style);

				content.add(timeText);
				content.add(timeVal);
				content.row();

				content.add(packagesText);
				content.add(packagesVal);
				content.row();

				content.add(levelsText);
				content.add(levelsVal);
				break;

			case ACTION:
				Label recoloringsText = new Label(
						_("statistic_label_action_recolorings"), style);
				Label recoloringsVal = new Label(""
						+ statistic.getRecolorings(), style);

				Label resetsText = new Label(
						_("statistic_label_action_resets"), style);
				Label resetsVal = new Label("" + statistic.getResetsUsed(),
						style);

				Label hintsText = new Label(_("statistic_label_action_hints"),
						style);
				Label hintsVal = new Label("" + statistic.getUsedHints(), style);

				content.add(recoloringsText);
				content.add(recoloringsVal);
				content.row();

				content.add(resetsText);
				content.add(resetsVal);
				content.row();

				content.add(hintsText);
				content.add(hintsVal);
				break;

			case GAME:
				Label alligatorsEatenText = new Label(
						_("statistic_label_game_alligators_eaten"), style);
				Label alligatorsEatenVal = new Label(""
						+ statistic.getAlligatorsEaten(), style);

				Label alligatorsPlacedText = new Label(
						_("statistic_label_game_alligators_placed"), style);
				Label alligatorsPlacedVal = new Label(""
						+ statistic.getAlligatorsPlaced(), style);

				Label eggsHatchedText = new Label(
						_("statistic_label_game_eggs_hatched"), style);
				Label eggsHatchedVal = new Label(""
						+ statistic.getEggsHatched(), style);

				Label eggsPlacedText = new Label(
						_("statistic_label_game_eggs_placed"), style);
				Label eggsPlacedVal = new Label("" + statistic.getEggsPlaced(),
						style);

				content.add(alligatorsEatenText);
				content.add(alligatorsEatenVal);
				content.row();

				content.add(alligatorsPlacedText);
				content.add(alligatorsPlacedVal);
				content.row();

				content.add(eggsHatchedText);
				content.add(eggsHatchedVal);
				content.row();

				content.add(eggsPlacedText);
				content.add(eggsPlacedVal);
				break;
			}
		}
	}

	private class ChangeTabClickListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			TextButton source = (TextButton) event.getListenerActor();
			if (source == actionsButton) {
				lastCategory = Category.ACTION;
				showCategory(Category.ACTION);
			} else if (source == gameButton) {
				lastCategory = Category.GAME;
				showCategory(Category.GAME);
			} else if (source == progressButton) {
				lastCategory = Category.PROGRESS;
				showCategory(Category.PROGRESS);
			}
		}
	}

	@Override
	protected void onShow() {
		showCategory(lastCategory);
	}

}
