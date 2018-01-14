package com.company.Car;

import com.sun.org.apache.xpath.internal.operations.String;

public class Car {

    private double speed;
    private double fuel;
    private double fuelEconomy;

    public Car(double speed, double fuel, double fuelEconomy) {
        this.speed = speed;
        this.fuel = fuel;
        this.fuelEconomy = fuelEconomy;
    }

    public void totalDistance(double travelDistance) {
        double fullDistance = fuel * (100 / fuelEconomy);
        if (travelDistance <= fullDistance) {
            System.out.printf("Total distance: %.1f kilometers %n", travelDistance);
        } else {
            System.out.printf("Total distance: %.1f kilometers %n", fullDistance);
        }
    }

    public void totalTime(double travelDistance) {
        double fullDistance = fuel * (100 / fuelEconomy);
        if (travelDistance <= fullDistance) {
            double time = (travelDistance / speed) * 60;
            int h = (int)time / 60;
            int min = (int)time % 60;
            System.out.printf("Total time: %d hours and %d minutes %n", h, min);
        } else {
            double time = (fullDistance / speed) * 60;
            int h = (int)time / 60;
            int min = (int)time % 60;
            System.out.printf("Total time: %d hours and %d minutes %n", h, min);
        }
    }

    public void leftFuel(double travelDistance) {
        double fullDistance = fuel * (100 / fuelEconomy);
        if (travelDistance >= fullDistance) {
            System.out.println("Fuel left: 0.0 liters");
        } else {
            double useFuel = (travelDistance / 100) * fuelEconomy;
            double leftFuel = fuel - useFuel;
            System.out.printf("Fuel left: %.1f liters", leftFuel);
        }
    }
}
