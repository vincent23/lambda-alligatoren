package de.croggle.ui.actors;

import static de.croggle.data.LocalizationHelper._;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.ui.ConfirmInterface;
import de.croggle.ui.StyleHelper;

public class YesNoDialog extends Dialog {

	private ConfirmInterface ci;

	public YesNoDialog(String msg, ConfirmInterface confirm) {

		super("", StyleHelper.getInstance().getDialogStyle());
		this.ci = confirm;
		StyleHelper helper = StyleHelper.getInstance();

		Label message = new Label(msg, helper.getBlackLabelStyle());
		message.setWrap(true);
		message.setAlignment(Align.center);

		TextButton yes = new TextButton(_("button_yes"),
				helper.getTextButtonStyle());
		TextButton no = new TextButton(_("button_no"),
				helper.getTextButtonStyle());
		yes.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ci.yes();
				YesNoDialog.this.hide();
			}
		});

		no.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ci.no();
				YesNoDialog.this.hide();
			}
		});

		clear();
		add(message).width(500).pad(100).colspan(2).expand();
		row();
		add(yes).center().width(300).height(70).pad(10);
		add(no).center().width(300).height(70).pad(10);
		// no idea why this is necessary, but it is
		layout();
	}

}
