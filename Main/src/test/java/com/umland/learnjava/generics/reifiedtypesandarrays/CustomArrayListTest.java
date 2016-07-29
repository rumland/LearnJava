package com.umland.learnjava.generics.reifiedtypesandarrays;

import org.junit.Assert;
import org.junit.Test;

public class CustomArrayListTest {
    @Test
    public void testAddAndGet() {
        CustomArrayList<Integer> customArrayList = new CustomArrayList<>();

        customArrayList.add(0);
        customArrayList.add(1);
        customArrayList.add(2);

        Assert.assertEquals(customArrayList.get(0), new Integer(0));
        Assert.assertEquals(customArrayList.get(1), new Integer(1));
        Assert.assertEquals(customArrayList.get(2), new Integer(2));
    }
}