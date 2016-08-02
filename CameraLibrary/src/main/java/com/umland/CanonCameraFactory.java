package com.umland;

public class CanonCameraFactory implements CameraFactory {
    @Override
    public Camera createCamera() {
        return new CanonCamera();
    }
}
