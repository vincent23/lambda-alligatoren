package de.croggle.ui.actors;

import static de.croggle.data.LocalizationHelper._;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.croggle.game.achievement.Achievement;
import de.croggle.ui.StyleHelper;

public class NewAchievementDialog extends Dialog {

	public NewAchievementDialog(Achievement achievement, int index,
			boolean isNew) {
		super("", StyleHelper.getInstance().getDialogStyle());

		StyleHelper helper = StyleHelper.getInstance();

		setBackground(helper.getDrawable("widgets/background-blue"));

		Label message = new Label(_("title_new_achievement"),
				helper.getBlackLabelStyle());

		Image icon;
		try {
			icon = new Image(helper.getDrawable(achievement
					.getEmblemPathAchieved(index)));
		} catch (IllegalArgumentException ex) {
			icon = new Image(helper.getDrawable("widgets/icon-trophy"));
		} catch (GdxRuntimeException ex) {
			icon = new Image(helper.getDrawable("widgets/icon-trophy"));
		}
		Label description = new Label(achievement.getDescription(index),
				helper.getBlackLabelStyle());
		ImageButton next = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-next"));

		clear();
		if (isNew) {
			add(message).height(75).padTop(10).row();
		}
		padTop(40);
		add(icon).size(200).pad(0, 200, 0, 200).row();
		add(description).height(75).padTop(10).row();

		if (isNew) {
			add(next).size(100).padBottom(10).padRight(10).right();
			next.addListener(new DisposeListener());
		} else {
			this.addListener(new DisposeListener());
		}
	}

	private class DisposeListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			NewAchievementDialog.this.hide();
		}
	}

}
