package com.umland.learnjava.generics;

import com.umland.learnjava.generics.clazz.CircularBuffer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class GenericClassCircularBufferTest {
	private CircularBuffer buffer = new CircularBuffer(2);
	
	@Test
	public void testShouldOfferPollableElement() {
		//noinspection unchecked
		Assert.assertTrue(buffer.offer(1));

		assertEquals(1, buffer.poll());
		Assert.assertNull(buffer.poll());
	}
	
	@Test
	public void testShouldNotOfferWhenBufferIsFull() {
		//noinspection unchecked
		Assert.assertTrue(buffer.offer(1));
		//noinspection unchecked
		Assert.assertTrue(buffer.offer(2));
		//noinspection unchecked
		Assert.assertFalse(buffer.offer(3));
	}

	@Test
	public void testShouldNotPollWhenBufferIsEmpty() {
		Assert.assertNull(buffer.poll());
	}

	@Test
	public void testShouldRecycleBuffer() {
		//noinspection unchecked
		Assert.assertTrue(buffer.offer(1));
		//noinspection unchecked
		Assert.assertTrue(buffer.offer(2));
		//noinspection unchecked
		Assert.assertFalse(buffer.offer(3));
		assertEquals(1, buffer.poll());
		//noinspection unchecked
		Assert.assertTrue(buffer.offer(3));
		assertEquals(2, buffer.poll());
		assertEquals(3, buffer.poll());
	}
}
