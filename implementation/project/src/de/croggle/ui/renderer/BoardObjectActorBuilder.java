package de.croggle.ui.renderer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

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
public class BoardObjectActorBuilder implements BoardObjectVisitor {

	// settings
	/**
	 * The relative size of a child as compared to its parent.
	 */
	private final double scaleFactor;
	/**
	 * The maximum height a BoardObjectActor can have in pixels. Used to
	 * allocate space for new BoardObjectActors. Facilitates layouting.
	 */
	private final int objectHeight;
	/**
	 * The maximum width a BoardObjectActor can have in pixels. Used to allocate
	 * space for new BoardObjectActors. Facilitates layouting.
	 */
	private final int objectWidth;
	/**
	 * The number of pixels padding between two horizontally neighboring actors.
	 */
	private final int paddingHorizontal;
	/**
	 * The number of pixels padding between two vertically neighboring actors.
	 */
	private final int paddingVertical;
	/**
	 * Map to access the width of any BoardObject occurring in the Board to
	 * build in O(1)
	 */
	private final Map<BoardObject, Double> widthMap;
	/**
	 * The direction the tree will grow in horizontally
	 */
	private final TreeGrowth horizontalGrowDirection;
	/**
	 * The direction the tree will grow in vertically
	 */
	private final TreeGrowth verticalGrowDirection;

	// working variables
	/**
	 * The hashmap to store the result in
	 */
	private Map<InternalBoardObject, BoardObjectActor> actors;
	/**
	 * The current scaling of newly added BoardObjectActors
	 */
	private double scaling = 1;
	/**
	 * Where newly added BoardObjectActors will be placed
	 */
	private Vector2 currentPosition;

	private BoardObjectActorBuilder(Board b, Vector2 treeOrigin,
			int objectWidth, int objectHeight, double scalefactor,
			int paddingHorizontal, int paddingVertical,
			TreeGrowth horizontalGrowDirection, TreeGrowth verticalGrowDirection) {
		this.scaleFactor = scalefactor;
		this.paddingHorizontal = paddingHorizontal;
		this.paddingVertical = paddingVertical;
		this.objectWidth = objectWidth;
		this.objectHeight = objectHeight;
		this.horizontalGrowDirection = horizontalGrowDirection;
		this.verticalGrowDirection = verticalGrowDirection;
		this.actors = new HashMap<InternalBoardObject, BoardObjectActor>();
		this.widthMap = CreateWidthMap.create(b, objectWidth, scalefactor,
				paddingHorizontal);
		this.currentPosition = treeOrigin;
	}

	/**
	 * Creates a hashmap of {@link BoardObjectActor}s corresponding to elements
	 * in the given board, with the respective elements as keys. The
	 * {@link BoardObjectActor}s are already layouted according to the given
	 * layout options (rootPosition).
	 * 
	 * WARNING: The code assumes that the given widths and heights are directed
	 * the same direction as the respective TreeGrowths are set. That means, no
	 * additional space is reserved for the actors' widths and heights.
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
			Vector2 treeOrigin, int objectWidth, int objectHeight,
			double scalefactor, int paddingHorizontal, int paddingVertical,
			TreeGrowth horizontalGrowDirection, TreeGrowth verticalGrowDirection) {
		BoardObjectActorBuilder builder = new BoardObjectActorBuilder(b,
				treeOrigin, objectWidth, objectHeight, scalefactor,
				paddingHorizontal, paddingVertical, horizontalGrowDirection,
				verticalGrowDirection);
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
	public static Map<InternalBoardObject, BoardObjectActor> build(Board b) {
		return build(b, new Vector2(0.f, 0.f), 150, 100, 0.75, 2, 1,
				TreeGrowth.NEG_POS, TreeGrowth.POS_NEG);
	}

	@Override
	public void visitEgg(Egg egg) {
		BoardObjectActor a = new EggActor(egg);
		a.setBounds(currentPosition.x, currentPosition.y, objectWidth
				* getScaling(), objectHeight * getScaling());
		actors.put(egg, a);
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		ColoredAlligatorActor a = new ColoredAlligatorActor(alligator);
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
		layoutChildren(board);
	}

	private float getScaling() {
		return (float) scaling;
	}

	/**
	 * Place a ParentActor in the middle of the horizontal space allocated for
	 * it and its children
	 * 
	 * @param p
	 */
	private void setParentActorBounds(ParentActor p) {
		double totalWidth = widthMap.get(p);
		float w = objectWidth * getScaling();
		float h = objectHeight * getScaling();
		float offset = ((float) totalWidth + w) / 2.f;
		if (this.horizontalGrowDirection == TreeGrowth.POS_NEG) {
			offset *= -1;
		}
		p.setBounds(currentPosition.x + offset, currentPosition.y, w, h);
	}

	private void layoutChildren(Parent p) {
		Vector2 initialPosition = currentPosition.cpy();

		// move currentPosition one level down
		float h = objectHeight * getScaling();
		if (verticalGrowDirection == TreeGrowth.NEG_POS) {
			// TODO apply scaling on padding?
			currentPosition.y += h + paddingVertical * getScaling();
		} else {
			// TODO apply scaling on padding?
			currentPosition.y -= h + paddingVertical * getScaling();
		}

		// iterate over the children and layout them depending on the horizontal
		// grow direction
		goDeeper();
		Iterator<InternalBoardObject> it = p.iterator();
		if (horizontalGrowDirection == TreeGrowth.NEG_POS) {
			while (it.hasNext()) {
				InternalBoardObject child = it.next();
				child.accept(this);
				currentPosition.x += getScaling() * widthMap.get(child);
				if (it.hasNext()) {
					// TODO apply scaling on padding?
					currentPosition.x += getScaling() * paddingHorizontal;
				}
			}
		} else {
			while (it.hasNext()) {
				InternalBoardObject child = it.next();
				child.accept(this);
				currentPosition.x -= getScaling() * widthMap.get(child);
				if (it.hasNext()) {
					// TODO apply scaling on padding?
					currentPosition.x -= getScaling() * paddingHorizontal;
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
		this.scaling *= scaleFactor;
	}

	/**
	 * Leave the current level inside the syntax tree
	 */
	private void goHigher() {
		this.scaling /= scaleFactor;
	}
}
