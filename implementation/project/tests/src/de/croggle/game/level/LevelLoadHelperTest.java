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
	
	public void testCase3() {
		Level l = LevelLoadHelper.instantiate(0, 3, app);
		Assert.assertTrue(l.getLevelIndex() == 3);
	}
	
	public void testCase4() {
		Level l = LevelLoadHelper.instantiate(0, 4, app);
		Assert.assertTrue(l.getLevelIndex() == 4);
	}
	
	public void testCase5() {
		Level l = LevelLoadHelper.instantiate(0, 5, app);
		Assert.assertTrue(l.getLevelIndex() == 5);
	}
	
	public void testCase6() {
		Level l = LevelLoadHelper.instantiate(0, 6, app);
		Assert.assertTrue(l.getLevelIndex() == 6);
	}
	
	public void testCase7() {
		Level l = LevelLoadHelper.instantiate(0, 7, app);
		Assert.assertTrue(l.getLevelIndex() == 7);
	}
	
	public void testCase8() {
		Level l = LevelLoadHelper.instantiate(0, 8, app);
		Assert.assertTrue(l.getLevelIndex() == 8);
	}
	
	public void testCase9() {
		Level l = LevelLoadHelper.instantiate(0, 9, app);
		Assert.assertTrue(l.getLevelIndex() == 9);
	}
	
	public void testCase10() {
		Level l = LevelLoadHelper.instantiate(0, 10, app);
		Assert.assertTrue(l.getLevelIndex() == 10);
	}
	
	public void testCase11() {
		Level l = LevelLoadHelper.instantiate(0, 11, app);
		Assert.assertTrue(l.getLevelIndex() == 11);
	}
	
	public void testCase20() {
		Level l = LevelLoadHelper.instantiate(1, 0, app);
		Assert.assertTrue(l.getLevelIndex() == 0);
	}
	
	public void testCase21() {
		Level l = LevelLoadHelper.instantiate(1, 1, app);
		Assert.assertTrue(l.getLevelIndex() == 1);
	}
	
	public void testCase22() {
		Level l = LevelLoadHelper.instantiate(1, 2, app);
		Assert.assertTrue(l.getLevelIndex() == 2);
	}
}
