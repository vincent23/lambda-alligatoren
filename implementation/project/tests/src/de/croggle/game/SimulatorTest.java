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
		inputOutputTest("(λx.x x) λx.x x", "(λx.x x) λx.x x", 5);
	}

	private void inputOutputTest(String input, String output, int maxSteps)
			throws IllegalBoardException, ColorOverflowException,
			AlligatorOverflowException {
		final Board inputBoard = LambdaToAlligator.convert(input);
		final Board outputBoard = LambdaToAlligator.convert(output);
		final Simulator simulator = new Simulator(inputBoard,
				new ColorController(), new BoardEventMessenger());
		for (int i = 0; i < maxSteps; i++) {
			final Board evaluated = simulator.evaluate();
			if (MatchWithRenaming.match(outputBoard, evaluated)) {
				return;
			}
		}
		fail();
	}
}
