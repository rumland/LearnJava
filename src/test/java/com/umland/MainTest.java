package com.umland;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void testCreateMessage() throws Exception {
        Assert.assertEquals("Hello World", Main.CreateMessage());
    }
}