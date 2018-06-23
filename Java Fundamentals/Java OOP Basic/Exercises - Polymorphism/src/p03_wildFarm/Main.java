package p03_wildFarm;

import p03_wildFarm.models.animalModels.*;
import p03_wildFarm.models.foodModels.Food;
import p03_wildFarm.models.foodModels.Meat;
import p03_wildFarm.models.foodModels.Vegetable;
import p03_wildFarm.util.ConfigConstants;
import p03_wildFarm.util.ConfigExMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Animal> animalsDB = new LinkedList<>();

        String line;
        while (!ConfigConstants.STOP.equalsIgnoreCase(line = reader.readLine())) {
            Animal animal = null;
            try {
                animal = createAnimal(line);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }

            Food food = null;
            try {
                food = produceFood(reader);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }

            if (animal == null || food == null)
                continue;

            System.out.println(animal.makeSound());
            try {
                animal.eat(food);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
            animalsDB.add(animal);
        }

        animalsDB.forEach(System.out::println);
    }

    private static Food produceFood(BufferedReader reader) throws IOException {
        String[] foodTokens = reader.readLine().split("\\s+");
        String type = foodTokens[ConfigConstants.TYPE_INDEX];
        int quantity = Integer.parseInt(foodTokens[ConfigConstants.QUANTITY_INDEX]);

        switch (type) {
            case ConfigConstants.FOOD_TYPE_VEGETABLE:
                return new Vegetable(quantity);
            case ConfigConstants.FOOD_TYPE_MEAT:
                return new Meat(quantity);
            default:
                throw new IllegalArgumentException(ConfigExMessage.INVALID_FOOD_TYPE_EX_MESSAGE);
        }
    }

    private static Animal createAnimal(String line) {
        String[] animalTokens = line.split("\\s+");
//        {AnimalType} {AnimalName} {AnimalWeight} {AnimalLivingRegion} [{CatBreed} = Only if its cat]
        String type = animalTokens[ConfigConstants.TYPE_INDEX];
        String name = animalTokens[ConfigConstants.NAME_INDEX];
        double weight = Double.parseDouble(animalTokens[ConfigConstants.WEIGHT_INDEX]);
        String livingRegion = animalTokens[ConfigConstants.LIVING_REGION_INDEX];

        switch (type) {
            case ConfigConstants.CAT_TYPE:
                String breed = animalTokens[ConfigConstants.BREED_INDEX];
                return new Cat(name, type, weight, livingRegion, breed);
            case ConfigConstants.TIGER_TYPE:
                return new Tiger(name, type, weight, livingRegion);
            case ConfigConstants.ZEBRA_TYPE:
                return new Zebra(name, type, weight, livingRegion);
            case ConfigConstants.MOUSE_TYPE:
                return new Mouse(name, type, weight, livingRegion);
            default:
                throw new IllegalArgumentException(ConfigExMessage.INVALID_ANIMAL_TYPE_EX_MESSAGE);
        }
    }
}
