package de.croggle.util.convert;

import java.util.Iterator;

import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import de.croggle.game.board.Parent;
import de.croggle.game.board.operations.BoardObjectVisitor;

/**
 * Helper class to format a given tree of BoardObjects as json.
 * 
 */
public class AlligatorToJson implements BoardObjectVisitor {

	private String result;
	private int depth;

	private AlligatorToJson() {
		result = "";
		depth = 0;
	}

	/**
	 * Performs the conversion between alligator constellations and their json
	 * formatted string representations.
	 * 
	 * @param b
	 *            the BoardOobject to be converted
	 * @return the converted json string
	 */
	public static String convert(BoardObject b) {
		AlligatorToJson converter = new AlligatorToJson();
		return converter.toJson(b);
	}

	private String toJson(BoardObject b) {
		result = "";
		depth = 0;
		b.accept(this);
		return result;
	}

	@Override
	public void visitEgg(Egg egg) {
		println("{");
		depth++;
		println("\"type\" : \"egg\",");
		println("\"color\" : " + egg.getColor().getId() + ",");
		println("\"movable\" : " + egg.isMovable() + ",");
		println("\"removable\" : " + egg.isRemovable() + ",");
		println("\"recolorable\" : " + egg.isRecolorable() + "");
		depth--;
		if (egg.getParent().isLastChild(egg)) {
			println("}");
		} else {
			print(indent() + "}");
		}
	}

	@Override
	public void visitColoredAlligator(ColoredAlligator alligator) {
		println("{");
		depth++;
		println("\"type\" : \"colored alligator\",");
		println("\"color\" : " + alligator.getColor().getId() + ",");
		println("\"movable\" : " + alligator.isMovable() + ",");
		println("\"removable\" : " + alligator.isRemovable() + ",");
		println("\"recolorable\" : " + alligator.isRecolorable() + ",");
		print(indent() + "\"children\" : ");
		printChildren(alligator);
		depth--;
		if (alligator.getParent().isLastChild(alligator)) {
			println("}");
		} else {
			print(indent() + "}");
		}
	}

	@Override
	public void visitAgedAlligator(AgedAlligator alligator) {
		println("{");
		depth++;
		println("\"type\" : \"aged alligator\",");
		println("\"movable\" : " + alligator.isMovable() + ",");
		println("\"removable\" : " + alligator.isRemovable() + ",");
		print(indent() + "\"children\" : ");
		printChildren(alligator);
		depth--;
		if (alligator.getParent().isLastChild(alligator)) {
			println("}");
		} else {
			print(indent() + "}");
		}
	}

	@Override
	public void visitBoard(Board board) {
		println("{");
		depth++;
		print(indent() + "\"families\" : ");
		printChildren(board);
		depth--;
		println("}");
	}

	private void printChildren(Parent p) {
		printChildren(p, false, true);
	}

	private void printChildren(Parent p, boolean indentBefore,
			boolean breakAfter) {
		if (p.getChildCount() < 1) {
			if (indentBefore)
				print(indent() + "[]");
			else
				print("[]");
			if (breakAfter)
				print("\n");
		} else {
			if (indentBefore)
				println("[");
			else
				print("[\n");
			depth++;
			Iterator<InternalBoardObject> i = p.iterator();
			while (i.hasNext()) {
				i.next().accept(this);
				if (i.hasNext()) {
					print(",\n");
				}
			}
			depth--;
			if (breakAfter) {
				println("]");
			} else {
				print(indent() + "]");
			}
		}
	}

	private void println(String line) {
		result += indent() + line + "\n";
	}

	private void print(String s) {
		result += s;
	}

	private String indent() {
		String result = "";
		for (int i = 0; i < depth; i++)
			result += (char) '\t';
		return result;
	}
}
