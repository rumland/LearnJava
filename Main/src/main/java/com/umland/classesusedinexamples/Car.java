package com.umland.classesusedinexamples;

public class Car {
    private int speed;
    private char driveType;
    private final int doors;

    public Car(int doors) {
        this.doors = doors;
    }

    public int drive(char driveType, int desiredSpeed) {
        selectDrive(driveType);

        while (speed != desiredSpeed) {
            accelerate();
        }

        return speed;
    }

    public int getDoors() {
        return doors;
    }

    private int accelerate() {
        return ++speed;
    }

    private void selectDrive(char driveType) {
        this.driveType = driveType;
    }
}
