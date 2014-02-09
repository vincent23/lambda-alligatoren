package de.croggle.game.level;

import junit.framework.Assert;
import android.content.Context;
import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidFiles;

import de.croggle.AlligatorApp;

public class LoadColorEditLevelFromJsonTest extends InstrumentationTestCase {

	AlligatorApp app;
	
	@Override
	protected void setUp() {
		Context ctxt = getInstrumentation().getTargetContext();
		AssetManager man = ctxt.getAssets();
		String abspath = ctxt.getFilesDir().getAbsolutePath();
		Gdx.files = new AndroidFiles(man, abspath);
		app = new AlligatorApp(ctxt);
	}
	
	public void testCase0() {
		Level l = LevelLoadHelper.instantiate(0, 0, app);
		Assert.assertTrue(l.getDescription().equals("Erstes Tutorial Level in dem das Einfärben von Spielelementen erklärt wird."));
		Assert.assertTrue(l.hasAnimation());
		Assert.assertTrue(l.getAbortSimulationAfter() == 111);
	}
	
	public void testCase1() {
		Level l = LevelLoadHelper.instantiate(0, 1, app);
		Assert.assertTrue(l.getDescription().equals("Zweites Tutoriallevel, in dem die β-Reduktion gezeigt wird. Benötigte Kenntnis des Spielers hierfür ist das Einfärben von Elementen."));
		Assert.assertFalse(l.hasAnimation());
		Assert.assertTrue(l.getAbortSimulationAfter() == 111);
	}
	
	public void testCase5() {
		Level l = LevelLoadHelper.instantiate(0, 5, app);
		Assert.assertFalse(l.hasAnimation());
		Assert.assertTrue(l.getAbortSimulationAfter() == 5);
	}

	public void testCase8() {
		Level l = LevelLoadHelper.instantiate(0, 8, app);
		Assert.assertFalse(l.hasAnimation());
		Assert.assertTrue(l.getAbortSimulationAfter() == 10);
	}
}
