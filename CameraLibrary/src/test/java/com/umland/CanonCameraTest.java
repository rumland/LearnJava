package com.umland;

import org.junit.Test;

import static org.junit.Assert.*;

public class CanonCameraTest {
    Camera camera = new CanonCameraFactory().createCamera();

    @Test
    public void takePhoto() throws Exception {
        camera.takePhoto();
    }
}