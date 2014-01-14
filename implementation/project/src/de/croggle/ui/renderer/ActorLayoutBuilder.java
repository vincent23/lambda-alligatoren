package de.croggle.ui.renderer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

import de.croggle.game.ColorController;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.board.operations.BoardObjectVisitor;
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
public class ActorLayoutBuilder implements BoardObjectVisitor {

	// settings
	private final ActorLayoutConfiguration config;

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

	private ActorLayoutBuilder(Board b, ActorLayoutConfiguration config) {
		this.config = config;
		this.actors = new HashMap<InternalBoardObject, BoardObjectActor>();
		this.widthMap = CreateWidthMap
				.create(b, config.getOriginalObjectWidth(),
						config.getVerticaleScaleFactor(),
						config.getHorizontalPadding());
		this.currentPosition = config.getTreeOrigin();
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
	 * @param firstElementPosition
	 *            the x,y-coordinate where the first element in the board is to
	 *            be displayed.
	 * @param objectWidth
	 *            the horizontal number of pixels allocated for each
	 *            BoardObject's representation
	 * @param objectHeight
	 *            the vertical number of pixels allocated for each BoardObject's
	 *            representation
	 * @param scalefactor
	 *            the relative size of a child compared to its parent
	 * @param paddingHorizontal
	 *            the number of pixels used to separate two horizontally
	 *            neighboring BoardObjectActors
	 * @param paddingVertical
	 *            the number of pixels used to separate two vertically
	 *            neighboring levels of BoardObjectActors in the tree
	 * @param horizontalGrowDirection
	 *            in which direction the tree of objects is to grow horizontally
	 * @param verticalGrowDirection
	 *            in which direction the tree of objects is to grow vertically
	 * @return a map of {@link BoardObjectActor}s, accessible via the original
	 *         {@link InternalBoardObject} they represent, layouted in regard of
	 *         the given parameters
	 */
	public static Map<InternalBoardObject, BoardObjectActor> build(Board b,
			ActorLayoutConfiguration config) {
		ActorLayoutBuilder builder = new ActorLayoutBuilder(b, config);
		b.accept(builder);
		return builder.actors;
	}

	/**
	 * Creates a {@link Board}'s representation of {@link BoardObjectActor}s
	 * using a standard set of layout options, namely:
	 * <ul>
	 * <li>origin: 0.f, 0.f</li>
	 * <li>objectWidth: 150</li>
	 * <li>objectHeight: 100</li>
	 * <li>scaleFactor: 0.75</li>
	 * <li>paddingHorizontal: 2</li>
	 * <li>paddingVertical: 1</li>
	 * <li>horizontalGrowDirection: NEG:POS</li>
	 * <li>verticalGrowDirection: POS_NEG</li>
	 * </ul>
	 * 
	 * WARNING: The code assumes that the given widths and heights are directed
	 * the same direction as the respective TreeGrowths are set. That means, no
	 * additional space is reserved for the actors' widths and heights.
	 * 
	 * @param b
	 *            the board to build a representation for
	 * @return the map of layouted BoardObjectActors, accessible via the
	 *         InternalBoardObject they represent
	 */
	public static Map<InternalBoardObject, BoardObjectActor> build(Board b, ColorController ccntrlr) {
		ActorLayoutConfiguration config = new ActorLayoutConfiguration();
		config.setColorController(ccntrlr);
		return build(b, config);
	}

	@Override
	public void visitEgg(Egg egg) {
		BoardObjectActor a = new EggActor(egg, config.getColorController());
		a.setBounds(currentPosition.x, currentPosition.y, config.getOriginalObjectWidth()
				* getScaling(), config.getOriginalObjectHeight() * getScaling());
		actors.put(egg, a);
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		ColoredAlligatorActor a = new ColoredAlligatorActor(alligator, config.getColorController());
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
		goHigher();
		layoutChildren(board);
		goDeeper();
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
		float w = config.getOriginalObjectWidth() * getScaling();
		float h = config.getOriginalObjectHeight() * getScaling();
		float offset = ((float) totalWidth - w) / 2.f;
		if (config.getHorizontalGrowth() == TreeGrowth.POS_NEG) {
			offset *= -1;
		}
		p.setBounds(currentPosition.x + offset, currentPosition.y, w, h);
	}

	private void layoutChildren(Parent p) {
		Vector2 initialPosition = currentPosition.cpy();

		// move currentPosition one level down
		float h = config.getOriginalObjectHeight() * getScaling();
		if (config.getVerticalGrowth() == TreeGrowth.NEG_POS) {
			// TODO apply scaling on padding?
			currentPosition.y += h + config.getVerticalPadding() * getScaling();
		} else {
			// TODO apply scaling on padding?
			currentPosition.y -= h + config.getVerticalPadding() * getScaling();
		}

		// iterate over the children and layout them depending on the horizontal
		// grow direction
		goDeeper();
		Iterator<InternalBoardObject> it = p.iterator();
		if (config.getHorizontalGrowth() == TreeGrowth.NEG_POS) {
			while (it.hasNext()) {
				InternalBoardObject child = it.next();
				child.accept(this);
				currentPosition.x += getScaling() * widthMap.get(child);
				if (it.hasNext()) {
					// TODO apply scaling on padding?
					currentPosition.x += getScaling() * config.getHorizontalPadding();
				}
			}
		} else {
			while (it.hasNext()) {
				InternalBoardObject child = it.next();
				child.accept(this);
				currentPosition.x -= getScaling() * widthMap.get(child);
				if (it.hasNext()) {
					// TODO apply scaling on padding?
					currentPosition.x -= getScaling() * config.getHorizontalPadding();
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
		this.scaling *= config.getVerticaleScaleFactor();
	}

	/**
	 * Leave the current level inside the syntax tree
	 */
	private void goHigher() {
		this.scaling /= config.getVerticaleScaleFactor();
	}
}
