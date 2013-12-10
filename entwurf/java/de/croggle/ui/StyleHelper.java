package de.croggle.ui;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Singleton to manage Styles.
 *
 * @navassoc 1 - 1 com.badlogic.gdx.scenes.scene2d.ui.Skin
 **/
public class StyleHelper {

	private Skin skin;

	public StyleHelper getInstance(){
		return null;
	}

	public Skin getSkin(){
		return null;
	}
	
	TextButtonStyle getTextButtonStyle(){
		return null;
	}

	ImageButtonStyle getImageButtonStyle(){
		return null;
	}

	ImageTextButtonStyle getImageTextButtonStyle(){
		return null;
	}

	LabelStyle getLabelStyle(){
		return null;
	}

	private void initialize(){

	}

}
