package de.croggle.ui.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class SelectBox extends com.badlogic.gdx.scenes.scene2d.ui.SelectBox {
	private boolean disabled;
	private final TextBounds bounds = new TextBounds();

	public SelectBox(Object[] items, Skin skin) {
		this(items, skin.get(SelectBoxStyle.class));
	}

	public SelectBox(Object[] items, Skin skin, String styleName) {
		this(items, skin.get(styleName, SelectBoxStyle.class));
	}

	public SelectBox(Object[] items, SelectBoxStyle style) {
		super(items, style);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Drawable background;
		SelectBoxStyle style = getStyle();
		String[] items = getItems();
		int selectedIndex = getSelectionIndex();
		if (disabled)
			background = style.backgroundDisabled;
		// TODO not elegant
		// else if (list != null && list.getParent() != null
		// && style.backgroundOpen != null)
		// background = style.backgroundOpen;
		// else if (clickListener.isOver() && style.backgroundOver != null)
		// background = style.backgroundOver;
		else
			background = style.background;
		final BitmapFont font = style.font;
		final Color fontColor = (disabled && style.disabledFontColor != null) ? style.disabledFontColor
				: style.fontColor;

		Color color = getColor();
		float x = getX();
		float y = getY();
		float width = getWidth();
		float height = getHeight();

		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		background.draw(batch, x, y, width, height);
		if (items.length > 0) {
			float availableWidth = width - background.getLeftWidth()
					- background.getRightWidth();
			int numGlyphs = font.computeVisibleGlyphs(items[selectedIndex], 0,
					items[selectedIndex].length(), availableWidth);
			bounds.set(font.getBounds(items[selectedIndex]));
			bounds.height *= getStage().getCamera().viewportHeight
					/ Gdx.graphics.getHeight();
			height -= background.getBottomHeight() + background.getTopHeight();
			float textY = (int) (height / 2 + background.getBottomHeight() - bounds.height / 2);
			font.setColor(fontColor.r, fontColor.g, fontColor.b, fontColor.a
					* parentAlpha);
			font.draw(batch, items[selectedIndex],
					x + background.getLeftWidth(), y + textY, 0, numGlyphs);
		}
	}

	@Override
	public void setDisabled(boolean disabled) {
		super.setDisabled(disabled);
		this.disabled = disabled;
	}
}
