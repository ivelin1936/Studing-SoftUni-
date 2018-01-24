package com.company.centers;

import com.company.animals.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CastrationCenter extends Center {

    private HashMap<String, List<Animal>> animalsForCastration;
    private List<Animal> storedAnimals;

    public CastrationCenter(String name) {
        super(name);
        this.animalsForCastration = new HashMap<>();
        this.storedAnimals = new ArrayList<>();
    }

    //TODO...
}
