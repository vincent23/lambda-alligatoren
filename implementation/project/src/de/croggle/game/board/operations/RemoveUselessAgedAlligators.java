package de.croggle.game.board.operations;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.event.BoardEventMessenger;

public class RemoveUselessAgedAlligators implements BoardObjectVisitor {
	private final BoardEventMessenger boardMessenger;

	private RemoveUselessAgedAlligators(BoardEventMessenger boardMessenger) {
		this.boardMessenger = boardMessenger;
	}

	public static void remove(BoardObject family,
			BoardEventMessenger boardMessenger) {
		RemoveUselessAgedAlligators visitor = new RemoveUselessAgedAlligators(
				boardMessenger);
		family.accept(visitor);
	}

	@Override
	public void visitEgg(Egg egg) {
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		alligator.acceptOnChildren(this);
		checkChildren(alligator);
	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		alligator.acceptOnChildren(this);
		checkChildren(alligator);
	}

	@Override
	public void visitBoard(Board board) {
		board.acceptOnChildren(this);
		checkChildren(board);
	}

	private void checkChildren(Parent p) {
		int firstNotEggPosition = 0;
		while (firstNotEggPosition < p.getChildCount()
				&& p.getChildAtPosition(firstNotEggPosition).getClass() == Egg.class) {
			firstNotEggPosition++;
		}
		if (firstNotEggPosition < p.getChildCount()) {
			InternalBoardObject firstNotEgg = p
					.getChildAtPosition(firstNotEggPosition);

			if (firstNotEgg.getClass() == AgedAlligator.class) {
				int i = 0;
				for (InternalBoardObject child : (AgedAlligator) firstNotEgg) {
					p.insertChild(child, firstNotEggPosition + i);
					i++;
				}
				p.removeChild(firstNotEgg);
				this.boardMessenger.notifyAgedAlligatorVanishes(
						(AgedAlligator) firstNotEgg, 0);
			}
		}
	}
}
