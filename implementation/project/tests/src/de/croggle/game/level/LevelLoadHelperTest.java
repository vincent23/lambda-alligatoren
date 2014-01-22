package de.croggle.game.level;

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
	}
}
