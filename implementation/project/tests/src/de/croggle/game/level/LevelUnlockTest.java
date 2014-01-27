package de.croggle.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidFiles;

import de.croggle.AlligatorApp;
import android.content.Context;
import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

public class LevelUnlockTest extends InstrumentationTestCase  {
	
	LevelController c;

	protected void setUp() throws Exception {
		Context ctxt = getInstrumentation().getTargetContext();
		AssetManager man = ctxt.getAssets();
		String abspath = ctxt.getFilesDir().getAbsolutePath();
		Gdx.files = new AndroidFiles(man, abspath);
		AlligatorApp app = new AlligatorApp(ctxt);
		c = new LevelController(0, app);
	}
	
	
	public void testUnlocked(){
		assertTrue(c.getUnlockedLevel(0).getUnlocked());
		assertFalse(c.getLevel(1).getUnlocked());
	}
	
	public void testNotUnlocked(){
		assertNull(c.getUnlockedLevel(6));
	}
	
	public void testSolved(){
		c.getUnlockedLevel(0).setSolvedTrue();
		assertNotNull(c.getUnlockedLevel(1));
	}
	

}
