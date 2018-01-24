package com.company.centers;

import com.company.animals.Animal;
import com.company.animals.CleansingStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CleansingCenter  extends Center{

    private HashMap<String, List<Animal>> animalsForCleansing;
    private List<String> cleansedAnimalsNames;

    public CleansingCenter(String name) {
        super(name);
        this.animalsForCleansing = new HashMap<>();
        this.cleansedAnimalsNames = new ArrayList<>();
    }

    public int getCountOnAnimalsForCleansing() {
        return countAnimalsForCleaning();
    }

    private int countAnimalsForCleaning() {
        int countAnimalsForCleaning = 0;
        for (String center : animalsForCleansing.keySet()) {
            countAnimalsForCleaning += animalsForCleansing.get(center).size();
        }
        return countAnimalsForCleaning;
    }

    public void addAnimalsForCleansing(String centerName, List<Animal> animals) {
        if (!animalsForCleansing.containsKey(centerName)) {
            animalsForCleansing.put(centerName, animals);
        } else {
            List<Animal> bufferList = new ArrayList<>();
            bufferList.addAll(animalsForCleansing.get(centerName));
            bufferList.addAll(animals);
            animalsForCleansing.replace(centerName, bufferList);
        }
    }

    public HashMap<String, List<Animal>> cleaseAnimals() {
        for (String centerGroup : animalsForCleansing.keySet()) {
            List<Animal> animals = new ArrayList<>(animalsForCleansing.get(centerGroup));
            cleasingAnimals(animals);
        }
        HashMap<String, List<Animal>> cleansedAnimals = new HashMap<>(animalsForCleansing);
        animalsForCleansing.clear();

        return cleansedAnimals;
    }

    private void cleasingAnimals(List<Animal> animals) {
        for (Animal animal : animals) {
            animal.setCleansingStatus(CleansingStatus.Cleansed);
            cleansedAnimalsNames.add(animal.getName());
        }
    }

    public List<String> getCleansedAnimalsNames() {
        return cleansedAnimalsNames;
    }
}
