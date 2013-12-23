package de.croggle.ui;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Singleton to manage Styles.
 * @depend - <helps> - de.croggle.ui.screens.AbstractScreen
 * @navassoc 1 - 1 com.badlogic.gdx.scenes.scene2d.ui.Skin
 **/
public class StyleHelper {

	private Skin skin;
	
	private StyleHelper(){}

	/**
	 * Gets the current instance.
	 * @return the current instance
	 */
	public StyleHelper getInstance(){
		return null;
	}

	/**
	 * Gets the used skin.
	 * @return the skin
	 */
	public Skin getSkin(){
		return null;
	}

	/**
	 * Gets the style of the text button.
	 * @return the text button's style
	 */
	TextButtonStyle getTextButtonStyle(){
		return null;
	}

	/**
	 * Gets the style of the image button.
	 * @return the image button's style
	 */
	ImageButtonStyle getImageButtonStyle(){
		return null;
	}

	/**
	 * Gets the style of the image text button.
	 * @return the image text button's style
	 */
	ImageTextButtonStyle getImageTextButtonStyle(){
		return null;
	}

	/**
	 * Gets the style of the label.
	 * @return the label's style
	 */
	LabelStyle getLabelStyle(){
		return null;
	}

	/**
	 * Inizializes the style helper, so it can be used by the screens.
	 */
	private void initialize(){

	}

}
