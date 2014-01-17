package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.StatisticController;
import de.croggle.game.profile.ProfileController;
import de.croggle.ui.StyleHelper;

/**
 * Screen which enables the teacher or parent to control the progress of every
 * user. For reference see ``Pflichtenheft 10.5.11 / Abbildung 20''.
 */
public class StatisticScreen extends AbstractScreen {

	private StatisticController statisticController;
	private ProfileController profileController;

	/**
	 * Creates the screen within which a parent or teacher can control the
	 * player's progress and statistics.
	 * 
	 * @param game
	 *            the backreference to the central game
	 * @param controller
	 *            the statistic controller, which is responsible for the
	 *            statistics
	 */
	public StatisticScreen(AlligatorApp game) {
		super(game);
		statisticController = game.getStatisticController();
		profileController = game.getProfileController();

		fillTable();
	}

	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		Label profiles = new Label("Profiles", helper.getLabelStyle());
		ScrollPane scroll = new ScrollPane(null);

		String[] items = { "profile1", "profile2" };
		SelectBox profileList = new SelectBox(items, helper.getSelectBoxStyle());

		table.pad(30);
		table.add(profiles);
		table.add(profileList).width(300);
		table.row();
		table.add(scroll).expand().colspan(2);
	}
}
