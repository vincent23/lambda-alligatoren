package de.croggle.ui.renderer;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.croggle.data.AssetManager;
import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.operations.FlattenTree;
import de.croggle.game.event.BoardEventListener;

class BoardActorBoardChangeAnimator implements BoardEventListener {
	private final BoardActor b;
	private boolean firstRebuild = true;

	public BoardActorBoardChangeAnimator(BoardActor b) {
		this.b = b;
	}

	/**
	 * Visualizes the recoloring of an object on the board.
	 * 
	 * @param recoloredObject
	 *            the object that has been recolored
	 */
	@Override
	public void onObjectRecolored(ColoredBoardObject recoloredObject) {
		BoardObjectActor actor = b.getLayout().getActor(recoloredObject);
		if (actor != null) {
			/*
			 * TODO unnecessary if recolor events were fired at the right moment
			 */
			ColoredBoardObjectActor cboa = (ColoredBoardObjectActor) actor;
			cboa.invalidate();
		}
	}

	/**
	 * Visualizes the process of one alligator eating another and its children,
	 * or just an egg, on the board.
	 * 
	 * @param eater
	 *            the alligator which eats the other alligator
	 * @param eatenFamily
	 *            the family which is eaten by the other alligator
	 */
	@Override
	public void onEat(final ColoredAlligator eater,
			final InternalBoardObject eatenFamily, int eatenParentPosition) {
		ColoredAlligatorActor eaterActor = ((ColoredAlligatorActor) b
				.getLayout().getActor(eater));
		eaterActor.enterEatingState();
		final List<InternalBoardObject> eatenLst = FlattenTree
				.toList(eatenFamily);

		final float animDuration = 0.2f;

		BoardObjectActor actor;
		for (InternalBoardObject eaten : eatenLst) {
			actor = b.getLayout().getActor(eaten);
			// automatically pooled actions, sooo convenient...
			MoveToAction moveAction = Actions.moveTo(eaterActor.getX(),
					eaterActor.getY(), animDuration);
			actor.addAction(moveAction);
			ScaleToAction scaleAction = Actions.scaleTo(0, 0, animDuration);
			actor.addAction(scaleAction);
		}

		b.addAction(new TemporalAction() {
			@Override
			protected void begin() {
				setDuration(animDuration);
			}

			@Override
			protected void update(float percent) {
				// do nothing
			}

			@Override
			protected void end() {
				BoardObjectActor eatenActor;
				for (InternalBoardObject eaten : eatenLst) {
					eatenActor = b.getLayout().getActor(eaten);
					b.getLayout().removeActor(eatenActor);
					b.removeFromWorld(eatenActor);
				}
				// the onAged event is responsible for this now
				// removeObjectAnimated(eater);
				applyDeltasAnimated(b.getLayout().getDeltasToFix());
			}
		});
	}

	/**
	 * Removes
	 * 
	 * @param object
	 */
	private void removeObjectAnimated(final InternalBoardObject object) {
		final float fadingtime = .3f;
		BoardObjectActor ba = b.getLayout().getActor(object);
		ba.addAction(Actions.fadeOut(fadingtime));
		b.addAction(new TemporalAction() {
			@Override
			protected void begin() {
				setDuration(fadingtime);
			}

			@Override
			protected void update(float percent) {
				// do nothing
			}

			@Override
			protected void end() {
				BoardObjectActor actor = b.getLayout().getActor(object);
				b.getLayout().removeActor(actor);
				b.removeFromWorld(actor);
				applyDeltasAnimated(b.getLayout().getDeltasToFix());
			}
		});
	}

	/**
	 * Visualizes the disappearance of an aged alligator on the board.
	 * 
	 * @param alligator
	 *            the alligator which disappeared
	 */
	@Override
	public void onAgedAlligatorVanishes(AgedAlligator alligator,
			int positionInParent) {
		removeObjectAnimated(alligator);
	}

	/**
	 * Completely rebuilds the board as it is seen on the screen.
	 * 
	 * @param board
	 *            the board that is going to replace the board that was seen
	 *            previously
	 */
	@Override
	public final void onBoardRebuilt(Board board) {
		if (firstRebuild) {
			firstRebuild = false;
		} else {
			// TODO dirrrty
			Image flash = new Image(AssetManager.getInstance().getColorTexture(
					Color.uncolored()));
			flash.setFillParent(true);
			b.addToActor(flash);
			flash.validate();
			flash.addAction(Actions.alpha(0.f, 0.4f));
			flash.addAction(Actions.delay(0.4f, Actions.removeActor()));
		}

		b.clearWorld();
		b.setLayout(ActorLayoutBuilder.build(board, b.getLayoutConfiguration()));
		for (BoardObjectActor actor : b.getLayout()) {
			b.addToWorld(actor);
		}
		b.updateListeners();
	}

	/**
	 * Visualizes the process of replacing an egg within a family with the
	 * family the protecting alligator has eaten.
	 * 
	 * @param replacedEgg
	 *            the hatching egg
	 * @param bornFamily
	 *            the family that hatches from that egg
	 */
	@Override
	public void onHatched(Egg replacedEgg, InternalBoardObject bornFamily) {
		EggActor eggActor = (EggActor) b.getLayout().getActor(replacedEgg);
		eggActor.enterHatchingState();
		removeObjectAnimated(replacedEgg);
		applyDeltasAnimated(b.getLayout().getDeltasToFix());
		b.boardSizeChanged();
	}

	private void applyDeltasAnimated(List<ActorDelta> deltas) {
		List<ActorDelta> created = new ArrayList<ActorDelta>();
		for (ActorDelta delta : deltas) {
			if (delta.isCreated()) {
				created.add(delta);
				continue;
			}
			applyDeltaAnimated(delta);
		}
		applyCreationDeltas(created);
	}

	private void applyDeltaAnimated(ActorDelta delta) {
		final float moveToDuration = 0.3f;
		final float sizeToDuration = 0.3f;

		Actor actor = delta.getActor();
		if (delta.isxChanged()) {
			MoveToAction moveTo;
			if (delta.isyChanged()) {
				moveTo = Actions.moveTo(delta.getNewX(), delta.getNewY(),
						moveToDuration);
			} else {
				moveTo = Actions.moveTo(delta.getNewX(), actor.getY(),
						moveToDuration);
			}
			actor.addAction(moveTo);
		} else if (delta.isyChanged()) {
			MoveToAction moveTo = Actions.moveTo(actor.getX(), delta.getNewY(),
					moveToDuration);
			actor.addAction(moveTo);
		}

		if (delta.isWidthChanged()) {
			SizeToAction sizeTo;
			if (delta.isHeightChanged()) {
				sizeTo = Actions.sizeTo(delta.getNewWidth(),
						delta.getNewHeight(), sizeToDuration);
			} else {
				sizeTo = Actions.sizeTo(delta.getNewWidth(), actor.getHeight(),
						sizeToDuration);
			}
			actor.addAction(sizeTo);
		} else if (delta.isyChanged()) {
			SizeToAction sizeTo = Actions.sizeTo(actor.getWidth(),
					delta.getNewHeight(), sizeToDuration);
			actor.addAction(sizeTo);
		}
	}

	private void applyCreationDeltas(final List<ActorDelta> deltas) {
		float animDuration = 0.2f;

		BoardObjectActor actor;
		for (ActorDelta delta : deltas) {
			actor = delta.getActor();
			actor.setScale(0.f);
			b.getLayout().addActor(actor);
			b.addToWorld(actor);
			ScaleToAction scaleAction = Actions.scaleTo(1, 1, animDuration);
			actor.addAction(scaleAction);
		}
	}

	@Override
	public void onAge(ColoredAlligator colored, AgedAlligator aged) {
		final float animationDuration = 0.3f;

		BoardObjectActor coloredActor = b.getLayout().getActor(colored);
		AgedAlligatorActor agedActor = new AgedAlligatorActor(aged);
		agedActor.setSize(coloredActor.getWidth(), coloredActor.getHeight());
		agedActor.setPosition(coloredActor.getX(), coloredActor.getY());
		agedActor.setColor(1.f, 1.f, 1.f, 0.f);
		agedActor.addAction(Actions.alpha(1.f, animationDuration));
		b.getLayout().addActor(agedActor);
		b.addToWorld(agedActor);
		removeObjectAnimated(colored);
	}

	@Override
	public void onObjectPlaced(InternalBoardObject placed) {
		applyDeltasAnimated(b.getLayout().getDeltasToFix());
		b.boardSizeChanged();
	}

	@Override
	public void onObjectRemoved(InternalBoardObject removed) {
		BoardObjectActor removedActor = b.getLayout().getActor(removed);
		if (removedActor != null) {
			b.removeFromWorld(removedActor);
			b.getLayout().removeActor(removedActor);
			applyDeltasAnimated(b.getLayout().getDeltasToFix());
			b.boardSizeChanged();
		}
	}

	@Override
	public void onObjectMoved(InternalBoardObject moved) {
		applyDeltasAnimated(b.getLayout().getDeltasToFix());
		b.boardSizeChanged();
	}

}
