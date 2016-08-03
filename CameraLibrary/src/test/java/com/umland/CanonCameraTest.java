package com.umland;

import org.junit.Test;

import static org.junit.Assert.*;

//TODO ru: Come back to 'Side by Side Deployment' @ 3:50.
public class CanonCameraTest {
    Camera camera = new CanonCameraFactory().createCamera();

    @Test
    public void takePhoto() throws Exception {
        camera.takePhoto();
    }
}