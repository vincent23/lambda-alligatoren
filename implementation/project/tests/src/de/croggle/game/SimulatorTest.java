package de.croggle.game;

import junit.framework.TestCase;
import de.croggle.game.board.AlligatorOverflowException;
import de.croggle.game.board.Board;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.board.operations.MatchWithRenaming;
import de.croggle.game.event.BoardEventMessenger;
import de.croggle.util.convert.LambdaToAlligator;

public class SimulatorTest extends TestCase {

	public void testOmega() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		final Board input = LambdaToAlligator.convert("(λx.x x) λx.x x");
		final Simulator simulator = new Simulator(input, new ColorController(),
				new BoardEventMessenger());
		for (int i = 0; i < 5; i++) {
			final Board evaluated = simulator.evaluate();
			assertTrue(MatchWithRenaming.match(input, evaluated));
		}
	}
}
