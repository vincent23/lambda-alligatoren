package de.croggle.game.board;

import junit.framework.Assert;
import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.operations.BoardObjectVisitor;

public class ParentTest extends TestCase {
	private Parent parent;

	protected void setUp() {
		parent = new Parent() {

			@Override
			public BoardObject copy() {
				return null;
			}

			@Override
			public void accept(BoardObjectVisitor visitor) {
			}
		};
	}

	public void testAddChild() {
		final InternalBoardObject child = getNewChild();
		parent.addChild(child);
		Assert.assertEquals(1, parent.getChildCount());
		Assert.assertEquals(0, parent.getChildPosition(child));
		Assert.assertTrue(parent.getFirstChild() == child);
		Assert.assertTrue(child.getParent() == parent);
		Assert.assertTrue(parent.isLastChild(child));
		int i = 0;
		for (InternalBoardObject currentChild : parent) {
			if (i == 0) {
				Assert.assertTrue(currentChild == child);
				i++;
			} else {
				fail();
			}
		}
	}

	public void testInsertChild() {
		final InternalBoardObject child1 = getNewChild();
		final InternalBoardObject child2 = getNewChild();
		final InternalBoardObject child3 = getNewChild();
		parent.insertChild(child3, 0);
		parent.insertChild(child1, 0);
		parent.insertChild(child2, 1);
		Assert.assertTrue(child1.getParent() == parent);
		Assert.assertTrue(child2.getParent() == parent);
		Assert.assertTrue(child3.getParent() == parent);
		Assert.assertEquals(parent.getChildCount(), 3);
		Assert.assertEquals(parent.getChildPosition(child1), 0);
		Assert.assertEquals(parent.getChildPosition(child2), 1);
		Assert.assertEquals(parent.getChildPosition(child3), 2);
		Assert.assertFalse(parent.isLastChild(child1));
		Assert.assertFalse(parent.isLastChild(child2));
		Assert.assertTrue(parent.isLastChild(child3));
		int i = 0;
		for (InternalBoardObject currentChild : parent) {
			switch (i) {
			case 0:
				Assert.assertTrue(currentChild == child1);
				break;
			case 1:
				Assert.assertTrue(currentChild == child2);
				break;
			case 2:
				Assert.assertTrue(currentChild == child3);
				break;
			}
			i++;
		}
		Assert.assertTrue(parent.getChildAfter(child1) == child2);
		Assert.assertTrue(parent.getChildAfter(child2) == child3);
		Assert.assertTrue(parent.getChildAfter(child3) == null);
	}

	public void testRemoveChild() {
		final InternalBoardObject child1 = getNewChild();
		final InternalBoardObject child2 = getNewChild();
		final InternalBoardObject child3 = getNewChild();
		parent.addChild(child1);
		parent.addChild(child2);
		parent.addChild(child3);
		Assert.assertEquals(0, parent.getChildPosition(child1));
		Assert.assertEquals(1, parent.getChildPosition(child2));
		Assert.assertEquals(2, parent.getChildPosition(child3));

		parent.removeChild(child1);
		Assert.assertEquals(-1, parent.getChildPosition(child1));
		Assert.assertEquals(0, parent.getChildPosition(child2));
		Assert.assertEquals(1, parent.getChildPosition(child3));
		parent.removeChild(child2);
		Assert.assertEquals(-1, parent.getChildPosition(child1));
		Assert.assertEquals(-1, parent.getChildPosition(child2));
		Assert.assertEquals(0, parent.getChildPosition(child3));
		parent.removeChild(child3);
		Assert.assertEquals(-1, parent.getChildPosition(child1));
		Assert.assertEquals(-1, parent.getChildPosition(child2));
		Assert.assertEquals(-1, parent.getChildPosition(child3));

	}

	public void testReplaceChild() {
		final InternalBoardObject child1 = getNewChild();
		final InternalBoardObject child2 = getNewChild();
		final InternalBoardObject child3 = getNewChild();
		parent.addChild(child1);
		parent.addChild(child2);
		Assert.assertEquals(0, parent.getChildPosition(child1));
		Assert.assertEquals(1, parent.getChildPosition(child2));
		Assert.assertEquals(-1, parent.getChildPosition(child3));

		Assert.assertFalse(parent.replaceChild(child3, child1));
		Assert.assertEquals(0, parent.getChildPosition(child1));
		Assert.assertEquals(1, parent.getChildPosition(child2));
		Assert.assertEquals(-1, parent.getChildPosition(child3));

		Assert.assertTrue(parent.replaceChild(child2, child3));
		Assert.assertEquals(0, parent.getChildPosition(child1));
		Assert.assertEquals(-1, parent.getChildPosition(child2));
		Assert.assertEquals(1, parent.getChildPosition(child3));

		Assert.assertFalse(parent.replaceChild(child1, child3));
		Assert.assertEquals(0, parent.getChildPosition(child1));
		Assert.assertEquals(-1, parent.getChildPosition(child2));
		Assert.assertEquals(1, parent.getChildPosition(child3));
	}

	private InternalBoardObject getNewChild() {
		return new Egg(false, false, new Color(1), false);
	}
}
