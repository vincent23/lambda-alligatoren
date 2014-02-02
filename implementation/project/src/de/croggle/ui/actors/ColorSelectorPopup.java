package de.croggle.ui.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.croggle.data.AssetManager;
import de.croggle.game.Color;
import de.croggle.game.ColorController;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.event.BoardEventMessenger;
import de.croggle.ui.StyleHelper;

/**
 * An actor displaying a set of colors that can be tapped to set a BoardObject's
 * color.
 * 
 */
public class ColorSelectorPopup extends Table {

	private final ColoredBoardObject boardObject;
	private final BoardEventMessenger messenger;
	private final ColorController cctrl;
	private final boolean colorBlindEnabled;
	
	private final float width = 150;
	private final float height = 200;

	/**
	 * Creates a new popup to offer the possibility to recolor a certain
	 * ColoredBoardObect.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given {@link ColoredBoardObject} does not allow
	 *             recoloring
	 * @param c
	 *            the {@link ColoredBoardObject} to recolor
	 */
	public ColorSelectorPopup(ColoredBoardObject c, ColorController cctrl, BoardEventMessenger messenger, boolean colorBlindEnabled) {
		if (!c.isRecolorable()) {
			throw new IllegalArgumentException(
					"ColorSelectPopup can only work on ColoredBoardObjects which allow  recoloring.");
		}
		
		this.boardObject = c;
		this.messenger = messenger;
		this.cctrl = cctrl;
		this.colorBlindEnabled = colorBlindEnabled;
		
		setBackground(StyleHelper.getInstance().getDrawable("widgets/button"));
		fillTable();
	}
	
	private void fillTable() {
		AssetManager assets = AssetManager.getInstance();
		int i = 0;
		final int colorsPerRow = 3;
		for (Color c : cctrl.getUsableColors()) {
			Button btn;
			if (colorBlindEnabled) {
				Drawable up = new TextureRegionDrawable(new TextureRegion(assets.getPatternTexture(c)));
				// TODO
				btn = new Button(new Button.ButtonStyle(up, up, up));
			} else {
				Drawable up = new TextureRegionDrawable(new TextureRegion(assets.getColorTexture(c)));
				// TODO
				btn = new Button(new Button.ButtonStyle(up, up, up));
			}
			this.add(btn);
			i++;
			if (i % colorsPerRow == 0) {
				this.row();
			}
		}
	}
	
	@Override
	public float getPrefWidth () {
		return width;
	}

	@Override
	public float getPrefHeight () {
		return height;
	}
	
	@Override
	public float getMinWidth () {
		return width;
	}

	@Override
	public float getMinHeight () {
		return height;
	}
	
	@Override
	public float getMaxWidth () {
		return width;
	}

	@Override
	public float getMaxHeight () {
		return height;
	}

}
