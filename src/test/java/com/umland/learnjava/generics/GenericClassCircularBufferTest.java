package com.umland.learnjava.generics;

import com.umland.learnjava.generics.clazz.CircularBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class GenericClassCircularBufferTest {
	private CircularBuffer buffer = new CircularBuffer(2);
	
	@Test
	public void testShouldOfferPollableElement() {
		assertTrue(buffer.offer(1));

		assertEquals(1, buffer.poll());
		assertNull(buffer.poll());
	}
	
	@Test
	public void testShouldNotOfferWhenBufferIsFull() {
		assertTrue(buffer.offer(1));
		assertTrue(buffer.offer(2));
		assertFalse(buffer.offer(3));
	}

	@Test
	public void testShouldNotPollWhenBufferIsEmpty() {
		assertNull(buffer.poll());
	}

	@Test
	public void testShouldRecycleBuffer() {
		assertTrue(buffer.offer(1));
		assertTrue(buffer.offer(2));
		assertFalse(buffer.offer(3));
		assertEquals(1, buffer.poll());
		assertTrue(buffer.offer(3));
		assertEquals(2, buffer.poll());
		assertEquals(3, buffer.poll());
	}
}
