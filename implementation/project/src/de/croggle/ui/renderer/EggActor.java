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
import de.croggle.game.board.Egg;

/**
 * An actor used for representing an egg.
 */
public class EggActor extends ColoredBoardObjectActor {

	TextureAtlas tex;
	TextureRegion mask;
	TextureRegion foreground;
	AssetManager assetManager;
	Texture background;

	/**
	 * Creates a new actor.
	 * 
	 * @param egg
	 *            The egg represented by the actor
	 */
	public EggActor(Egg egg, ColorController controller) {
		super(egg, controller);

		assetManager = AssetManager.getInstance();
		assetManager.load("textures/pack.atlas", TextureAtlas.class);
		assetManager.finishLoading();
		tex = assetManager.get("textures/pack.atlas", TextureAtlas.class);
		mask = tex.findRegion("egg/background");
		foreground = tex.findRegion("egg/foreground");
		this.setWidth(foreground.getRegionWidth());
		this.setHeight(foreground.getRegionHeight());
		Pixmap bg = new Pixmap((int)getWidth(), (int)getHeight(), Pixmap.Format.RGB888);
		bg.setColor(controller.getRepresentation(((Egg)this.object).getColor()));
		bg.fill();
		background = new Texture(bg);
	}

	
	private void drawBackground(SpriteBatch batch, float width, float height) {
		//now that the buffer has our alpha, we simply draw the sprite with the mask applied
		Gdx.gl.glColorMask(true, true, true, true);
		batch.setBlendFunction(GL10.GL_DST_ALPHA, GL10.GL_ONE_MINUS_DST_ALPHA);
		
		//The scissor test is optional, but it depends 
		//Gdx.gl.glEnable(GL10.GL_SCISSOR_TEST);
		//Gdx.gl.glScissor(clipX, clipY, clipWidth, clipHeight);
		
		//draw our background to be masked
		batch.draw(background, 0, 0, width, height);
		
		batch.flush();
		//disable scissor before continuing
		//Gdx.gl.glDisable(GL10.GL_SCISSOR_TEST);
	}
	
	private void drawAlphaMask(SpriteBatch batch, float width, float height) {
		//disable RGB color, only enable ALPHA to the frame buffer
		Gdx.gl.glColorMask(false, false, false, true);
		
		//change the blending function for our alpha map
		batch.setBlendFunction(GL10.GL_ONE, GL10.GL_ZERO);
		
		//draw alpha mask sprite(s)
		batch.draw(mask, 0, 0, width, height);
		
		//flush the batch to the GPU
		batch.flush();
	}
	
	private void drawForeground(SpriteBatch batch, float width, float height) {
		//regular blending mode
		batch.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		batch.draw(foreground, 0, 0, width, height);
		
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
		float w = getWidth();
		float h = getHeight();
		
		// just to make sure
		batch.enableBlending();
		
		//draw the alpha mask
		drawAlphaMask(batch, w, h);
		
		//draw our foreground elements
		drawBackground(batch, w, h);
		
		//draw background
		drawForeground(batch, w, h);
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

	/**
	 * Signals the actor to (re-)enter the normal rendering state. That is, an
	 * egg with a specific color.
	 */
	public void enterNormalState() {

	}

	/**
	 * Signals the actor to enter the hatching rendering state. That is,
	 * scattered eggshell with the specific color. Will initiate a transition
	 * animation from a normal egg to the broken eggshell.
	 */
	public void enterHatchingState() {

	}
}
