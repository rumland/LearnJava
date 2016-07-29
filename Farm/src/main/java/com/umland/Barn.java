package com.umland;

public class Barn implements BarnInterface {
    private final String defaultColor = "red";
    private final String color;

    public Barn() {
        this.color = this.defaultColor;
    }

    public Barn(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}