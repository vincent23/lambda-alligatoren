package de.croggle.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import de.croggle.data.AssetManager;

/**
 * Singleton to manage Styles.
 **/
public class StyleHelper {

	private static StyleHelper instance;
	private Skin skin;
	private TextureAtlas atlas;

	public StyleHelper() {
		AssetManager manager = AssetManager.getInstance();
		manager.load("textures/pack.atlas", TextureAtlas.class);
		manager.finishLoading();
		atlas = manager.get("textures/pack.atlas", TextureAtlas.class);
		skin = new Skin(Gdx.files.internal("skin.json"), atlas);
	}

	/**
	 * Gets the current instance.
	 * 
	 * @return the current instance
	 */
	public static StyleHelper getInstance() {
		if (instance == null) {
			instance = new StyleHelper();
		}
		return instance;
	}

	/**
	 * Gets the used skin.
	 * 
	 * @return the skin
	 */
	public Skin getSkin() {
		return skin;
	}

	public void dispose() {
		if (skin != null) {
			skin.dispose();
		}
	}

	/**
	 * Gets the default button style.
	 * 
	 * @return
	 */
	public ButtonStyle getButtonStyle() {
		return skin.get(ButtonStyle.class);
	}

	/**
	 * Gets the style of the text button.
	 * 
	 * @return the text button's style
	 */
	public TextButtonStyle getTextButtonStyle() {
		return skin.get(TextButtonStyle.class);
	}

	/**
	 * Gets the style of the image button.
	 * 
	 * @return the image button's style
	 */
	public ImageButtonStyle getImageButtonStyle() {
		return skin.get(ImageButtonStyle.class);
	}

	/**
	 * Returns the style for a round image button
	 * 
	 * @return
	 */
	public ImageButtonStyle getImageButtonStyleRound() {
		return skin.get("round", ImageButtonStyle.class);
	}

	/**
	 * Returns the style for an image button with the given icon as image.
	 * 
	 * @param icon
	 *            the identifier of the icon in the texture atlas
	 * @return
	 */
	public ImageButtonStyle getImageButtonStyle(String icon) {
		ImageButtonStyle style = new ImageButtonStyle(getImageButtonStyle());
		style.imageUp = skin.getDrawable(icon);
		return style;
	}

	/**
	 * Returns the style for a round image button with the given icon as image.
	 * 
	 * @param icon
	 *            the identifier of the icon in the texture atlas
	 * @return
	 */
	public ImageButtonStyle getImageButtonStyleRound(String icon) {
		ImageButtonStyle style = new ImageButtonStyle(
				getImageButtonStyleRound());
		style.imageUp = skin.getDrawable(icon);
		return style;
	}

	/**
	 * Gets the style of the image text button.
	 * 
	 * @return the image text button's style
	 */
	public ImageTextButtonStyle getImageTextButtonStyle() {
		return null;
	}

	/**
	 * Gets the style of the label.
	 * 
	 * @return the label's style
	 */
	public LabelStyle getLabelStyle() {
		return skin.get(LabelStyle.class);
	}

	public CheckBoxStyle getCheckBoxStyle() {
		return skin.get(CheckBoxStyle.class);
	}

	public SliderStyle getSliderStyle() {
		return skin.get(SliderStyle.class);
	}

}
