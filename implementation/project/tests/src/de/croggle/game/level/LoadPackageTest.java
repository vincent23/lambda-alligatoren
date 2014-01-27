package de.croggle.game.level;

import java.util.List;

import junit.framework.Assert;
import android.content.Context;
import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidFiles;

import de.croggle.AlligatorApp;

public class LoadPackageTest extends InstrumentationTestCase {
	
	AlligatorApp app;
	
	@Override
	protected void setUp() {
		Context ctxt = getInstrumentation().getTargetContext();
		AssetManager man = ctxt.getAssets();
		String abspath = ctxt.getFilesDir().getAbsolutePath();
		Gdx.files = new AndroidFiles(man, abspath);
		app = new AlligatorApp(ctxt);
	}

	public void testLoading(){
		LevelPackagesController controller = new LevelPackagesController(app);

		List<LevelPackage> list = controller.getLevelPackages();
		Assert.assertTrue(list.size() == 2);
	}
	
	public void testLoadedValues(){
		LevelPackagesController controller = new LevelPackagesController(app);

		List<LevelPackage> list = controller.getLevelPackages();
		LevelPackage one = list.get(0);
		 Assert.assertEquals(one.getDescription(), "Levelpaket zum Erlernen von Croggel.");
	}
}
