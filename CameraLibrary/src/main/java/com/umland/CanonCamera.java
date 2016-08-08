package com.umland;

public class CanonCamera implements Camera {
    @Override
    public String takePhoto() {
        String message = "Canon photo taken.";
        System.out.println(message);

        return message;
    }
}
