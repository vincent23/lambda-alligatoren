package de.croggle.ui.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.croggle.data.AssetManager;
import de.croggle.game.board.ColoredBoardObject;

public class ColoredBoardObjectActor extends BoardObjectActor {

	private final TextureRegion mask;
	private final TextureRegion foreground;
	private Texture background;
	private boolean valid = false;
	private boolean colorBlind = false;

	public ColoredBoardObjectActor(ColoredBoardObject object,
			boolean colorBlindEnabled, String foregroundPath, String maskPath) {
		super(object);

		AssetManager assetManager = AssetManager.getInstance();
		TextureAtlas tex;
		try {
			tex = assetManager.get("textures/pack.atlas", TextureAtlas.class);
		} catch (GdxRuntimeException ex) {
			throw new IllegalStateException(
					"Could not access atlas containing necessary textures. Make sure it is loaded before instantiating BoardObjectActors.");
		}
		mask = tex.findRegion(maskPath);
		foreground = tex.findRegion(foregroundPath);
		this.colorBlind = colorBlindEnabled;
		this.setWidth(foreground.getRegionWidth());
		this.setHeight(foreground.getRegionHeight());

		validate();
	}

	/**
	 * Updates the color/pattern texture of this {@link ColoredBoardObject}. The
	 * Actor is automatically validated next time it is rendered.
	 */
	public void validate() {
		if (colorBlind) {
			background = AssetManager.getInstance().getPatternTexture(
					((ColoredBoardObject) getBoardObject()).getColor());
		} else {
			background = AssetManager.getInstance().getColorTexture(
					((ColoredBoardObject) getBoardObject()).getColor());
		}
		valid = true;
	}

	/**
	 * Invalidates this actor, causing it to refresh its background texture
	 * before it is rendered the next time.
	 */
	public void invalidate() {
		valid = false;
	}

	public void setColorBlindEnabled(boolean enabled) {
		if (enabled == colorBlind) {
			return;
		} else {
			colorBlind = enabled;
			invalidate();
		}
	}

	public boolean getColorBlindEnabled() {
		return colorBlind;
	}

	private void drawAlphaMask(SpriteBatch batch) {
		// prevent batch from drawing buffered stuff here
		batch.flush();
		// disable RGB color, only enable ALPHA to the frame buffer
		Gdx.gl.glColorMask(false, false, false, true);

		// change the blending function for our alpha map
		batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);

		// draw alpha mask sprite(s)
		batch.draw(mask, getX(), getY(), getWidth() * getScaleX(), getHeight()
				* getScaleY());

		// flush the batch to the GPU
		batch.flush();
		// reset the color mask
		Gdx.gl.glColorMask(true, true, true, true);
		// reset blend function
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}

	private void drawBackground(SpriteBatch batch) {
		if (!valid) {
			validate();
		}
		// now that the buffer has our alpha, we simply draw the sprite with the
		// mask applied
		batch.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);

		// The scissor test is optional, but it depends
		// Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
		// Gdx.gl.glScissor((int) getX(), (int) getY(), (int)
		// Math.ceil(getWidth()), (int) Math.ceil(getHeight()));

		// draw our background to be masked
		final float width = getWidth() * getScaleX();
		final float height = getHeight() * getScaleY();
		final int n = 10;
		batch.draw(background, getX(), getY(), width, height, 0, 0, n, n
				* height / width);

		batch.flush();
		// disable scissor before continuing
		// Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
		// reset blend function
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}

	private void drawForeground(SpriteBatch batch) {
		batch.draw(foreground, getX(), getY(), getWidth() * getScaleX(),
				getHeight() * getScaleY());

		// flush the batch to the GPU
		batch.flush();
	}

	/**
	 * Draws the actor. The sprite batch is configured to draw in he parent's
	 * coordinate system.
	 * 
	 * @param batch
	 *            The sprite batch specifies where to draw into.
	 * @param parentAlpha
	 *            the parent's alpha value
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Color c = batch.getColor();
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a * parentAlpha);

		// draw the alpha mask
		drawAlphaMask(batch);

		// draw background
		drawBackground(batch);

		// draw our foreground elements
		drawForeground(batch);

		// restore alpha value that was removed by mask
		Gdx.gl.glColorMask(false, false, false, true);
		batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
		batch.draw(background, getX(), getY(), getWidth() * getScaleX(),
				getHeight() * getScaleY());
		batch.flush();
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glColorMask(true, true, true, true);
		batch.setColor(c);
	}

	/**
	 * Updates the actor based on time.
	 * 
	 * @param delta
	 *            Time in seconds since the last update.
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	protected void sizeChanged() {
	}
}
