package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

	private final float width = 210;
	private final float height = 280;

	/**
	 * Creates a new popup to offer the possibility to recolor a certain
	 * ColoredBoardObect.
	 * 
	 * @param editing
	 * @param c
	 * 
	 * @throws IllegalArgumentException
	 *             if the given {@link ColoredBoardObject} does not allow
	 *             recoloring
	 */
	public ColorSelectorPopup(BoardActorLayoutEditing editing,
			ColoredBoardObject c) {
		if (!c.isRecolorable()) {
			throw new IllegalArgumentException(
					"ColorSelectPopup can only work on ColoredBoardObjects which allow  recoloring.");
		}

		this.boardObject = c;
		this.messenger = editing.getMessenger();
		ActorLayoutConfiguration config = editing.getLayout()
				.getLayoutConfiguration();
		this.cctrl = config.getColorController();
		this.colorBlindEnabled = config.isColorBlindEnabled();

		this.setBackground(StyleHelper.getInstance().getDrawable(
				"widgets/button"));
		setSize(width, height);
		setTouchable(Touchable.enabled);
		fillTable();
	}

	public class AutomaticCloseListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			Vector2 point = new Vector2(x, y);
			Group g = (Group) event.getListenerActor();
			if(g.isAscendantOf(ColorSelectorPopup.this)){
				point = g.localToDescendantCoordinates(ColorSelectorPopup.this,
						point);
	
				if (ColorSelectorPopup.this.getParent() != null
						&& ColorSelectorPopup.this.hit(point.x, point.y, true) == null) {
					((BoardActor) (ColorSelectorPopup.this.getParent()))
							.removeFromActor(ColorSelectorPopup.this);
					event.getListenerActor().removeListener(this);
				}
			}
		}
	}

	private void fillTable() {
		int i = 0;
		final int colorsPerRow = 2;
		for (Color c : cctrl.getUsableColors()) {
			Button bt = new Button(StyleHelper.getInstance().getDrawable(
					"widgets/button"));
			ColorButton btn = new ColorButton(c);
			btn.addListener(new ColorButtonListener());
			bt.add(btn).size(60, 60).center();
			this.add(bt).size(76, 76).pad(6);
			i++;
			if (i % colorsPerRow == 0) {
				this.row();
			}
		}
	}

	private class ColorButtonListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			Color col = ((ColorButton) event.getListenerActor()).c;
			ColorSelectorPopup.this.clear();
			ColorSelectorPopup.this.remove();
			boardObject.setColor(col);
			messenger.notifyObjectRecolored(boardObject);
		}
	}

	private class ColorButton extends Button {
		private final Color c;

		private final float width = 70;
		private final float height = width;

		public ColorButton(Color c) {
			this.c = c;
			AssetManager assets = AssetManager.getInstance();
			Drawable up;
			if (colorBlindEnabled) {
				up = new TextureRegionDrawable(new TextureRegion(
						assets.getPatternTexture(c)));
			} else {
				up = new TextureRegionDrawable(new TextureRegion(
						assets.getColorTexture(c)));
			}

			this.setStyle(new Button.ButtonStyle(up, up, up));
		}

		@Override
		public float getPrefWidth() {
			return width;
		}

		@Override
		public float getPrefHeight() {
			return height;
		}

		@Override
		public float getMinWidth() {
			return width;
		}

		@Override
		public float getMinHeight() {
			return height;
		}

		@Override
		public float getMaxWidth() {
			return width;
		}

		@Override
		public float getMaxHeight() {
			return height;
		}
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public float getPrefWidth() {
		return width;
	}

	@Override
	public float getPrefHeight() {
		return height;
	}

	@Override
	public float getMinWidth() {
		return width;
	}

	@Override
	public float getMinHeight() {
		return height;
	}

	@Override
	public float getMaxWidth() {
		return width;
	}

	@Override
	public float getMaxHeight() {
		return height;
	}
}
