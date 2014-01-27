package de.croggle.game.level;

import junit.framework.Assert;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidFiles;

import de.croggle.AlligatorApp;
import android.content.Context;
import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

public class LevelControllerTest extends InstrumentationTestCase {
	
	LevelController controller;

	protected void setUp() throws Exception {
		Context ctxt = getInstrumentation().getTargetContext();
		AssetManager man = ctxt.getAssets();
		String abspath = ctxt.getFilesDir().getAbsolutePath();
		Gdx.files = new AndroidFiles(man, abspath);
		AlligatorApp app = new AlligatorApp(ctxt);
		controller = new LevelController(0, app);
	}
	
	
	public void testSize(){
		assertTrue(controller.getPackageSize() == 12);
	}
	
	public void testLevel(){
		Assert.assertTrue(controller.getLevel(0).getDescription().equals("Erstes Tutorial Level in dem das Einfärben von Spielelementen erklärt wird."));
	}
	
	

}
