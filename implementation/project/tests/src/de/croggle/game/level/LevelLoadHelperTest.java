package de.croggle.game.level;

import junit.framework.Assert;
import android.content.Context;
import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidFiles;

import de.croggle.AlligatorApp;

public class LevelLoadHelperTest extends InstrumentationTestCase {
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
		Assert.assertTrue(l.getLevelIndex() == 0);
	}
	
	public void testCase1() {
		Level l = LevelLoadHelper.instantiate(0, 1, app);
		Assert.assertTrue(l.getLevelIndex() == 1);
	}
	
	public void testCase2() {
		Level l = LevelLoadHelper.instantiate(0, 2, app);
		Assert.assertTrue(l.getLevelIndex() == 2);
	}
}
