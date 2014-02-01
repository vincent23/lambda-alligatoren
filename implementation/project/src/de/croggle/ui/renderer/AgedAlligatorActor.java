package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.croggle.data.AssetManager;
import de.croggle.game.board.AgedAlligator;

/**
 * An actor used for representing an aged alligator.
 */
public class AgedAlligatorActor extends BoardObjectActor {

	private TextureRegion foreground;

	/**
	 * Creates a new actor.
	 * 
	 * @param alligator
	 *            the AgedAlligator represented by this actor
	 */
	public AgedAlligatorActor(AgedAlligator alligator) {
		super(alligator);
		AssetManager assetManager = AssetManager.getInstance();

		TextureAtlas tex;
		try {
			tex = assetManager.get("textures/pack.atlas", TextureAtlas.class);
		} catch (GdxRuntimeException ex) {
			throw new IllegalStateException(
					"Could not access atlas containing necessary textures. Make sure it is loaded before instantiating BoardObjectActors.");
		}
		foreground = tex.findRegion("agedalligator/alligator");
		this.setWidth(foreground.getRegionWidth());
		this.setHeight(foreground.getRegionHeight());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Color c = batch.getColor();
		Color n = getColor();
		batch.setColor(n.r, n.g, n.b, n.a * parentAlpha);
		batch.draw(foreground, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());
		batch.flush();
		batch.setColor(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
	}
}
