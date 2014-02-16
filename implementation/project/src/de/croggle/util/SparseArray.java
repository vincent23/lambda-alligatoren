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

	public T put(int key, T val) {
		int pos = find(key);
		if (pos < 0) {
			// insert
			pos = -pos - 1;
			l.add(pos, new Tuple2<Integer, T>(key, val));
			return null;
		} else {
			// replace
			Tuple2<Integer, T> elm = l.get(pos);
			T previous = elm.el2;
			elm.el2 = val;
			return previous;
		}
	}

	public T get(int key) {
		int pos = find(key);
		if (pos < 0) {
			return null;
		}
		return l.get(pos).el2;
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
