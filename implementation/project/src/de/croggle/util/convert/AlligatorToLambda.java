package de.croggle.util.convert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.board.operations.BoardObjectVisitor;

public class AlligatorToLambda implements BoardObjectVisitor {

	private Map<Color, String> names;
	private String result;

	private AlligatorToLambda() {
		names = new HashMap<Color, String>();
	}

	public static String convert(BoardObject b) {
		AlligatorToLambda converter = new AlligatorToLambda();
		return converter.toString(b);
	}

	private String toString(BoardObject b) {
		result = "";
		b.accept(this);
		return result;
	}

	private String colorToName(Color c) {
		if (names.containsKey(c)) {
			return names.get(c);
		} else {
			String name;
			int n = names.size();
			if (n < 3) {
				name = "" + (char) ('x' + n);
			} else if (n < 11) {
				name = "" + (char) ('p' + n - 3);
			} else if (n < 26) {
				name = "" + (char) ('a' + n - 11);
			} else {
				// TODO this will result in crap
				name = "" + (char) ('α' + n - 26);
			}
			this.names.put(c, name);
			return name;
		}
	}

	@Override
	public void visitEgg(Egg egg) {
		result += colorToName(egg.getColor());
	}

	private void visitParent(Parent p, boolean parenthesis) {
		if (p.getChildCount() <= 1) {
			p.acceptOnChildren(this);
		} else {
			if (parenthesis) {
				result += "(";
			}
			Iterator<InternalBoardObject> i = p.iterator();
			while (i.hasNext()) {
				i.next().accept(this);
				if (i.hasNext()) {
					result += " ";
				}
			}
			if (parenthesis) {
				result += ")";
			}
		}
	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		visitParent(alligator, true);
	}

	@Override
	public void visitBoard(Board board) {
		visitParent(board, false);
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		boolean par = !alligator.getParent().isLastChild(alligator);
		if (par) {
			result += "(";
		}
		result += "λ" + colorToName(alligator.getColor()) + ".";
		visitParent(alligator, false);
		if (par) {
			result += ")";
		}
	}

}
