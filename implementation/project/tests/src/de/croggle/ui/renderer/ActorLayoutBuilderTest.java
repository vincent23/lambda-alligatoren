package de.croggle.ui.renderer;

import java.util.Map;

import de.croggle.game.ColorController;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.operations.CreateHeightMap;
import de.croggle.util.convert.LambdaToAlligator;
import junit.framework.TestCase;

public class ActorLayoutBuilderTest extends TestCase{
	public void testCorrectHeight() {
		Board b = LambdaToAlligator.convert("(λa.λb.λs.λz.(a s (b s z))) (λs.λz.(s (s (s z)))) (λs.λz.(s (s (s (s z)))))");
		ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		ColorController ccntrlr = new ColorController();
		config.setColorController(ccntrlr);
		Map<BoardObject, Float> heightMap = CreateHeightMap.create(b);
		ActorLayout l = ActorLayoutBuilder.build(b, config);
	}
}
