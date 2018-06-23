package p03_wildFarm.models.animalModels;

import p03_wildFarm.models.foodModels.Food;
import p03_wildFarm.util.ConfigExMessage;

public abstract class Animal implements IAnimal{

    private static final int DEFAULT_EATEN_FOOD = 0;

    private String name;
    private String type;
    private double weight;
    private int foodEaten;

    public Animal(String name, String type, double weight) {
        this.setName(name);
        this.setType(type);
        this.setWeight(weight);
        this.setFoodEaten(DEFAULT_EATEN_FOOD);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format(ConfigExMessage.EMPTY_STRING_EX_MESSAGE, "Animal name"));
        }
        this.name = name;
    }

    protected void setFoodEaten(int foodAmount) {
        this.foodEaten = foodAmount;
    }

    private void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format(ConfigExMessage.EMPTY_STRING_EX_MESSAGE, "Animal type"));
        }
        this.type = type;
    }

    private void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException(ConfigExMessage.NEGATIVE_WEIGHT_EX_MESSAGE);
        }
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getFoodEaten() {
        return this.foodEaten;
    }

    public abstract String makeSound();

    public abstract void eat(Food food);
}
