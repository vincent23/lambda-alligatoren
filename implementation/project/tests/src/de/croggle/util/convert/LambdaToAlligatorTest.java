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
	}


	@Override
	public void tearDown() {
	}

}
