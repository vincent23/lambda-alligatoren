package de.croggle.util.convert;

import de.croggle.game.board.Board;
import junit.framework.TestCase;

/**
 * 
 */
public class LambdaToAlligatorTest extends TestCase {

	public LambdaToAlligatorTest() {
	}

	@Override
	protected void setUp() throws Exception {

	}

	public void testSimpleTerm() {
		Board b = LambdaToAlligator.convert("(λx.x) y");
		String s = AlligatorToLambda.convert(b);
		assertEquals("(λx.x) y", s);
	}

	public void testComplexTerm() {
		String term = "(λa.λb.λs.λz.(a s (b s z))) (λs.λz.(s (s (s z)))) (λs.λz.(s (s (s (s z)))))";
		String expected = "(λx.λy.λz.λp.(x z (y z p))) (λz.λp.(z (z (z p)))) λz.λp.(z (z (z (z p))))";
		Board b = LambdaToAlligator.convert(term);
		String s = AlligatorToLambda.convert(b);
		System.out.println(s);
		assertEquals(expected, s);
	}

	public void testLongVariableNames() {
		String term = "λone.one two";
		Board b = LambdaToAlligator.convert(term);
		String s = AlligatorToLambda.convert(b);
		assertEquals("λx.x y", s);
	}

	@Override
	public void tearDown() {
	}

}
