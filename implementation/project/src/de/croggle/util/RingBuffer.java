package de.croggle.util;

/**
 * Utility construct, which simplifies saving the last 30 steps immensely.
 */
public class RingBuffer<T> {

	private T[] buffer;
	private int head = 0;
	private int items = 0;

	/**
	 * Creates a ringbuffer with a given size.
	 * 
	 * @param size
	 *            the size with which the ringbuffer will be created. 30 in our
	 *            case.
	 */
	@SuppressWarnings("unchecked")
	public RingBuffer(int size) {
		assert size > 0 : "Cannot initialize RingBuffer with size smaller 0.";
		this.buffer = (T[]) new Object[size];
	}

	/**
	 * Places the given object on top of the ringbuffer.
	 * 
	 * @param obj
	 *            the object which will be placed upon the ringbuffer.
	 */
	public void push(T obj) {
		if (items < buffer.length) {
			items++;
		}
		buffer[head] = obj;
		head = (head + 1) % buffer.length;
	}

	/**
	 * Removes the topmost object of the ringbuffer and returns it.
	 * 
	 * @return the object which used to be on top of the ringbuffer.
	 * @throws Exception
	 */
	public T pop() throws Exception {
		if (items == 0) {
			throw new Exception("");
		}
		head = (head + buffer.length - 1) % buffer.length;
		items--;
		return buffer[head];
	}

	public int size() {
		return items;
	}
}
