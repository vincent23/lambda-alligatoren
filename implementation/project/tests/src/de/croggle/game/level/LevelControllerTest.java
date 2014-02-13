package de.croggle.game.level;

import junit.framework.Assert;
import android.test.InstrumentationTestCase;
import de.croggle.AlligatorApp;
import de.croggle.test.TestActivity;

public class LevelControllerTest extends InstrumentationTestCase {

	LevelController controller;

	@Override
	protected void setUp() throws Exception {
		TestActivity test = new TestActivity(getInstrumentation()
				.getTargetContext(), false);
		AlligatorApp app = test.getApp();
		controller = new LevelController(0, app);
	}

	public void testSize() {
		assertTrue(controller.getPackageSize() == 12);
	}

	public void testLevel() {
		Assert.assertTrue(controller
				.getLevel(0)
				.getDescription()
				.equals("Erstes Tutorial Level in dem das Einfärben von Spielelementen erklärt wird."));
	}

}
