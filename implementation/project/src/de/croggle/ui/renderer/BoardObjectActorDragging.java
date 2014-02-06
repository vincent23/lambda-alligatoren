package de.croggle.ui.renderer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.operations.BoardObjectVisitor;

class BoardObjectActorDragging {

	private final float autoPanBorderWidth;
	private final float autoPanBorderHeight;

	protected final BoardActor b;

	protected final ColoredAlligatorActor coloredDragActor;
	protected final EggActor eggDragActor;
	protected final AgedAlligatorActor agedDragActor;

	protected final ColoredAlligatorActor coloredValidDragActor;
	protected final EggActor eggValidDragActor;
	protected final AgedAlligatorActor agedValidDragActor;

	protected final ColoredAlligatorActor coloredInvalidDragActor;
	protected final EggActor eggInvalidDragActor;
	protected final AgedAlligatorActor agedInvalidDragActor;

	public BoardObjectActorDragging(BoardActorLayoutEditing editing) {
		this.b = editing.getBoardActor();

		// TODO maybe bind the value somehow to BoardActor size changes
		autoPanBorderWidth = Math.min(b.getWidth() / 2, 150);
		autoPanBorderHeight = Math.min(b.getHeight() / 2, 100);

		boolean colorBlind = b.getLayout().getLayoutConfiguration()
				.isColorBlindEnabled();

		{
			coloredDragActor = new ColoredAlligatorActor(new ColoredAlligator(
					false, false, Color.uncolored(), false), colorBlind);
			coloredDragActor.addAction(new AutoPanAction());
			eggDragActor = new EggActor(new Egg(false, false,
					Color.uncolored(), false), colorBlind);
			eggDragActor.addAction(new AutoPanAction());
			agedDragActor = new AgedAlligatorActor(new AgedAlligator(false,
					false));
			agedDragActor.addAction(new AutoPanAction());
		}

		{
			coloredValidDragActor = new ColoredAlligatorActor(
					new ColoredAlligator(false, false, Color.uncolored(), false),
					colorBlind);
			coloredValidDragActor.setColor(0.f, 1.f, 0.f, 1.f);
			coloredValidDragActor.addAction(new AutoPanAction());
			eggValidDragActor = new EggActor(new Egg(false, false,
					Color.uncolored(), false), colorBlind);
			eggValidDragActor.setColor(0.f, 1.f, 0.f, 1.f);
			eggValidDragActor.addAction(new AutoPanAction());
			agedValidDragActor = new AgedAlligatorActor(new AgedAlligator(
					false, false));
			agedValidDragActor.setColor(0.f, 1.f, 0.f, 1.f);
			agedValidDragActor.addAction(new AutoPanAction());
		}

		{
			coloredInvalidDragActor = new ColoredAlligatorActor(
					new ColoredAlligator(false, false, Color.uncolored(), false),
					colorBlind);
			coloredInvalidDragActor.setColor(1.f, 0.f, 0.f, 1.f);
			coloredInvalidDragActor.addAction(new AutoPanAction());
			eggInvalidDragActor = new EggActor(new Egg(false, false,
					Color.uncolored(), false), colorBlind);
			eggInvalidDragActor.setColor(1.f, 0.f, 0.f, 1.f);
			eggInvalidDragActor.addAction(new AutoPanAction());
			agedInvalidDragActor = new AgedAlligatorActor(new AgedAlligator(
					false, false));
			agedInvalidDragActor.setColor(1.f, 0.f, 0.f, 1.f);
			agedInvalidDragActor.addAction(new AutoPanAction());
		}
	}

	public class AutoPanAction extends Action {

		// only act once in %divider% milliseconds
		private final float divider = 0.01f;

		private final float xDistance = 2;
		private final float yDistance = 2;

		private float timePassed;

		@Override
		public boolean act(float delta) {
			if (timePassed >= divider) {
				timePassed = 0;
				Actor a = getActor();
				float x = a.getX();
				float y = a.getY();
				float maxx = x + a.getWidth();
				float maxy = y + a.getHeight();
				float w = b.getWidth();
				float h = b.getHeight();
				Vector2 pMin = new Vector2(x, y);
				Vector2 pMax = new Vector2(maxx, maxy);
				pMin = b.stageToLocalCoordinates(pMin);
				pMax = b.stageToLocalCoordinates(pMax);
				if (pMin.x <= autoPanBorderWidth) {
					if (pMax.x < w - autoPanBorderWidth) {
						b.panActorCoords(xDistance, 0);
					}
				} else if (pMax.x >= w - autoPanBorderWidth) {
					b.panActorCoords(-xDistance, 0);
				}

				if (pMin.y <= autoPanBorderHeight) {
					if (pMax.y < h - autoPanBorderHeight) {
						b.panActorCoords(0, yDistance);
					}
				} else if (pMax.y >= h - autoPanBorderHeight) {
					b.panActorCoords(0, -yDistance);
				}
			}
			timePassed += delta;

			// never end
			return false;
		}
	}

	public BoardObjectActor getDragActor(final BoardObjectActor a) {
		final BoardObjectActor result[] = new BoardObjectActor[1];
		BoardObjectVisitor visitor = new BoardObjectVisitor() {
			@Override
			public void visitEgg(Egg egg) {
				((Egg) eggDragActor.getBoardObject()).setColor(egg.getColor());
				eggDragActor.validate();
				eggDragActor.setSize(a.getWidth() * b.getZoom(), a.getHeight()
						* b.getZoom());
				result[0] = eggDragActor;
			}

			@Override
			public void visitColoredAlligator(ColoredAlligator alligator) {
				((ColoredAlligator) coloredDragActor.getBoardObject())
						.setColor(alligator.getColor());
				coloredDragActor.validate();
				coloredDragActor.setSize(a.getWidth() * b.getZoom(),
						a.getHeight() * b.getZoom());
				result[0] = coloredDragActor;
			}

			@Override
			public void visitBoard(Board board) {
				// Just ignore
			}

			@Override
			public void visitAgedAlligator(AgedAlligator alligator) {
				result[0] = agedDragActor;
			}
		};
		a.getBoardObject().accept(visitor);

		result[0].setSize(a.getWidth(), a.getHeight());
		result[0].setScale(b.getZoom());

		return result[0];
	}

	public BoardObjectActor getValidDragActor(final BoardObjectActor a) {
		final BoardObjectActor result[] = new BoardObjectActor[1];
		BoardObjectVisitor visitor = new BoardObjectVisitor() {
			@Override
			public void visitEgg(Egg egg) {
				((Egg) eggValidDragActor.getBoardObject()).setColor(egg
						.getColor());
				eggValidDragActor.validate();
				eggValidDragActor.setSize(a.getWidth() * b.getZoom(),
						a.getHeight() * b.getZoom());
				result[0] = eggValidDragActor;
			}

			@Override
			public void visitColoredAlligator(ColoredAlligator alligator) {
				((ColoredAlligator) coloredValidDragActor.getBoardObject())
						.setColor(alligator.getColor());
				coloredValidDragActor.validate();
				coloredValidDragActor.setSize(a.getWidth() * b.getZoom(),
						a.getHeight() * b.getZoom());
				result[0] = coloredValidDragActor;
			}

			@Override
			public void visitBoard(Board board) {
				// Just ignore
			}

			@Override
			public void visitAgedAlligator(AgedAlligator alligator) {
				result[0] = agedValidDragActor;
			}
		};
		a.getBoardObject().accept(visitor);

		result[0].setSize(a.getWidth(), a.getHeight());
		result[0].setScale(b.getZoom());

		return result[0];
	}

	public BoardObjectActor getInvalidDragActor(final BoardObjectActor a) {
		final BoardObjectActor result[] = new BoardObjectActor[1];
		BoardObjectVisitor visitor = new BoardObjectVisitor() {
			@Override
			public void visitEgg(Egg egg) {
				((Egg) eggInvalidDragActor.getBoardObject()).setColor(egg
						.getColor());
				eggInvalidDragActor.validate();
				eggInvalidDragActor.setSize(a.getWidth() * b.getZoom(),
						a.getHeight() * b.getZoom());
				result[0] = eggInvalidDragActor;
			}

			@Override
			public void visitColoredAlligator(ColoredAlligator alligator) {
				((ColoredAlligator) coloredInvalidDragActor.getBoardObject())
						.setColor(alligator.getColor());
				coloredInvalidDragActor.validate();
				coloredInvalidDragActor.setSize(a.getWidth() * b.getZoom(),
						a.getHeight() * b.getZoom());
				result[0] = coloredInvalidDragActor;
			}

			@Override
			public void visitBoard(Board board) {
				// Just ignore
			}

			@Override
			public void visitAgedAlligator(AgedAlligator alligator) {
				result[0] = agedInvalidDragActor;
			}
		};
		a.getBoardObject().accept(visitor);

		result[0].setSize(a.getWidth(), a.getHeight());
		result[0].setScale(b.getZoom());

		return result[0];
	}
}
