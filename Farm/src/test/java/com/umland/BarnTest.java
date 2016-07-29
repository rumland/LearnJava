package com.umland;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BarnTest {
    private final String defaultColor = "red";
    private Barn barn;

    @Before
    public void before() {
        barn = new Barn(defaultColor);
    }

    @Test
    public void barnColorTest() {
        Assert.assertEquals(defaultColor, barn.getColor());
    }

    @Test
    public void barnDefaultColorTest() {
        Barn b = new Barn();
        Assert.assertEquals(defaultColor, b.getColor());
    }
}