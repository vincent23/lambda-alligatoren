package de.croggle.game.board;

import junit.framework.Assert;
import junit.framework.TestCase;
import de.croggle.game.board.operations.BoardObjectVisitor;

public class AlligatorTest extends TestCase {

	public void testParent() {
		final Alligator alligator = new DummyAlligator(false, false);
		Assert.assertNull(alligator.getParent());
		Parent parent = new DummyAlligator(false, false);
		alligator.setParent(parent);
		Assert.assertTrue(alligator.getParent() == parent);
	}

	public void testIsMovable() {
		Alligator nonMovableAlligator = new DummyAlligator(false, false);
		Alligator movableAlligator = new DummyAlligator(true, false);
		Assert.assertFalse(nonMovableAlligator.isMovable());
		Assert.assertTrue(movableAlligator.isMovable());
		nonMovableAlligator = new DummyAlligator(false, true);
		movableAlligator = new DummyAlligator(true, true);
		Assert.assertFalse(nonMovableAlligator.isMovable());
		Assert.assertTrue(movableAlligator.isMovable());
	}

	public void testIsRemovable() {
		Alligator nonRemovableAlligator = new DummyAlligator(false, false);
		Alligator removableAlligator = new DummyAlligator(false, true);
		Assert.assertFalse(nonRemovableAlligator.isRemovable());
		Assert.assertTrue(removableAlligator.isRemovable());
		nonRemovableAlligator = new DummyAlligator(true, false);
		removableAlligator = new DummyAlligator(true, true);
		Assert.assertFalse(nonRemovableAlligator.isRemovable());
		Assert.assertTrue(removableAlligator.isRemovable());
	}

	private static class DummyAlligator extends Alligator {
		protected DummyAlligator(boolean movable, boolean removable) {
			super(movable, removable);
		}

		@Override
		public InternalBoardObject copy() {
			fail();
			return null;
		}

		@Override
		public void accept(BoardObjectVisitor visitor) {
			fail();
		}

	}
}
