package de.croggle.ui.renderer;

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

abstract class ActorLayouter implements BoardObjectVisitor {
	protected abstract AgedAlligatorActor provideAgedAlligatorActor(AgedAlligator alligator);
	protected abstract ColoredAlligatorActor provideColoredAlligatorActor(ColoredAlligator alligator);
	protected abstract EggActor provideEggActor(Egg egg);
	/**
	 * Called before any children are layouted
	 * @param actor
	 */
	protected void notifyAgedAlligatorLayouted(AgedAlligatorActor actor) {
	}
	/**
	 * Called before any children are layouted
	 * @param actor
	 */
	protected void notifyColoredAlligatorLayouted(ColoredAlligatorActor actor) {
	}
	protected void notifyEggLayouted(EggActor actor) {
	}
	protected void notifyLayouted(BoardObjectActor actor) {
	}
	
	// settings
	/**
	 * The configuration used for adjusting the created layouts
	 */
	private final ActorLayoutConfiguration config;
	
	/**
	 * the board to operate on
	 */
	private final Board b;

	/**
	 * Map to access the width of any BoardObject occurring in the Board to
	 * build in O(1)
	 */
	protected final Map<BoardObject, Float> widthMap;
	
	/**
	 * The current scaling of newly added BoardObjectActors
	 */
	private float scaling = 1;
	
	/**
	 * Where newly added BoardObjectActors will be placed
	 */
	private Vector2 currentPosition;

	
	ActorLayouter(Board b, ActorLayoutConfiguration config) {
		this.config = config;
		this.b = b;
		this.widthMap = CreateWidthMap.create(b,
				config.getUniformObjectWidth(),
				config.getVerticalScaleFactor(), config.getHorizontalPadding());
		this.currentPosition = config.getTreeOrigin().cpy();
	}
	
	protected ActorLayoutConfiguration getConfig() {
		return config;
	}
	
	/**
	 * To be called before accessing this {@link ActorLayouter layouter's} results.
	 */
	protected void doLayout() {
		b.accept(this);
	}
	
	@Override
	public void visitEgg(Egg egg) {
		EggActor a = provideEggActor(egg);
		float offsetx = (config.getUniformObjectWidth() - config.getEggWidth())
				/ 2 * getScaling();
		if (config.getHorizontalGrowth() == TreeGrowth.POS_NEG) {
			offsetx *= -1;
		}

		float h = config.getEggHeight() * getScaling();
		float y = currentPosition.y;
		if (config.getVerticalGrowth() == TreeGrowth.NEG_POS
				&& config.getRenderDirectionY() == TreeGrowth.POS_NEG) {
			y += h;
		} else if (config.getVerticalGrowth() == TreeGrowth.POS_NEG
				&& config.getRenderDirectionY() == TreeGrowth.NEG_POS) {
			y -= h;
		}

		a.setBounds(currentPosition.x + offsetx, y, config.getEggWidth()
				* getScaling(), h);
		notifyEggLayouted(a);
		notifyLayouted(a);
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		ColoredAlligatorActor a = provideColoredAlligatorActor(alligator);
		setParentActorBounds(a, alligator);
		notifyColoredAlligatorLayouted(a);
		notifyLayouted(a);
		layoutChildren(alligator);
	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		AgedAlligatorActor a = provideAgedAlligatorActor(alligator);
		setParentActorBounds(a, alligator);
		notifyAgedAlligatorLayouted(a);
		notifyLayouted(a);
		layoutChildren(alligator);
	}

	@Override
	public void visitBoard(Board board) {
		Parent p = board;
		Iterator<InternalBoardObject> it = p.iterator();
		if (config.getHorizontalGrowth() == TreeGrowth.NEG_POS) {
			while (it.hasNext()) {
				InternalBoardObject child = it.next();
				if (config.getRenderDirectionX() == TreeGrowth.POS_NEG) {
					currentPosition.x += widthMap.get(child);
				}
				child.accept(this);
				if (config.getRenderDirectionX() == TreeGrowth.NEG_POS) {
					currentPosition.x += widthMap.get(child);
				}
				if (it.hasNext()) {
					currentPosition.x += config.getHorizontalPadding();
				}
			}
		} else {
			while (it.hasNext()) {
				InternalBoardObject child = it.next();
				if (config.getRenderDirectionX() == TreeGrowth.NEG_POS) {
					currentPosition.x -= widthMap.get(child);
				}
				child.accept(this);
				if (config.getRenderDirectionX() == TreeGrowth.POS_NEG) {
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
	private void setParentActorBounds(BoardObjectActor p, Parent parent) {
		double totalWidth = widthMap.get(parent);
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
				&& config.getRenderDirectionY() == TreeGrowth.POS_NEG) {
			y += h;
		} else if (config.getVerticalGrowth() == TreeGrowth.POS_NEG
				&& config.getRenderDirectionY() == TreeGrowth.NEG_POS) {
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
				if (config.getRenderDirectionX() == TreeGrowth.POS_NEG) {
					currentPosition.x += widthMap.get(child);
				}
				child.accept(this);
				if (config.getRenderDirectionX() == TreeGrowth.NEG_POS) {
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
				if (config.getRenderDirectionX() == TreeGrowth.NEG_POS) {
					currentPosition.x -= widthMap.get(child);
				}
				child.accept(this);
				if (config.getRenderDirectionX() == TreeGrowth.POS_NEG) {
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
