package com.company.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputTokens = reader.readLine().split("\\s+");
        String[] travelDistance = reader.readLine().split("\\s+");

        double speed = Double.parseDouble(inputTokens[0]);
        double fuel = Double.parseDouble(inputTokens[1]);
        double fuelEconomy = Double.parseDouble(inputTokens[2]);
        double travelDis = Double.parseDouble(travelDistance[1]);

        Car car = new Car(speed, fuel, fuelEconomy);

        String command = reader.readLine();
        while (!command.equals("END")) {

            executeCommand(command, car, travelDis);

            command = reader.readLine();
        }
    }

    private static void executeCommand(String command, Car car, double travelDis) {

        switch (command) {
            case "Distance":
                car.totalDistance(travelDis);
                break;
            case "Time":
                car.totalTime(travelDis);
                break;
            case "Fuel":
                car.leftFuel(travelDis);
                break;
        }
    }
}
