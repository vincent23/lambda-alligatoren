package de.croggle.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class SparseArray<T> {
	private final Tuple2<Integer, T> finder;
	private final Comparator<Tuple2<Integer, T>> comp;
	private final LinkedList<Tuple2<Integer, T>> l;

	public SparseArray() {
		comp = new Comparator<Tuple2<Integer, T>>() {
			@Override
			public int compare(Tuple2<Integer, T> lhs, Tuple2<Integer, T> rhs) {
				return lhs.el1 - rhs.el1;
			}
		};
		l = new LinkedList<Tuple2<Integer, T>>();
		finder = new Tuple2<Integer, T>();
	}

	public void put(int key, T val) {
		int pos = find(key);
		if (pos < 0) {
			// insert
			pos = -pos - 1;
			l.add(pos, new Tuple2<Integer, T>(key, val));
		} else {
			// replace
			l.get(pos).el2 = val;
		}
	}

	public T get(int key) {
		return l.get(find(key)).el2;
	}

	public int size() {
		return l.size();
	}

	private int find(int key) {
		finder.el1 = key;
		int result = Collections.binarySearch(l, finder, comp);
		return result;
	}
}
