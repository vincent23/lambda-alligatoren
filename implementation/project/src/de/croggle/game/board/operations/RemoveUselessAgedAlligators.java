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
	private boolean found;

	private RemoveUselessAgedAlligators(BoardEventMessenger boardMessenger) {
		this.boardMessenger = boardMessenger;
		this.found = false;
	}

	public static void remove(BoardObject family,
			BoardEventMessenger boardMessenger) {
		RemoveUselessAgedAlligators visitor = new RemoveUselessAgedAlligators(
				boardMessenger);
		visitor.found = true;
		while (visitor.found != false) {
			visitor.found = false;
			family.accept(visitor);
		}
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
		InternalBoardObject first = p.getFirstChild();
		if (first.getClass() == AgedAlligator.class) {
			int i = 0;
			for (InternalBoardObject child : (AgedAlligator) first) {
				p.insertChild(child, i);
				i++;
			}
			p.removeChild(first);
			this.boardMessenger.notifyAgedAlligatorVanishes(
					(AgedAlligator) first, 0);
		}
	}
}
