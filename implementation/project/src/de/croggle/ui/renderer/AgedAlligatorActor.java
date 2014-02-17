package de.croggle.ui.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.croggle.data.AssetManager;
import de.croggle.game.board.AgedAlligator;

/**
 * An actor used for representing an aged alligator.
 */
public class AgedAlligatorActor extends BoardObjectActor {

	private final TextureRegion foreground;
	private final Matrix3 localTransform = new Matrix3();
	private final Matrix3 worldTransform = new Matrix3();
	private final Matrix4 batchTransform = new Matrix4();
	private final Matrix4 oldBatchTransform = new Matrix4();

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
		setWidth(foreground.getRegionWidth());
		setHeight(foreground.getRegionHeight());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		applyTransform(batch);
		Color c = batch.getColor();
		Color n = getColor();
		batch.setColor(n.r, n.g, n.b, n.a * parentAlpha);
		batch.draw(foreground, 0, 0, getWidth(), getHeight());
		batch.flush();
		batch.setColor(c);
		resetTransform(batch);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
	}

	/**
	 * Set the SpriteBatch's transformation matrix, often with the result of
	 * {@link #computeTransform()}. Note this causes the batch to be flushed.
	 * {@link #resetTransform(SpriteBatch)} will restore the transform to what
	 * it was before this call.
	 * 
	 * @param batch
	 * @param transform
	 */
	private void applyTransform(SpriteBatch batch) {
		Matrix4 transform = computeTransform(batch);
		oldBatchTransform.set(batch.getTransformMatrix());
		batch.setTransformMatrix(transform);
	}

	/**
	 * Restores the SpriteBatch transform to what it was before
	 * {@link #applyTransform(SpriteBatch, Matrix4)}. Note this causes the batch
	 * to be flushed.
	 * 
	 * @param batch
	 */
	private void resetTransform(SpriteBatch batch) {
		batch.setTransformMatrix(oldBatchTransform);
	}

	/**
	 * Returns the transform for this group's coordinate system.
	 * 
	 * @param batch
	 * @return
	 */
	protected Matrix4 computeTransform(SpriteBatch batch) {
		float originX = getOriginX();
		float originY = getOriginY();
		float rotation = getRotation();
		float scaleX = getScaleX();
		float scaleY = getScaleY();

		if (originX != 0 || originY != 0) {
			localTransform.setToTranslation(originX, originY);
		} else {
			localTransform.idt();
		}
		if (rotation != 0) {
			localTransform.rotate(rotation);
		}
		if (scaleX != 1 || scaleY != 1) {
			localTransform.scale(scaleX, scaleY);
		}
		if (originX != 0 || originY != 0) {
			localTransform.translate(-originX, -originY);
		}
		localTransform.trn(getX(), getY());

		worldTransform.set(batch.getTransformMatrix());
		worldTransform.mul(localTransform);

		batchTransform.set(worldTransform);
		return batchTransform;
	}
}
