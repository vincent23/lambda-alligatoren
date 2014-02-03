package de.croggle.ui.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.croggle.data.AssetManager;

public class MaskedImage extends Image {

	private final Texture mask;

	public MaskedImage(String imagePath, String maskPath) {
		AssetManager assets = AssetManager.getInstance();

		assets.load(maskPath, Texture.class);
		assets.load(imagePath, Texture.class);
		assets.finishLoading();

		mask = assets.get(maskPath, Texture.class);

		Texture image = assets.get(imagePath, Texture.class);

		this.setDrawable(new TextureRegionDrawable(new TextureRegion(image)));
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
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
		batch.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);
		super.draw(batch, parentAlpha);
		batch.flush();
		Gdx.gl.glColorMask(false, false, false, true);
		Gdx.gl.glClearColor(0.f, 0.f, 0.f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.flush();
		Gdx.gl.glColorMask(true, true, true, true);
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}
}
