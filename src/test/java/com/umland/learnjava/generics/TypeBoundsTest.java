package com.umland.learnjava.generics;

import com.umland.learnjava.generics.TypeBounds;
import org.junit.Assert;
import org.junit.Test;

public class TypeBoundsTest {
	@Test
	public void testRetainOrderOfOrderedPair() {
		TypeBounds<Integer> sortedPair = new TypeBounds<>(1, 2);
		Assert.assertEquals(1, sortedPair.getFirst().intValue());
		Assert.assertEquals(2, sortedPair.getSecond().intValue());
	}

	@Test
	public void testFlipOrderOfMisorderedPair() {
		TypeBounds<Integer> sortedPair = new TypeBounds<>(3, 2);
		Assert.assertEquals(2, sortedPair.getFirst().intValue());
		Assert.assertEquals(3, sortedPair.getSecond().intValue());
	}

	@Test(expected = NullPointerException.class)
	public void testNullFirst() {
		TypeBounds<Integer> sortedPair = new TypeBounds<>(null, 2);
		Assert.assertEquals(2, sortedPair.getFirst().intValue());
		Assert.assertEquals(3, sortedPair.getSecond().intValue());
	}

	@Test(expected = NullPointerException.class)
	public void testNullSecond() {
		TypeBounds<Integer> sortedPair = new TypeBounds<>(2, null);
		Assert.assertEquals(2, sortedPair.getFirst().intValue());
		Assert.assertEquals(3, sortedPair.getSecond().intValue());
	}

	@Test(expected = NullPointerException.class)
	public void testNullAll() {
		TypeBounds<Integer> sortedPair = new TypeBounds<>(null, null);
		Assert.assertEquals(2, sortedPair.getFirst().intValue());
		Assert.assertEquals(3, sortedPair.getSecond().intValue());
	}
}
