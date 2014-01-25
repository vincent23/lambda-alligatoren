package de.croggle.game;

import junit.framework.TestCase;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.AlligatorOverflowException;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.IllegalBoardException;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.operations.BoardObjectVisitor;
import de.croggle.game.board.operations.MatchWithRenaming;
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

	private void inputOutputTest(String input, String output, int maxSteps)
			throws IllegalBoardException, ColorOverflowException,
			AlligatorOverflowException {
		final Board inputBoard = LambdaToAlligator.convert(input);
		final Board outputBoard = LambdaToAlligator.convert(output);
		final Simulator simulator = new Simulator(inputBoard,
				new ColorController(), new BoardEventMessenger());
		final RemoveUselessAgedAlligators removeVisitor = new RemoveUselessAgedAlligators();
		Board evaluated = inputBoard;
		for (int i = 0; i < maxSteps; i++) {
			evaluated = simulator.evaluate();
			evaluated.accept(removeVisitor);
			if (MatchWithRenaming.match(outputBoard, evaluated)) {
				return;
			}
		}
		fail("Evaluated to " + AlligatorToLambda.convert(evaluated)
				+ ", expected " + AlligatorToLambda.convert(outputBoard));
	}

	private static class RemoveUselessAgedAlligators implements
			BoardObjectVisitor {

		@Override
		public void visitEgg(Egg egg) {
		}

		@Override
		public void visitColoredAlligator(ColoredAlligator alligator) {
			alligator.acceptOnChildren(this);
		}

		@Override
		public void visitAgedAlligator(AgedAlligator alligator) {
			if (alligator.getParent().getFirstChild() == alligator) {
				alligator.getParent().removeChild(alligator);
				alligator.acceptOnChildren(this);
				int i = 0;
				for (InternalBoardObject child : alligator) {
					alligator.getParent().insertChild(child, i);
					i++;
				}
			}
		}

		@Override
		public void visitBoard(Board board) {
			board.acceptOnChildren(this);
		}

	}
}
