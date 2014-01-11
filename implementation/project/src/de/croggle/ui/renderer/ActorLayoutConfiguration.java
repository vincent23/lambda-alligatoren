package de.croggle.ui.renderer;

import com.badlogic.gdx.math.Vector2;

import de.croggle.game.ColorController;

/**
 * 
 * WARNING: Code assumes that the given widths and heights are directed the same
 * direction as the respective TreeGrowths are set. That means, while rendering
 * the drawn textures MUST extend in the direction the TreeGrowths are set.
 */
public class ActorLayoutConfiguration {
	private TreeGrowth horizontalGrowth;
	private TreeGrowth verticalGrowth;
	private ColorController colorController;
	private float verticaleScaleFactor;
	private float originalObjectWidth;
	private float originalObjectHeight;
	private Vector2 treeOrigin;
	private float horizontalPadding;
	private float verticalPadding;

	/**
	 * 
	 */
	public ActorLayoutConfiguration() {
		this.horizontalGrowth = TreeGrowth.NEG_POS;
		this.verticalGrowth = TreeGrowth.POS_NEG;

		this.colorController = null;
		this.verticaleScaleFactor = .75f;
		this.originalObjectHeight = 100;
		this.originalObjectWidth = 150;
		this.treeOrigin = new Vector2(0, 0);
		this.horizontalPadding = 2;
		this.verticalPadding = 1;
	}

	/**
	 * 
	 * @param treeOrigin
	 * @param horizontalGrowth
	 *            the direction the tree will grow in horizontally
	 * @param verticalGrowth
	 *            the direction the tree will grow in vertically
	 * @param verticalScaleFactor
	 *            the relative size of a child as compared to its parent.
	 * @param originalObjectWidth
	 *            the width that the widest type of BoardObjectActor has in
	 *            pixels. Used to allocate space for new BoardObjectActors.
	 *            Facilitates layouting.
	 * @param originalObjectHeight
	 *            the height that the highest type of BoardObjectActor has in
	 *            pixels. Used to allocate space for new BoardObjectActors.
	 *            Facilitates layouting.
	 * @param horizontalPadding
	 *            the number of pixels padding between two horizontally
	 *            neighboring actors.
	 * 
	 * @param verticalPadding
	 *            the number of pixels padding between two vertically
	 *            neighboring actors.
	 * @param controller
	 */
	public ActorLayoutConfiguration(Vector2 treeOrigin,
			TreeGrowth horizontalGrowth, TreeGrowth verticalGrowth,
			float verticalScaleFactor, float originalObjectWidth,
			float originalObjectHeight, float horizontalPadding,
			float verticalPadding, ColorController controller) {
		this.treeOrigin = treeOrigin;
		this.horizontalGrowth = horizontalGrowth;
		this.verticalGrowth = verticalGrowth;
		this.verticaleScaleFactor = verticalScaleFactor;
		this.originalObjectWidth = originalObjectWidth;
		this.originalObjectHeight = originalObjectHeight;
		this.horizontalPadding = horizontalPadding;
		this.verticalPadding = verticalPadding;
		this.colorController = controller;

	}

	/**
	 * 
	 * @return
	 */
	public TreeGrowth getHorizontalGrowth() {
		return horizontalGrowth;
	}

	/**
	 * 
	 * @param horizontalGrowth
	 */
	public void setHorizontalGrowth(TreeGrowth horizontalGrowth) {
		this.horizontalGrowth = horizontalGrowth;
	}

	/**
	 * 
	 * @return
	 */
	public TreeGrowth getVerticalGrowth() {
		return verticalGrowth;
	}

	/**
	 * 
	 * @param verticalGrowth
	 */
	public void setVerticalGrowth(TreeGrowth verticalGrowth) {
		this.verticalGrowth = verticalGrowth;
	}

	/**
	 * 
	 * @return
	 */
	public ColorController getColorController() {
		return colorController;
	}

	/**
	 * 
	 * @param colorController
	 */
	public void setColorController(ColorController colorController) {
		this.colorController = colorController;
	}

	/**
	 * 
	 * @return
	 */
	public float getVerticaleScaleFactor() {
		return verticaleScaleFactor;
	}

	/**
	 * 
	 * @param verticaleScaleFactor
	 */
	public void setVerticaleScaleFactor(float verticaleScaleFactor) {
		this.verticaleScaleFactor = verticaleScaleFactor;
	}

	/**
	 * 
	 * @return
	 */
	public float getOriginalObjectWidth() {
		return originalObjectWidth;
	}

	/**
	 * 
	 * @param originalObjectWidth
	 */
	public void setOriginalObjectWidth(float originalObjectWidth) {
		this.originalObjectWidth = originalObjectWidth;
	}

	/**
	 * 
	 * @return
	 */
	public float getOriginalObjectHeight() {
		return originalObjectHeight;
	}

	/**
	 * 
	 * @param originalObjectHeight
	 */
	public void setOriginalObjectHeight(float originalObjectHeight) {
		this.originalObjectHeight = originalObjectHeight;
	}

	/**
	 * 
	 * @return
	 */
	public Vector2 getTreeOrigin() {
		return treeOrigin;
	}

	/**
	 * 
	 * @param treeOrigin
	 */
	public void setTreeOrigin(Vector2 treeOrigin) {
		this.treeOrigin = treeOrigin;
	}

	/**
	 * 
	 * @return
	 */
	public float getHorizontalPadding() {
		return horizontalPadding;
	}

	/**
	 * 
	 * @param horizontalPadding
	 */
	public void setHorizontalPadding(float horizontalPadding) {
		this.horizontalPadding = horizontalPadding;
	}

	/**
	 * 
	 * @return
	 */
	public float getVerticalPadding() {
		return verticalPadding;
	}

	/**
	 * 
	 * @param verticalPadding
	 */
	public void setVerticalPadding(float verticalPadding) {
		this.verticalPadding = verticalPadding;
	}
}
