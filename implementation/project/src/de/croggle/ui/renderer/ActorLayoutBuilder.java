package de.croggle.ui.renderer;

import java.util.HashMap;
import java.util.Map;

import de.croggle.game.Color;
import de.croggle.game.ColorController;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.operations.CreateHeightMap;

/**
 * Helper class to create and layout a {@link Board}'s representation of
 * {@link BoardActor}s. The resulting set of of {@link BoardObjectActor}s is
 * stored in a map to be able to access each BoardObjectActor individually via
 * the InternalBoardObject it represents.
 * 
 * WARNING: The code assumes that the given widths and heights are directed the
 * same direction as the respective TreeGrowths are set. That means, no
 * additional space is reserved for the actors' widths and heights.
 * 
 * The code realizes the design decision, that it is always the parent which is
 * responsible for the correct positioning of the child. The advantage or result
 * of this is, that the parent enforces paddings.
 */
class ActorLayoutBuilder extends ActorLayouter {

	// working variables
	/**
	 * The hashmap to store the result in
	 */
	private Map<InternalBoardObject, BoardObjectActor> actors;

	public ActorLayoutBuilder(Board b, ActorLayoutConfiguration config) {
		super(b, config);
		this.actors = new HashMap<InternalBoardObject, BoardObjectActor>();
	}

	/**
	 * Creates a hashmap of {@link BoardObjectActor}s corresponding to elements
	 * in the given board, with the respective elements as keys. The
	 * {@link BoardObjectActor}s are already layouted according to the given
	 * layout options.
	 * 
	 * 
	 * 
	 * @param b
	 *            the board to create a {@link BoardObjectActor} representation
	 *            for
	 * @param config
	 *            the layout options to be applied when layouting the actors
	 * @return a {@link ActorLayout} representation of the given board, layouted
	 *         in regard of the given parameters
	 */
	public static ActorLayout build(Board b, ActorLayoutConfiguration config) {
		ActorLayoutBuilder builder = new ActorLayoutBuilder(b, config);
		builder.doLayout();
		ActorLayout result = new ActorLayout(builder.actors, b, config);
		result.getLayoutStatistics().setWidthMap(builder.widthMap);
		// TODO as this is not really necessary for the build process, maybe
		// implement it more efficiently as a byproduct?
		Map<BoardObject, Float> heightMap = CreateHeightMap.create(b,
				config.getUniformObjectHeight(),
				config.getVerticalScaleFactor(), config.getVerticalPadding());
		result.getLayoutStatistics().setHeightMap(heightMap);
		return result;
	}

	/**
	 * Creates a {@link Board}'s representation of {@link BoardObjectActor}s
	 * using a standard set of {@link ActorLayoutConfiguration layout options}
	 * 
	 * @param b
	 *            the board to build a representation for
	 * @param ccntrlr
	 *            the color controller used by the {@link BoardActor}s for
	 *            obtaining real world colors representing their game name
	 *            {@link Color}.
	 * @return a {@link ActorLayout} representation of the given board
	 */
	public static ActorLayout build(Board b, ColorController ccntrlr) {
		ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(ccntrlr);
		return build(b, config);
	}

	@Override
	protected AgedAlligatorActor provideAgedAlligatorActor(AgedAlligator alligator) {
		AgedAlligatorActor actor = new AgedAlligatorActor(alligator);
		actors.put(alligator, actor);
		return actor;
	}

	@Override
	protected ColoredAlligatorActor provideColoredAlligatorActor(ColoredAlligator alligator) {
		ColoredAlligatorActor actor = new ColoredAlligatorActor(alligator, getConfig().isColorBlindEnabled());
		actors.put(alligator, actor);
		return actor;
	}

	@Override
	protected EggActor provideEggActor(Egg egg) {
		EggActor actor = new EggActor(egg, getConfig().isColorBlindEnabled());
		actors.put(egg, actor);
		return actor;
	}
}
