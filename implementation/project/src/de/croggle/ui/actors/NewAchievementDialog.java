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
		Label message = new Label(_("title_new_achievement"),
				helper.getBlackLabelStyle());

		Image icon;
		try {
			icon = new Image(helper.getDrawable(achievement
					.getEmblemPathachieved(index)));
		} catch (IllegalArgumentException ex) {
			icon = new Image(helper.getDrawable("widgets/icon-trophy"));
		} catch (GdxRuntimeException ex) {
			icon = new Image(helper.getDrawable("widgets/icon-trophy"));
		}
		Label description = new Label(achievement.getDescription(index),
				helper.getBlackLabelStyle());
		ImageButton next = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-play"));

		clear();
		if (isNew) {
			add(message).pad(30).row();
		}
		add(icon).size(300).pad(0, 100, 0, 100).row();
		add(description).pad(30).row();

		if (isNew) {
			add(next);
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
