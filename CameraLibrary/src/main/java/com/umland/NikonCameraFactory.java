package com.umland;

public class NikonCameraFactory implements CameraFactory {
    public Camera createCamera() {
        return new NikonCamera();
    }
}
