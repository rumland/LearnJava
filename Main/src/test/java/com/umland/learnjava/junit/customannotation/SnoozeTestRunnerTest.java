package com.umland.learnjava.junit.customannotation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SnoozeTestRunner.class)
public class SnoozeTestRunnerTest {
	private static int callCount = 0;

	@Test
	@SnoozeAnnotation(date = "1/1/3020")
	public void testSnoozed() {
		Assert.assertTrue("Shold not have run test.", false);
	}

	@Test
	@SnoozeAnnotation(date = "1/1/1020")
	public void testNotSnoozed() {
		Assert.assertEquals(0, callCount);
		++callCount;
		Assert.assertTrue("Shold have run test.", true);
	}

	@Test
	public void testRunBecauseNoSnooze() {
		Assert.assertTrue("Shold have run test.", true);
	}
}
