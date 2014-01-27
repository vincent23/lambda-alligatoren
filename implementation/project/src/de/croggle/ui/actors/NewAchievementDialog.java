package de.croggle.ui.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.game.achievement.Achievement;
import de.croggle.ui.StyleHelper;

public class NewAchievementDialog extends Dialog {

	public NewAchievementDialog(Achievement achievement, int index,
			boolean hasCloseButton) {
		super("", StyleHelper.getInstance().getDialogStyle());

		StyleHelper helper = StyleHelper.getInstance();
		Label message = new Label("You got a new achievement!",
				helper.getLabelStyle());
		Image icon = new Image(helper.getDrawable(achievement
				.getEmblemPath(index)));
		Label description = new Label(achievement.getDescription(index),
				helper.getLabelStyle());
		ImageButton next = new ImageButton(
				helper.getImageButtonStyleRound("widgets/icon-play"));

		add(message).row();
		add(icon).row();
		add(description).row();

		if (hasCloseButton) {
			add(next);
			next.addListener(new DisposeListener());
		} else {
			this.addListener(new DisposeListener());
		}
	}

	private class DisposeListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			NewAchievementDialog.this.cancel();
		}
	}

}
