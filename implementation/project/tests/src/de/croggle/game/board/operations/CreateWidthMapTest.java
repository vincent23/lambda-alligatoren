package de.croggle.game.board.operations;

import java.util.Map;

import com.badlogic.gdx.math.Vector2;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.ui.renderer.ActorLayoutConfiguration;
import de.croggle.ui.renderer.AgedAlligatorActor;
import de.croggle.ui.renderer.TreeGrowth;
import de.croggle.util.convert.LambdaToAlligator;
import junit.framework.TestCase;

public class CreateWidthMapTest extends TestCase {
	public void testSimple() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);

		Map<BoardObject, Float> map = CreateWidthMap.create(b);
		assertEquals(2.0f, map.get(b));
		assertEquals(1.0f, map.get(a));
		assertEquals(1.0f, map.get(e1));
		assertEquals(1.0f, map.get(e2));
	}

	public void testCase0() {
		// standard layout options
		ActorLayoutConfiguration config = new ActorLayoutConfiguration(
				new Vector2(0, 0), TreeGrowth.NEG_POS, TreeGrowth.POS_NEG, TreeGrowth.NEG_POS, TreeGrowth.NEG_POS, .75f, 2, 2, null, false, 150, 150, 150, 150, 150, 150);

		Board b = LambdaToAlligator.convert("(λx.x) ((λy.y) (λz.z))");
		Map<BoardObject, Float> map = CreateWidthMap.create(b,
				config.getUniformObjectWidth(),
				config.getVerticalScaleFactor(), config.getHorizontalPadding());
		AgedAlligator aa = (AgedAlligator) b.iterator(1).next();
		assertEquals(226.5, map.get(aa), 1e-8);
	}
}
