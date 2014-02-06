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
	private BoardEventMessenger boardMessenger;
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
		acceptOnChildrenWithBreak(alligator);
	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		if (alligator.getParent().getFirstChild() == alligator) {
			found = true;
			alligator.getParent().removeChild(alligator);
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
		acceptOnChildrenWithBreak(board);
	}

	private void acceptOnChildrenWithBreak(Parent parent) {
		for (InternalBoardObject child : parent) {
			child.accept(this);
			if (found) {
				break;
			}
		}
	}

}
