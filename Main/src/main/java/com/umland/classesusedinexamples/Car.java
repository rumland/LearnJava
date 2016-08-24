package com.umland.classesusedinexamples;

public class Car extends Vehicle {
    private int speed;
    private char driveType;
    private final int doors;
    private final String[] people;

    public Car(final int doors) {
        this.doors = doors;
        this.people = new String[] { "Joe", "Sue" };
    }

    public Car(final int doors, final String[] people) {
        this.doors = doors;
        this.people = people;
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
