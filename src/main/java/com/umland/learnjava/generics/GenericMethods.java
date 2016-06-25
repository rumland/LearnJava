
package com.umland.learnjava.generics;

import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.Validate;

public class GenericMethods {
	public static <T> T min(List<T> list, Comparator<T> comparator) {
		Validate.notNull(list, "list must not be null");
		Validate.notEmpty(list, "list must not be empty");
		Validate.notNull(comparator, "comparator must not be null");

		T min = list.get(0);
		for (T o : list) {
			if (comparator.compare(o, min) < 0) {
				min = o;
			}
		}

		return min;
	}
}
