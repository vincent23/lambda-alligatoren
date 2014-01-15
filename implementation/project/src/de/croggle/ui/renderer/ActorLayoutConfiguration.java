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
	private float verticalScaleFactor;
	private float uniformObjectWidth;
	private float uniformObjectHeight;
	private Vector2 treeOrigin;
	private float horizontalPadding;
	private float verticalPadding;

	private float eggWidth;
	private float eggHeight;
	private float agedAlligatorWidth;
	private float agedAlligatorHeight;
	private float coloredAlligatorWidth;
	private float coloredAlligatorHeight;

	/**
	 * 
	 */
	public ActorLayoutConfiguration() {
		this.horizontalGrowth = TreeGrowth.NEG_POS;
		this.verticalGrowth = TreeGrowth.POS_NEG;

		this.colorController = null;
		this.verticalScaleFactor = .75f;
		
		this.treeOrigin = new Vector2(0, 0);
		this.horizontalPadding = 0;
		this.verticalPadding = 0;

		eggWidth = 100;
		eggHeight = 70;

		agedAlligatorWidth = 150;
		agedAlligatorHeight = 70;
		
		coloredAlligatorWidth = agedAlligatorWidth;
		coloredAlligatorHeight = 80;
		
		this.uniformObjectWidth = 150;
		this.uniformObjectHeight = 80;
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
			float verticalScaleFactor, float horizontalPadding,
			float verticalPadding, ColorController controller, float eggWidth,
			float eggHeight, float agedAlligatorWidth,
			float agedAlligatorHeight, float coloredAlligatorWidth,
			float coloredAlligatorHeight) {
		this.treeOrigin = treeOrigin;
		this.horizontalGrowth = horizontalGrowth;
		this.verticalGrowth = verticalGrowth;
		this.verticalScaleFactor = verticalScaleFactor;

		this.horizontalPadding = horizontalPadding;
		this.verticalPadding = verticalPadding;
		this.colorController = controller;

		this.eggWidth = eggWidth;
		this.eggHeight = eggHeight;
		this.agedAlligatorWidth = agedAlligatorWidth;
		this.agedAlligatorHeight = agedAlligatorHeight;
		this.coloredAlligatorWidth = coloredAlligatorWidth;
		this.coloredAlligatorHeight = coloredAlligatorHeight;

		this.uniformObjectWidth = Math.max(Math.max(eggWidth, coloredAlligatorWidth), agedAlligatorWidth);
		this.uniformObjectHeight = Math.max(Math.max(eggHeight, coloredAlligatorHeight), agedAlligatorHeight);
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
	public float getVerticalScaleFactor() {
		return verticalScaleFactor;
	}

	/**
	 * 
	 * @param verticaleScaleFactor
	 */
	public void setVerticalScaleFactor(float verticaleScaleFactor) {
		this.verticalScaleFactor = verticaleScaleFactor;
	}

	/**
	 * 
	 * @return
	 */
	public float getUniformObjectWidth() {
		return uniformObjectWidth;
	}

	/**
	 * 
	 * @param originalObjectWidth
	 */
	/*
	public void setUniformObjectWidth(float originalObjectWidth) {
		this.uniformObjectWidth = originalObjectWidth;
	}
	*/

	/**
	 * 
	 * @return
	 */
	public float getUniformObjectHeight() {
		return uniformObjectHeight;
	}

	/**
	 * 
	 * @param originalObjectHeight
	 */
	/*
	public void setUniformObjectHeight(float originalObjectHeight) {
		this.uniformObjectHeight = originalObjectHeight;
	}
	*/

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

	private void newWidth(float width) {
		this.uniformObjectWidth = Math.max(width, uniformObjectWidth);
	}
	
	private void newHeight(float height) {
		this.uniformObjectHeight = Math.max(height, uniformObjectHeight);
	}
	
	/**
	 * 
	 * @return
	 */
	public float getEggWidth() {
		return eggWidth;
	}

	/**
	 * 
	 * @param eggWidth
	 */
	public void setEggWidth(float eggWidth) {
		this.eggWidth = eggWidth;
		newWidth(eggWidth);
	}

	/**
	 * 
	 * @return
	 */
	public float getEggHeight() {
		return eggHeight;
	}

	/**
	 * 
	 * @param eggHeight
	 */
	public void setEggHeight(float eggHeight) {
		this.eggHeight = eggHeight;
		newHeight(eggHeight);
	}

	/**
	 * 
	 * @return
	 */
	public float getAgedAlligatorWidth() {
		return agedAlligatorWidth;
	}

	/**
	 * 
	 * @param agedAlligatorWidth
	 */
	public void setAgedAlligatorWidth(float agedAlligatorWidth) {
		this.agedAlligatorWidth = agedAlligatorWidth;
		newWidth(agedAlligatorWidth);
	}

	/**
	 * 
	 * @return
	 */
	public float getAgedAlligatorHeight() {
		return agedAlligatorHeight;
	}

	/**
	 * 
	 * @param agedAlligatorHeight
	 */
	public void setAgedAlligatorHeight(float agedAlligatorHeight) {
		this.agedAlligatorHeight = agedAlligatorHeight;
		newHeight(agedAlligatorHeight);
	}

	/**
	 * 
	 * @return
	 */
	public float getColoredAlligatorWidth() {
		return coloredAlligatorWidth;
	}

	/**
	 * 
	 * @param coloredAlligatorWidth
	 */
	public void setColoredAlligatorWidth(float coloredAlligatorWidth) {
		this.coloredAlligatorWidth = coloredAlligatorWidth;
		newWidth(coloredAlligatorWidth);
	}

	/**
	 * 
	 * @return
	 */
	public float getColoredAlligatorHeight() {
		return coloredAlligatorHeight;
	}

	/**
	 * 
	 * @param coloredAlligatorHeight
	 */
	public void setColoredAlligatorHeight(float coloredAlligatorHeight) {
		this.coloredAlligatorHeight = coloredAlligatorHeight;
		newHeight(coloredAlligatorHeight);
	}
}
