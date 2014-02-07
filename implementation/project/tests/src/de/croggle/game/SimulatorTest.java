package de.croggle.game;

import junit.framework.TestCase;
import de.croggle.game.board.AlligatorOverflowException;
import de.croggle.game.board.Board;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.board.operations.RemoveUselessAgedAlligators;
import de.croggle.game.event.BoardEventMessenger;
import de.croggle.util.convert.AlligatorToLambda;
import de.croggle.util.convert.LambdaToAlligator;

public class SimulatorTest extends TestCase {

	public void testOmega() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λx.x x) λx.x x", "(λx.x x) λx.x x", 1);
	}

	public void testLevel2() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λo.o) y", "y", 1);
	}

	public void testLevel8() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λx.λy.x) z", "λy.z", 1);
	}

	public void testLevel10() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λx.λy.x x) u v", "u u", 2);

	}

	public void testLevel12() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λx.x)((λy.y)(λz.z))", "λz.z", 2);
	}

	public void testTwoAlligators() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λx.x) (λy.y)", "(λy.y)", 1);
	}

	public void testTakeFirst() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λx.λy. x) a b", "a", 2);
	}

	public void testTakeSecond() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λx.λy. x) a b", "b", 2);
	}

	public void testOldAlligator() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λx.x) ((λy.y) (λz.z))", "λz.z", 2);
	}

	public void testColorRule() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λx.λy.x) (λy.y)", "λz.(λy.y)", 1);
	}

	public void testYCombinatorOneStep() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("λg.(λx.g (x x)) (λx.g (x x))",
				"λg.g ((λx.g (x x)) (λx.g (x x)))", 1);
	}

	public void testIncrementZero() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest("(λa.λs.λz.s (a s z)) (λs.λz.z)", "(λs.λz.s z)", 3);
	}

	public void testOnePlusOne() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest(
				"(λa.λb.λs.λz.(a s (b s z))) (λs.λz.(s z)) (λs.λz.(s z))",
				"(λs.λz.s (s z))", 6);
	}

	public void testThreePlusFour() throws IllegalBoardException,
			ColorOverflowException, AlligatorOverflowException {
		inputOutputTest(
				"(λa.λb.λs.λz.(a s (b s z))) (λs.λz.(s (s (s z)))) (λs.λz.(s (s (s (s z)))))",
				"(λs.λz.s (s (s (s (s (s(s z)))))))", 6);
	}

	private void inputOutputTest(String input, String output, int maxSteps)
			throws IllegalBoardException, ColorOverflowException,
			AlligatorOverflowException {
		final Board inputBoard = LambdaToAlligator.convert(input);
		final Board outputBoard = LambdaToAlligator.convert(output);
		final Simulator simulator = new Simulator(inputBoard,
				new ColorController(), new BoardEventMessenger());
		Board evaluated = simulator.getCurrentBoard();
		for (int i = 0; i < maxSteps; i++) {
			simulator.evaluate();
			RemoveUselessAgedAlligators.remove(evaluated,
					new BoardEventMessenger());
			if (MatchWithRenaming.match(outputBoard, evaluated)) {
				return;
			}
		}
		fail("Evaluated to " + AlligatorToLambda.convert(evaluated)
				+ ", expected " + AlligatorToLambda.convert(outputBoard));
	}
}
