package com.company.centers;

import com.company.animals.Animal;
import com.company.animals.CastrateStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CastrationCenter extends Center {

    private HashMap<String, List<Animal>> animalsForCastration;
    private List<String> storedCastratedAnimalsName;

    public CastrationCenter(String name) {
        super(name);
        this.animalsForCastration = new HashMap<>();
        this.storedCastratedAnimalsName = new ArrayList<>();
    }

    public void addAnimalsForCastration(String centerName, List<Animal> animals) {
        if (animalsForCastration.containsKey(centerName)){
            updateAnimalsForCastrationDatabase(centerName, animals);
        }
        animalsForCastration.putIfAbsent(centerName, animals);
    }

    private void updateAnimalsForCastrationDatabase(String centerName, List<Animal> animals) {
        List<Animal> buffer = new ArrayList<>();
        buffer.addAll(animalsForCastration.get(centerName));
        buffer.addAll(animals);
        animalsForCastration.replace(centerName, buffer);
    }

    public HashMap<String, List<Animal>> castrateAnimals() {
        for (String centerGroup : animalsForCastration.keySet()) {
            List<Animal> animals = new ArrayList<>(animalsForCastration.get(centerGroup));
            castratingAnimals(animals);
        }
        HashMap<String, List<Animal>> castratedAnimals = new HashMap<>(animalsForCastration);
        animalsForCastration.clear();

        return castratedAnimals;
    }

    private void castratingAnimals(List<Animal> animals) {
        for (Animal animal : animals) {
            animal.setCastrateStatus(CastrateStatus.Castrated);
            storedCastratedAnimalsName.add(animal.getName());
        }
    }

    public List<String> getCastratedAnimalsNames() {
        return storedCastratedAnimalsName;
    }
}
