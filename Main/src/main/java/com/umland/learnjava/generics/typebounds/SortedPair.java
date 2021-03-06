package com.umland.learnjava.generics.typebounds;

import org.apache.commons.lang3.Validate;

public class SortedPair<T extends Comparable<T>> {
	private final T first;
	private final T second;

	public SortedPair(T left, T right) {
		Validate.notNull(left, "left must not be null");
		Validate.notNull(right, "right must not be null");

		if (left.compareTo(right) < 0) {
			this.first = left;
			this.second = right;
		} else {
			this.first = right;
			this.second = left;
		}
	}

	public T getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}
}
