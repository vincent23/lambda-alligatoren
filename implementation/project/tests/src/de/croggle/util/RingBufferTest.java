package de.croggle.util;

import junit.framework.TestCase;

public class RingBufferTest extends TestCase {
	private final static int TEST_SIZE = 30;
	private RingBuffer<Object> ringBuffer;

	protected void setUp() {
		ringBuffer = new RingBuffer<Object>(TEST_SIZE);
	}

	public void testPush() {
		ringBuffer.push(new Object());
	}

	public void testEmptyPop() {
		try {
			ringBuffer.pop();
			fail("Expected exception on empty buffer");
		} catch (Exception e) {
		}
	}

	public void testMinSize() throws Exception {
		final Object[] objects = new Object[TEST_SIZE];
		for (int i = 0; i < TEST_SIZE; i++) {
			final Object object = new Object();
			objects[i] = object;
			ringBuffer.push(object);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(objects[TEST_SIZE - i - 1], ringBuffer.pop());
		}
	}

	public void testMaxSize() throws Exception {
		final Object[] objects = new Object[TEST_SIZE];
		ringBuffer.push(new Object());
		for (int i = 0; i < TEST_SIZE; i++) {
			final Object object = new Object();
			objects[i] = object;
			ringBuffer.push(object);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(objects[TEST_SIZE - i - 1], ringBuffer.pop());
		}
		testEmptyPop();
	}
}
