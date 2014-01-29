package de.croggle.ui.screens;

import static de.croggle.data.LocalizationHelper._;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import de.croggle.AlligatorApp;
import de.croggle.data.LocalizationHelper;
import de.croggle.ui.StyleHelper;
import de.croggle.ui.screens.AbstractScreen.LogicalPredecessorListener;

public class CreditsScreen extends AbstractScreen {
	
	private static final String credits = LocalizationHelper.getLocalizedString("app_credits");
	private Table content = new Table();

	public CreditsScreen(AlligatorApp game) {
		super(game);
		
		fillTable();
	}
	
	private void fillTable() {
		StyleHelper helper = StyleHelper.getInstance();
		ImageButton back = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-back"));
		
		ScrollPane pane = new ScrollPane(content);
		pane.setScrollingDisabled(true, false);
		Label label = new Label(credits, helper.getLabelStyle());
		label.setAlignment(Align.left, Align.center);
		content.add(label);
		
		table.add(back).top().left().size(100);
		table.row();
		table.add(pane).expand().top().left().fill();
		
		// add listeners
		back.addListener(new LogicalPredecessorListener());
	}

}
