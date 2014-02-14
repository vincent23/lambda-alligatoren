package de.croggle.util;

public class Tuple2<x_t, y_t> {
	public x_t el1;
	public y_t el2;

	public Tuple2() {
	}

	public Tuple2(x_t el1, y_t el2) {
		this.el1 = el1;
		this.el2 = el2;
	}

	public void set(x_t el1, y_t el2) {
		this.el1 = el1;
		this.el2 = el2;
	}
}
