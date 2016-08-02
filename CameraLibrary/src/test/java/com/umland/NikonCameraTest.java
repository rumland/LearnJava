package com.umland;

import org.junit.Test;

import static org.junit.Assert.*;

public class NikonCameraTest {
    Camera camera = new NikonCameraFactory().createCamera();

    @Test
    public void takePhoto() throws Exception {
        camera.takePhoto();
    }
}