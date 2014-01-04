package de.croggle.util.convert;

import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import junit.framework.TestCase;

public class AlligatorToJsonTest extends TestCase {

	/**
	 * A simple test with a manually created alligator tree.
	 */
	public void testSimpleManual() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);

		String expected = "{\n\t\"" + "families\" : [\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"colored alligator\",\n"
				+ "\t\t\t\"color\" : 0,\n" + "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true,\n"
				+ "\t\t\t\"children\" : [\n" + "\t\t\t\t{\n"
				+ "\t\t\t\t\t\"type\" : \"egg\",\n"
				+ "\t\t\t\t\t\"color\" : 0,\n"
				+ "\t\t\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\t\t\"recolorable\" : true\n" + "\t\t\t\t}\n"
				+ "\t\t\t]\n" + "\t\t},\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"egg\",\n" + "\t\t\t\"color\" : 1,\n"
				+ "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true\n" + "\t\t}\n\t]\n" + "}\n";
		System.out.println(expected);
		assertEquals(expected, AlligatorToJson.convert(b));
	}

}
