package de.croggle.ui.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

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
	ShapeRenderer shapes;

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
		shapes = new ShapeRenderer();
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
		//batch.begin(); // called by stage.draw in AbstractScreen
		// 2. clear our depth buffer with 1.0
		Gdx.gl.glClearDepthf(1f);
		Gdx.gl.glClear(GL10.GL_DEPTH_BUFFER_BIT);

		// 3. set the function to LESS
		Gdx.gl.glDepthFunc(GL10.GL_LESS);

		// 4. enable depth writing
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);

		// 5. Enable depth writing, disable RGBA color writing
		Gdx.gl.glDepthMask(true);
		Gdx.gl.glColorMask(false, false, false, false);

		// /////////// Draw mask

		// 6. render your primitive shapes
		batch.draw(mask, 0, 0);
		
		batch.end();

		// /////////// Draw sprite(s) to be masked
		shapes.begin(ShapeType.Filled);

		// 8. Enable RGBA color writing
		// (SpriteBatch.begin() will disable depth mask)
		Gdx.gl.glColorMask(true, true, true, true);

		// 9. Make sure testing is enabled.
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);

		// 10. Now depth discards pixels outside our masked shapes
		Gdx.gl.glDepthFunc(GL10.GL_EQUAL);

		// push to the batch
		shapes.rect(0, 0, getWidth(), getHeight());

		// end/flush your batch
		shapes.end();
		
		batch.begin();
		batch.draw(foreground, 0, 0);
		//batch.end(); // called by stage.draw in AbstractScreen
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
