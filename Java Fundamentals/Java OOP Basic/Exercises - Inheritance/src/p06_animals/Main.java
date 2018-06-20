package p06_animals;

import p06_animals.models.*;
import p06_animals.util.ConfigExMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static final String STOP_PROCESS = "Beast!";
    private static final String ANIMAL_CAT = "Cat";
    private static final String ANIMAL_DOG = "Dog";
    private static final String ANIMAL_FROG = "Frog";
    private static final String ANIMAL_KITTEN = "Kitten";
    private static final String ANIMAL_TOMCAT = "Tomcat";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Animal> animalsDB = new LinkedList<>();

        String animalType;
        while (!STOP_PROCESS.equalsIgnoreCase(animalType = reader.readLine())) {
            Animal animal = null;
            try {
                animal = createAnimal(animalType, reader);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }

            if (animal != null) {
                animalsDB.add(animal);
            }
        }

        animalsDB.forEach(animal -> {
            System.out.println(animal);
            System.out.println(animal.produceSound());
        });

    }

    private static Animal createAnimal(String animalType, BufferedReader reader) throws IOException {
        String[] animalTokens = reader.readLine().split("\\s+");
        String name = animalTokens[0];
        int age = Integer.parseInt(animalTokens[1]);
        String gender = animalTokens[2];

        switch (animalType) {
            case ANIMAL_CAT:
                return new Cat(animalType, name, age, gender);
            case ANIMAL_DOG:
                return new Dog(animalType, name, age, gender);
            case ANIMAL_FROG:
                return new Frog(animalType, name, age, gender);
            case ANIMAL_KITTEN:
                return new Kitten(animalType, name, age, gender);
            case ANIMAL_TOMCAT:
                return new Tomcat(animalType, name, age, gender);
            default:
                throw new IllegalArgumentException(ConfigExMessage.DEFAULT_EXCEPTION_MESSAGE);
        }
    }
}
