
package com.umland.learnjava.generics;

import java.util.Comparator;

public class PassingParameterToGenericType<T> implements Comparator<T> {
	private final Comparator<T> delegateComparator;

	public PassingParameterToGenericType(final Comparator<T> delegateComparator) {
		this.delegateComparator = delegateComparator;
	}

	@Override
	public int compare(T o1, T o2) {
		return -1 * delegateComparator.compare(o1, o2);
	}

}
