package com.umland;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CanonCameraFactoryTest {
    @Test
    public void createCamera() throws Exception {
        Camera camera = new CanonCameraFactory().createCamera();
        Assert.assertNotNull(camera);
        Assert.assertEquals(CanonCamera.class, camera.getClass());
    }
}