package com.umland;

import org.junit.Assert;
import org.junit.Test;

public class NikonCameraFactoryTest {
    @Test
    public void createCamera() throws Exception {
        Camera camera = new NikonCameraFactory().createCamera();
        Assert.assertNotNull(camera);
        Assert.assertEquals(NikonCamera.class, camera.getClass());
    }
}