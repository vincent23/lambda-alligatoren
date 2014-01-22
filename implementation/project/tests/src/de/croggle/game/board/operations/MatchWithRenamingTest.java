package de.croggle.game.board.operations;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import junit.framework.TestCase;

public class MatchWithRenamingTest extends TestCase {
	public void testSimple() {
		Board b0 = new Board();
		ColoredAlligator ca00 = new ColoredAlligator(true, true, new Color(0), true);
		ColoredAlligator ca01 = new ColoredAlligator(true, true, new Color(1), true);
		ColoredAlligator ca02 = new ColoredAlligator(true, true, new Color(2), true);
		Egg e00 = new Egg(true, true, new Color(0), true);
		Egg e01 = new Egg(true, true, new Color(1), true);
		Egg e02 = new Egg(true, true, new Color(2), true);
		AgedAlligator aa00 = new AgedAlligator(true, true);
		b0.addChild(ca00);
		ca00.addChild(e00);
		b0.addChild(aa00);
		aa00.addChild(ca01);
		aa00.addChild(ca02);
		ca01.addChild(e01);
		ca02.addChild(e02);
		
		Board b1 = new Board();
		ColoredAlligator ca10 = new ColoredAlligator(true, true, new Color(3), true);
		ColoredAlligator ca11 = new ColoredAlligator(true, true, new Color(4), true);
		ColoredAlligator ca12 = new ColoredAlligator(true, true, new Color(5), true);
		Egg e10 = new Egg(true, true, new Color(3), true);
		Egg e11 = new Egg(true, true, new Color(4), true);
		Egg e12 = new Egg(true, true, new Color(5), true);
		AgedAlligator aa10 = new AgedAlligator(true, true);
		b1.addChild(ca10);
		ca10.addChild(e10);
		b1.addChild(aa10);
		aa10.addChild(ca11);
		aa10.addChild(ca12);
		ca11.addChild(e11);
		ca12.addChild(e12);
		
		assertTrue(MatchWithRenaming.match(b0, b1));
	}
}
