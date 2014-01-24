package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.ui.StyleHelper;

/**
 * A simple dialog with only a message and a "close" button to display.
 * 
 */
public class NotificationDialog extends Dialog {

	public NotificationDialog(String msg) {
		super("", StyleHelper.getInstance().getDialogStyle());

		StyleHelper helper = StyleHelper.getInstance();

		Label message = new Label(msg, helper.getLabelStyle());
		TextButton okay = new TextButton("Okay", helper.getTextButtonStyle());

		okay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				NotificationDialog.this.hide();
			}
		});

		clear();
		add(message).center().pad(100).row();
		add(okay).center().width(300).height(70);
	}

}
