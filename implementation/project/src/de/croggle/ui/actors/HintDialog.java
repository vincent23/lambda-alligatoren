package de.croggle.ui.actors;

import static de.croggle.data.LocalizationHelper._;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.croggle.game.level.Level;
import de.croggle.ui.StyleHelper;

public class HintDialog extends Dialog {
	
	public HintDialog(Level level) {
		super("", StyleHelper.getInstance().getDialogStyle());
		StyleHelper helper = StyleHelper.getInstance();
		Image hint;
		try{
			hint = new Image(helper.getDrawable(level.getHint()));
		} catch (IllegalArgumentException ex) {
			hint = new Image(helper.getDrawable("hints/0000"));
		} catch (GdxRuntimeException ex) {
			hint = new Image(helper.getDrawable("hints/0000"));
		}
		TextButton okay = new TextButton(_("button_ok"),
				helper.getTextButtonStyle());
		
		okay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				HintDialog.this.hide();
			}
		});
		
		clear();
		add(hint).pad(50, 100, 20, 100);
		row();
		add(okay).center().width(300).height(70).pad(30, 30, 50, 30);
		
	}


}
