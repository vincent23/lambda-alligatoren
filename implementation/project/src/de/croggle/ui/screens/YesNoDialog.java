package de.croggle.ui.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.croggle.ui.ConfirmInterface;
import de.croggle.ui.StyleHelper;

public class YesNoDialog extends Dialog {
	
	private ConfirmInterface ci;
	
	public YesNoDialog(String msg, ConfirmInterface confirm) {
		
		super("", StyleHelper.getInstance().getDialogStyle());
		this.ci = confirm;
		StyleHelper helper = StyleHelper.getInstance();
		
		Label message = new Label(msg, helper.getLabelStyle());
		TextButton yes = new TextButton("Yes", helper.getTextButtonStyle());
		TextButton no = new TextButton("No" , helper.getTextButtonStyle());
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
		add(message).center().pad(100).colspan(2).row();
		add(yes).center().width(300).height(70).pad(10);
		add(no).center().width(300).height(70).pad(10);
		
		
		
		
	}

}
