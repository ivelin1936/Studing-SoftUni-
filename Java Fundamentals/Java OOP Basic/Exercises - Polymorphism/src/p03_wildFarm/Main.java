package p03_wildFarm;

import p03_wildFarm.IO.ConsoleIO;
import p03_wildFarm.factory.AnimalFactory;
import p03_wildFarm.factory.FoodFactory;
import p03_wildFarm.models.animalModels.*;
import p03_wildFarm.models.foodModels.Food;
import p03_wildFarm.util.ConfigConstants;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();
        FoodFactory foodFactory = new FoodFactory();
        ConsoleIO consoleIO = new ConsoleIO();

        List<Animal> animalsDB = new LinkedList<>();

        String line;
        while (!ConfigConstants.STOP.equalsIgnoreCase(line = consoleIO.readLine())) {
            Animal animal = null;
            try {
                animal = animalFactory.produceAnimal(line);
            } catch (IllegalArgumentException iae) {
                consoleIO.writeLine(iae.getMessage());
            }

            Food food = null;
            try {
                food = foodFactory.produceFood(consoleIO);
            } catch (IllegalArgumentException iae) {
                consoleIO.writeLine(iae.getMessage());
            }

            if (animal == null || food == null)
                continue;

            consoleIO.writeLine(animal.makeSound());
            try {
                animal.eat(food);
            } catch (IllegalArgumentException iae) {
                consoleIO.writeLine(iae.getMessage());
            }
            animalsDB.add(animal);
        }

        animalsDB.forEach(animal -> consoleIO.writeLine(animal.toString()));
    }
}
