package com.umland;

public class NikonCamera implements Camera {
    @Override
    public void takePhoto() {
        System.out.println("Nikon photo taken.");
    }
}
