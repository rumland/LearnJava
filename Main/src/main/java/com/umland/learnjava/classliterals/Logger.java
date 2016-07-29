package com.umland.learnjava.classliterals;

public class Logger {
    private String message;

    public Logger(String message) {
        this.message = message;
    }

    public void log() {
        System.out.printf("%s%n", message);
    }
}
