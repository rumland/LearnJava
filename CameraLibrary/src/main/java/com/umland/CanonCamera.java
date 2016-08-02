package com.umland;

public class CanonCamera implements Camera {
    @Override
    public void takePhoto() {
        System.out.println("Canon photo taken.");
    }
}
