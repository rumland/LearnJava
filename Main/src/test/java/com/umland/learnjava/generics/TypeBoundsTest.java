package com.umland.learnjava.generics;

import com.umland.learnjava.generics.typebounds.SortedPair;
import org.junit.Assert;
import org.junit.Test;

public class TypeBoundsTest {
	@Test
	public void testRetainOrderOfOrderedPair() {
		SortedPair<Integer> sortedPair = new SortedPair<>(1, 2);
		Assert.assertEquals(1, sortedPair.getFirst().intValue());
		Assert.assertEquals(2, sortedPair.getSecond().intValue());
	}

	@Test
	public void testFlipOrderOfMisorderedPair() {
		SortedPair<Integer> sortedPair = new SortedPair<>(3, 2);
		Assert.assertEquals(2, sortedPair.getFirst().intValue());
		Assert.assertEquals(3, sortedPair.getSecond().intValue());
	}

	@Test(expected = NullPointerException.class)
	public void testNullFirst() {
		SortedPair<Integer> sortedPair = new SortedPair<>(null, 2);
		Assert.assertEquals(2, sortedPair.getFirst().intValue());
		Assert.assertEquals(3, sortedPair.getSecond().intValue());
	}

	@Test(expected = NullPointerException.class)
	public void testNullSecond() {
		SortedPair<Integer> sortedPair = new SortedPair<>(2, null);
		Assert.assertEquals(2, sortedPair.getFirst().intValue());
		Assert.assertEquals(3, sortedPair.getSecond().intValue());
	}

	@Test(expected = NullPointerException.class)
	public void testNullAll() {
		SortedPair<Integer> sortedPair = new SortedPair<>(null, null);
		Assert.assertEquals(2, sortedPair.getFirst().intValue());
		Assert.assertEquals(3, sortedPair.getSecond().intValue());
	}
}
