package com.company.centers;

import com.company.animals.Animal;
import com.company.animals.CleansingStatus;

import java.util.ArrayList;
import java.util.List;

public class AdoptionCenter extends Center {

    private List<Animal> storedAnimals;
    private List<String> adoptedAnimals;

    public AdoptionCenter(String name) {
        super(name);
        this.storedAnimals = new ArrayList<>();
        this.adoptedAnimals = new ArrayList<>();
    }

    public void registerAnimal(Animal animal) {
        storedAnimals.add(animal);
    }

    public void returnBackCleansedAnimals(List<Animal> cleansedAnimals) {
        storedAnimals.addAll(cleansedAnimals);
    }

    public List<Animal> getStoredUncleansedAnimals() {
        List<Animal> animalsForCleansing = new ArrayList<>();

        for (Animal animal : storedAnimals) {
            if (animal.getCleansingStatus().equals(CleansingStatus.Uncleansed)) {
                animalsForCleansing.add(animal);
            }
        }
        storedAnimals.removeAll(animalsForCleansing);

        return animalsForCleansing;
    }

    public void adoptedAnimals() {
        setAdoptedAnimals();
    }

    private void setAdoptedAnimals() {
        List<Animal> candidates = new ArrayList<>(storedAnimals);
        for (Animal animal : candidates) {
            if (animal.getCleansingStatus().equals(CleansingStatus.Cleansed)) {
                adoptedAnimals.add(animal.getName());
                storedAnimals.remove(animal);
            }
        }
    }

    public int getAnimalsAwaitingAdoption() {
        return countingAwaitingAdoption();
    }

    private int countingAwaitingAdoption() {
        int counter = 0;
        for (Animal storedAnimal : storedAnimals) {
            if (storedAnimal.getCleansingStatus().equals(CleansingStatus.Cleansed)) {
                counter++;
            }
        }
        return counter;
    }

    public List<String> getAdoptedAnimals() {
        return this.adoptedAnimals;
    }
}
