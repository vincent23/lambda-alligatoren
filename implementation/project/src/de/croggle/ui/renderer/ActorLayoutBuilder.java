package de.croggle.ui.renderer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

import de.croggle.game.Color;
import de.croggle.game.ColorController;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.board.operations.BoardObjectVisitor;
import de.croggle.game.board.operations.CreateHeightMap;
import de.croggle.game.board.operations.CreateWidthMap;

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
class ActorLayoutBuilder implements BoardObjectVisitor {

	// settings
	private final ActorLayoutConfiguration config;
	private final TreeGrowth renderDirectionX;
	private final TreeGrowth renderDirectionY;

	/**
	 * Map to access the width of any BoardObject occurring in the Board to
	 * build in O(1)
	 */
	private final Map<BoardObject, Float> widthMap;

	// working variables
	/**
	 * The hashmap to store the result in
	 */
	private Map<InternalBoardObject, BoardObjectActor> actors;

	/**
	 * The current scaling of newly added BoardObjectActors
	 */
	private float scaling = 1;
	/**
	 * Where newly added BoardObjectActors will be placed
	 */
	private Vector2 currentPosition;

	private ActorLayoutBuilder(Board b, ActorLayoutConfiguration config,
			TreeGrowth renderDirectionX, TreeGrowth renderDirectionY) {
		this.config = config;
		this.renderDirectionX = renderDirectionX;
		this.renderDirectionY = renderDirectionY;
		this.widthMap = CreateWidthMap.create(b,
				config.getUniformObjectWidth(),
				config.getVerticalScaleFactor(), config.getHorizontalPadding());
		this.actors = new HashMap<InternalBoardObject, BoardObjectActor>();
		this.currentPosition = config.getTreeOrigin().cpy();
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
		return build(b, config, TreeGrowth.NEG_POS, TreeGrowth.NEG_POS);
	}

	/**
	 * Creates a hashmap of {@link BoardObjectActor}s corresponding to elements
	 * in the given board, with the respective elements as keys. The
	 * {@link BoardObjectActor}s are already layouted according to the given
	 * layout options.
	 * 
	 * @param b
	 *            the board to create a {@link BoardObjectActor} representation
	 *            for
	 * @param config
	 *            the layout options to be applied when layouting the actors
	 * @param renderDirectionX
	 *            in which direction the {@link BoardActor actors} will actually
	 *            be actually be drawn horizontally
	 * @param renderDirectionY
	 *            in which direction the {@link BoardActor actors} will actually
	 *            be actually be drawn vertically
	 * @return a {@link ActorLayout} representation of the given board, layouted
	 *         in regard of the given parameters
	 */
	public static ActorLayout build(Board b, ActorLayoutConfiguration config,
			TreeGrowth renderDirectionX, TreeGrowth renderDirectionY) {
		ActorLayoutBuilder builder = new ActorLayoutBuilder(b, config,
				renderDirectionX, renderDirectionY);
		b.accept(builder);
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
	public void visitEgg(Egg egg) {
		BoardObjectActor a = new EggActor(egg, config.getColorController());
		float offsetx = (config.getUniformObjectWidth() - config.getEggWidth())
				/ 2 * getScaling();
		if (config.getHorizontalGrowth() == TreeGrowth.POS_NEG) {
			offsetx *= -1;
		}

		float h = config.getEggHeight() * getScaling();
		float y = currentPosition.y;
		if (config.getVerticalGrowth() == TreeGrowth.NEG_POS
				&& renderDirectionY == TreeGrowth.POS_NEG) {
			y += h;
		} else if (config.getVerticalGrowth() == TreeGrowth.POS_NEG
				&& renderDirectionY == TreeGrowth.NEG_POS) {
			y -= h;
		}

		a.setBounds(currentPosition.x + offsetx, y, config.getEggWidth()
				* getScaling(), h);
		actors.put(egg, a);
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		ColoredAlligatorActor a = new ColoredAlligatorActor(alligator,
				config.getColorController());
		setParentActorBounds(a);
		actors.put(alligator, a);
		layoutChildren(alligator);
	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		AgedAlligatorActor a = new AgedAlligatorActor(alligator);
		setParentActorBounds(a);
		actors.put(alligator, a);
		layoutChildren(alligator);
	}

	@Override
	public void visitBoard(Board board) {
		Parent p = board;
		Iterator<InternalBoardObject> it = p.iterator();
		if (config.getHorizontalGrowth() == TreeGrowth.NEG_POS) {
			while (it.hasNext()) {
				InternalBoardObject child = it.next();
				if (renderDirectionX == TreeGrowth.POS_NEG) {
					currentPosition.x += widthMap.get(child);
				}
				child.accept(this);
				if (renderDirectionX == TreeGrowth.NEG_POS) {
					currentPosition.x += widthMap.get(child);
				}
				if (it.hasNext()) {
					currentPosition.x += config.getHorizontalPadding();
				}
			}
		} else {
			while (it.hasNext()) {
				InternalBoardObject child = it.next();
				if (renderDirectionX == TreeGrowth.NEG_POS) {
					currentPosition.x -= widthMap.get(child);
				}
				child.accept(this);
				if (renderDirectionX == TreeGrowth.POS_NEG) {
					currentPosition.x -= widthMap.get(child);
				}
				if (it.hasNext()) {
					currentPosition.x -= config.getHorizontalPadding();
				}
			}
		}
	}

	private float getScaling() {
		return scaling;
	}

	/**
	 * Place a ParentActor in the middle of the horizontal space allocated for
	 * it and its children
	 * 
	 * @param p
	 */
	private void setParentActorBounds(BoardObjectActor p) {
		double totalWidth = widthMap.get(p.getBoardObject());
		float w = config.getUniformObjectWidth() * getScaling();
		float h = config.getUniformObjectHeight() * getScaling();
		if (p.getClass() == AgedAlligatorActor.class) {
			w = config.getAgedAlligatorWidth() * getScaling();
			h = config.getAgedAlligatorHeight() * getScaling();
		} else if (p.getClass() == ColoredAlligatorActor.class) {
			w = config.getColoredAlligatorWidth() * getScaling();
			h = config.getColoredAlligatorHeight() * getScaling();
		}
		float offset = ((float) totalWidth - w) / 2.f;
		if (config.getHorizontalGrowth() == TreeGrowth.POS_NEG) {
			offset *= -1;
		}
		float y = currentPosition.y;
		if (config.getVerticalGrowth() == TreeGrowth.NEG_POS
				&& renderDirectionY == TreeGrowth.POS_NEG) {
			y += h;
		} else if (config.getVerticalGrowth() == TreeGrowth.POS_NEG
				&& renderDirectionY == TreeGrowth.NEG_POS) {
			y -= h;
		}

		p.setBounds(currentPosition.x + offset, y, w, h);
	}

	private void layoutChildren(Parent p) {
		Vector2 initialPosition = currentPosition.cpy();

		// move currentPosition one level down
		float h = config.getUniformObjectHeight() * getScaling();
		if (config.getVerticalGrowth() == TreeGrowth.NEG_POS) {
			currentPosition.y += h + config.getVerticalPadding() * getScaling();
		} else {
			currentPosition.y -= h + config.getVerticalPadding() * getScaling();
		}

		// iterate over the children and layout them depending on the horizontal
		// grow direction
		goDeeper();
		// used for having children still centered if smaller than parent
		float childrenWidth = 0;
		Iterator<InternalBoardObject> it = p.iterator();
		while (it.hasNext()) {
			childrenWidth += widthMap.get(it.next());
			if (it.hasNext()) {
				childrenWidth += getScaling() * config.getHorizontalPadding();
			}
		}

		it = p.iterator();
		if (config.getHorizontalGrowth() == TreeGrowth.NEG_POS) {
			currentPosition.x += (widthMap.get(p) - childrenWidth) / 2;
			while (it.hasNext()) {
				InternalBoardObject child = it.next();
				if (renderDirectionX == TreeGrowth.POS_NEG) {
					currentPosition.x += widthMap.get(child);
				}
				child.accept(this);
				if (renderDirectionX == TreeGrowth.NEG_POS) {
					currentPosition.x += widthMap.get(child);
				}
				if (it.hasNext()) {
					currentPosition.x += getScaling()
							* config.getHorizontalPadding();
				}
			}
		} else {
			currentPosition.x -= (widthMap.get(p) - childrenWidth) / 2;
			while (it.hasNext()) {
				InternalBoardObject child = it.next();
				if (renderDirectionX == TreeGrowth.NEG_POS) {
					currentPosition.x -= widthMap.get(child);
				}
				child.accept(this);
				if (renderDirectionX == TreeGrowth.POS_NEG) {
					currentPosition.x -= widthMap.get(child);
				}
				if (it.hasNext()) {
					currentPosition.x -= getScaling()
							* config.getHorizontalPadding();
				}
			}
		}
		goHigher();

		currentPosition = initialPosition;
	}

	/**
	 * Enter the next level inside the syntax tree
	 */
	private void goDeeper() {
		this.scaling *= config.getVerticalScaleFactor();
	}

	/**
	 * Leave the current level inside the syntax tree
	 */
	private void goHigher() {
		this.scaling /= config.getVerticalScaleFactor();
	}
}
