package com.company.commandEngine;

import com.company.animals.Animal;
import com.company.animals.Cat;
import com.company.animals.Dog;
import com.company.centers.AdoptionCenter;
import com.company.centers.CleansingCenter;
import com.company.commandEngine.interfaces.IAnimalCenterManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnimalCenterManager implements IAnimalCenterManager {

    private HashMap<String, AdoptionCenter> adoptionCentersDB;
    private HashMap<String, CleansingCenter> cleansingCentersDB;

    public AnimalCenterManager() {
        this.adoptionCentersDB = new HashMap<>();
        this.cleansingCentersDB = new HashMap<>();
    }

    private void addAdoptionCenter(AdoptionCenter centers) {
        this.adoptionCentersDB.putIfAbsent(centers.getName(), centers);
    }

    private void addCleansingCenter(CleansingCenter centers) {
        this.cleansingCentersDB.putIfAbsent(centers.getName(), centers);
    }

    @Override
    public void registerCleansingCenter(String name) {
        addCleansingCenter(new CleansingCenter(name));
    }

    @Override
    public void registerAdoptionCenter(String name) {
        addAdoptionCenter(new AdoptionCenter(name));
    }

    @Override
    public void registerDog(String name, int age, int learnedCommands, String adoptionCenterName) {
        Animal dog = new Dog(name, age, learnedCommands);
        addAnimalToCurrentAdoptionCenterName(adoptionCenterName, dog);
    }

    @Override
    public void registerCat(String name, int age, int intelligenceCoefficient, String adoptionCenterName) {
        Animal cat = new Cat(name, age, intelligenceCoefficient);
        addAnimalToCurrentAdoptionCenterName(adoptionCenterName, cat);
    }

    private void addAnimalToCurrentAdoptionCenterName(String adoptionCenterName, Animal animal) {
        if (adoptionCentersDB.containsKey(adoptionCenterName)) {
            adoptionCentersDB.get(adoptionCenterName).registerAnimal(animal);
        }
    }

    @Override
    public void sendForCleansing(String adoptionCenterName, String cleansingCenterName) {
        if (adoptionCentersDB.containsKey(adoptionCenterName ))
        {
            if (cleansingCentersDB.containsKey(cleansingCenterName))
            {
                cleansingCentersDB.get(cleansingCenterName)
                        .addAnimalsForCleansing(adoptionCenterName,
                                adoptionCentersDB.get(adoptionCenterName).getStoredUncleansedAnimals());
            }
        }
    }

    @Override
    public void cleanse(String cleansingCenterName) {
        if (cleansingCentersDB.containsKey(cleansingCenterName)) {
            HashMap<String, List<Animal>> cleanedAnimals =
                    new HashMap<>(cleansingCentersDB.get(cleansingCenterName).cleaseAnimals());
            sentCleanedAnimalsBackToCenters(cleanedAnimals);
        }
    }

    private void sentCleanedAnimalsBackToCenters(HashMap<String, List<Animal>> cleansedAnimals) {
        for (String centerName : cleansedAnimals.keySet()) {
            adoptionCentersDB.get(centerName).returnBackCleansedAnimals(cleansedAnimals.get(centerName));
        }
    }

    @Override
    public void adopt(String adoptionCenterName) {
        if (adoptionCentersDB.containsKey(adoptionCenterName)) {
            adoptionCentersDB.get(adoptionCenterName).adoptedAnimals();
        }
    }

    @Override
    public void printStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Paw Incorporative Regular Statistics%n"))
                .append(String.format("Adoption Centers: %d%n", adoptionCentersDB.size()))
                .append(String.format("Cleansing Centers: %d%n", cleansingCentersDB.size()))
                .append(String.format("Adopted Animals: %s%n", adoptedAnimals()))
                .append(String.format("Cleansed Animals: %s%n", cleansedAnimals()))
                .append(String.format("Animals Awaiting Adoption: %d%n", getAnimalsAwaitingAdoption()))
                .append(String.format("Animals Awaiting Cleansing: %d", getAnimalsAwaitingCleansing()));

        System.out.println(sb.toString());
    }

    private String adoptedAnimals() {
        List<String> adoptedAnimals = new ArrayList<>();
        for (String aCenter : adoptionCentersDB.keySet()) {
            adoptedAnimals.addAll(adoptionCentersDB.get(aCenter).getAdoptedAnimals());
        }
        adoptedAnimals.sort(String::compareToIgnoreCase);

        String adoAnimals = null;
        if (adoptedAnimals.size() > 0) {
            adoAnimals = String.join(", ", adoptedAnimals);
        } else {
            adoAnimals = "None";
        }

        return adoAnimals;
    }

    private String cleansedAnimals() {
        List<String> cleansedAnimals = new ArrayList<>();
        for (String cCenter : cleansingCentersDB.keySet()) {
            cleansedAnimals.addAll(cleansingCentersDB.get(cCenter).getCleansedAnimalsNames());
        }
        cleansedAnimals.sort(String::compareToIgnoreCase);

        String cleanAnimals = null;
        if (cleansedAnimals.size() > 0) {
            cleanAnimals = String.join(", ", cleansedAnimals);
        } else {
            cleanAnimals = "None";
        }

        return cleanAnimals;
    }

    private int getAnimalsAwaitingCleansing() {
        int counter = 0;
        for (String cCenter : cleansingCentersDB.keySet()) {
            counter += cleansingCentersDB.get(cCenter).getCountOnAnimalsForCleansing();
        }
        return counter;
    }

    private int getAnimalsAwaitingAdoption() {
        int counter = 0;
        for (String aCenter : adoptionCentersDB.keySet()) {
            counter += adoptionCentersDB.get(aCenter).getAnimalsAwaitingAdoption();
        }
        return counter;
    }
}
