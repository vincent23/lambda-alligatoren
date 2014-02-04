package de.croggle.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import de.croggle.data.AssetManager;

/**
 * Singleton to manage Styles.
 **/
public class StyleHelper {

	private static StyleHelper instance;
	private Skin skin;
	private TextureAtlas atlas;
	private FreeTypeFontGenerator generator;

	private StyleHelper() {
		AssetManager manager = AssetManager.getInstance();
		manager.load("textures/pack.atlas", TextureAtlas.class);

	}

	/**
	 * Gets the current instance.
	 * 
	 * @return the current instance
	 */
	public static StyleHelper getInstance() {
		if (instance == null) {
			throw new IllegalStateException(
					"Stylehelper must be initialized before first usage");
		}
		if (instance.skin == null) {
			instance.atlas = AssetManager.getInstance().get(
					"textures/pack.atlas", TextureAtlas.class);
			instance.skin = new Skin(Gdx.files.internal("skin.json"),
					instance.atlas);
			instance.generator = new FreeTypeFontGenerator(
					Gdx.files.internal("fonts/rawengulk_sans.otf"));
			instance.generateFonts(instance.generator, instance.skin);
		}
		return instance;
	}

	/**
	 * Creates a new stylehelper that will from now on be returned by
	 * getInstance
	 */
	public static void initialize() {
		instance = new StyleHelper();
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
		generator.dispose();
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

	public TextButtonStyle getTextButtonStyleLevel() {
		return skin.get("level", TextButtonStyle.class);
	}

	public TextButtonStyle getTextButtonStyleSquare() {
		return skin.get("square", TextButtonStyle.class);
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
		return skin.get(ImageTextButtonStyle.class);
	}

	public ImageTextButtonStyle getImageTextButtonStyleTransparent() {
		return skin.get("transparent", ImageTextButtonStyle.class);
	}

	public ImageTextButtonStyle getImageTextButtonStyle(String icon) {
		ImageTextButtonStyle style = new ImageTextButtonStyle(
				getImageTextButtonStyle());
		style.imageUp = skin.getDrawable(icon);
		return style;
	}

	public ImageTextButtonStyle getImageTextButtonStyleTransparent(String icon) {
		ImageTextButtonStyle style = new ImageTextButtonStyle(
				getImageTextButtonStyleTransparent());
		style.imageUp = skin.getDrawable(icon);
		return style;
	}

	/**
	 * Gets the style of the label.
	 * 
	 * @return the label's style
	 */
	public LabelStyle getLabelStyle() {
		return skin.get(LabelStyle.class);
	}

	public LabelStyle getBlackLabelStyle() {
		LabelStyle style = new LabelStyle(getLabelStyle());
		style.fontColor = skin.getColor("black");
		return style;
	}

	public LabelStyle getLabelStyle(int size) {
		LabelStyle style = new LabelStyle(getLabelStyle());
		style.font = generateFont(size);
		return style;
	}

	public LabelStyle getBlackLabelStyle(int size) {
		LabelStyle style = new LabelStyle(getLabelStyle());
		style.fontColor = skin.getColor("black");
		style.font = generateFont(size);
		return style;
	}

	public CheckBoxStyle getCheckBoxStyle() {
		return skin.get(CheckBoxStyle.class);
	}

	public SliderStyle getSliderStyle() {
		return skin.get(SliderStyle.class);
	}

	public TextFieldStyle getTextFieldStyle() {
		return skin.get(TextFieldStyle.class);
	}

	public SelectBoxStyle getSelectBoxStyle() {
		return skin.get(SelectBoxStyle.class);
	}

	public WindowStyle getDialogStyle() {
		return skin.get("dialog", WindowStyle.class);
	}

	public WindowStyle getWindowStyle() {
		return skin.get(WindowStyle.class);
	}

	public Drawable getDrawable(String path) {
		return skin.getDrawable(path);
	}

	private BitmapFont generateFont(int size) {
		final BitmapFont font = generator.generateFont(size);
		font.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return font;
	}

	private void generateFonts(FreeTypeFontGenerator generator, Skin skin) {
		final BitmapFont labelFont = generateFont(30);
		skin.get(LabelStyle.class).font = labelFont;
		skin.get(TextFieldStyle.class).font = labelFont;
		skin.get(SelectBoxStyle.class).font = labelFont;
		skin.get(SelectBoxStyle.class).listStyle.font = labelFont;
		for (TextButtonStyle style : skin.getAll(TextButtonStyle.class)
				.values()) {
			style.font = labelFont;
		}
		for (ImageTextButtonStyle style : skin.getAll(
				ImageTextButtonStyle.class).values()) {
			style.font = labelFont;
		}
	}

}
