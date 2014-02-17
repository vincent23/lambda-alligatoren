package de.croggle.game.board.operations;

import java.util.List;

import de.croggle.game.board.ColoredBoardObject;
import de.croggle.game.board.Egg;
import de.croggle.game.board.Parent;

public class Boundedness {

	public static boolean isBound(Egg e) {
		List<Parent> parents = GetParentHierarchy.get(e);
		for (Parent p : parents) {
			if (p instanceof ColoredBoardObject) {
				if (((ColoredBoardObject) p).getColor().equals(e.getColor())) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isFree(Egg e) {
		return !isBound(e);
	}

}
