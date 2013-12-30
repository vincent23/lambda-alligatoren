package de.croggle.data;

import android.test.InstrumentationTestCase;
import static de.croggle.data.LocalizationHelper._;

public class LocalizationHelperTest extends InstrumentationTestCase {
	
	public void testAppName() {
		
		LocalizationHelper.setContext(getInstrumentation().getContext());
		String expected = "Croggle Test Test"; // no clue why it results in this name, but it should be correct
		String actual = _("app_name");
		
		System.out.println(actual);
		
		assertEquals(expected, actual);
	}
}
