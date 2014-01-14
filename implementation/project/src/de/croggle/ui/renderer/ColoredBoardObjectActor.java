package de.croggle.ui.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.croggle.data.AssetManager;
import de.croggle.game.ColorController;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.InternalBoardObject;

public class ColoredBoardObjectActor extends BoardObjectActor {
	
	protected final ColorController controller;
	private TextureAtlas tex;
	private TextureRegion mask;
	private TextureRegion foreground;
	private AssetManager assetManager;
	private Texture background;

	public ColoredBoardObjectActor(InternalBoardObject object, ColorController controller, String foregroundPath, String maskPath) {
		super(object);
		if (!(object instanceof ColoredBoardObject)) {
			throw new IllegalArgumentException("ColoredBoardObjectActor expects to be representing a ColoredBoardObject");
		}
		ColoredBoardObject cbo = (ColoredBoardObject) object;
		
		this.controller = controller;
		
		assetManager = AssetManager.getInstance();
		assetManager.load("textures/pack.atlas", TextureAtlas.class);
		assetManager.finishLoading();
		tex = assetManager.get("textures/pack.atlas", TextureAtlas.class);
		mask = tex.findRegion(maskPath);
		foreground = tex.findRegion(foregroundPath);
		this.setWidth(foreground.getRegionWidth());
		this.setHeight(foreground.getRegionHeight());
		Pixmap bg = new Pixmap((int)getWidth(), (int)getHeight(), Pixmap.Format.RGB888);
		bg.setColor(controller.getRepresentation(cbo.getColor()));
		bg.fill();
		background = new Texture(bg);
	}
	
	private void drawBackground(SpriteBatch batch) {
		//now that the buffer has our alpha, we simply draw the sprite with the mask applied
		Gdx.gl.glColorMask(true, true, true, true);
		batch.setBlendFunction(GL10.GL_DST_ALPHA, GL10.GL_ONE_MINUS_DST_ALPHA);
		
		//The scissor test is optional, but it depends 
		//Gdx.gl.glEnable(GL10.GL_SCISSOR_TEST);
		//Gdx.gl.glScissor(clipX, clipY, clipWidth, clipHeight);
		
		//draw our background to be masked
		batch.draw(background, getX(), getY(), getWidth(), getHeight());
		
		batch.flush();
		//disable scissor before continuing
		//Gdx.gl.glDisable(GL10.GL_SCISSOR_TEST);
	}
	
	private void drawAlphaMask(SpriteBatch batch) {
		//disable RGB color, only enable ALPHA to the frame buffer
		Gdx.gl.glColorMask(false, false, false, true);
		
		//change the blending function for our alpha map
		batch.setBlendFunction(GL10.GL_ONE, GL10.GL_ZERO);
		
		//draw alpha mask sprite(s)
		batch.draw(mask, getX(), getY(), getWidth(), getHeight());
		
		//flush the batch to the GPU
		batch.flush();
	}
	
	private void drawForeground(SpriteBatch batch) {
		//regular blending mode
		batch.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		batch.draw(foreground, getX(), getY(), getWidth(), getHeight());
		
		//flush the batch to the GPU
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
		// just to make sure
		batch.enableBlending();
		
		//draw the alpha mask
		drawAlphaMask(batch);
		
		//draw our foreground elements
		drawBackground(batch);
		
		//draw background
		drawForeground(batch);
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
	
	protected void sizeChanged() {
	}
}
