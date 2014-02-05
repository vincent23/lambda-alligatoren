package de.croggle.game.board.operations;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.event.BoardEventMessenger;

public class RemoveUselessAgedAlligators implements BoardObjectVisitor {
	private BoardEventMessenger boardMessenger;

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
	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		if (alligator.getParent().getFirstChild() == alligator) {
			alligator.getParent().removeChild(alligator);
			alligator.acceptOnChildren(this);
			int i = 0;
			for (InternalBoardObject child : alligator) {
				alligator.getParent().insertChild(child, i);
				i++;
			}
			this.boardMessenger.notifyAgedAlligatorVanishes(alligator, 0);
		}
	}

	@Override
	public void visitBoard(Board board) {
		board.acceptOnChildren(this);
	}

}
