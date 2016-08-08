package com.umland;

public class NikonCamera implements Camera {
    @Override
    public String takePhoto() {
        String message = "Nikon photo taken.";
        System.out.println(message);

        return message;
    }
}
