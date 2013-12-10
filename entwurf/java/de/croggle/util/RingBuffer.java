package de.croggle.util;

/**
 * 
 */
public class RingBuffer<T> {
	
	private T[] buffer;
	private int head = 0;
	private int items = 0;
	
	public RingBuffer (int size) {
		assert size > 0 : "Cannot initialize RingBuffer with size smaller 0.";
		this.buffer = new T[size];
	}
	
	public void push (T obj) {
		if (items < buffer.length) {
			items++;
		}
		buffer [head] = obj;
		head = (head + 1) % buffer.length;
	}
	
	public T pop () {
		if (items == 0) {
			throw new Exception("");
		}
		head = (head + buffer.length - 1) % buffer.length;
		items--;
		return buffer[head];
	}
	
}
