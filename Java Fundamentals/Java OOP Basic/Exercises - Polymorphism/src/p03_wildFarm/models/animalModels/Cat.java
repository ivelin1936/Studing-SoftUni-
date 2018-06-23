package p03_wildFarm.models.animalModels;

import p03_wildFarm.models.foodModels.Food;
import p03_wildFarm.util.ConfigConstants;
import p03_wildFarm.util.ConfigExMessage;

import java.text.DecimalFormat;

public class Cat extends Felime {

    private static final String DEFAULT_SOUND = "Meowwww";
    private static final String BREED_STR = "Breed";

    private String breed;

    public Cat(String name, String type, double weight, String livingRegion, String breed) {
        super(name, type, weight, livingRegion);
        this.setBreed(breed);
    }

    private void setBreed(String breed) {
        if (breed == null || breed.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format(ConfigExMessage.EMPTY_STRING_EX_MESSAGE, BREED_STR));
        }
        this.breed = breed;
    }

    private String getBreed() {
        return this.breed;
    }

    @Override
    public String makeSound() {
        return DEFAULT_SOUND;
    }

    @Override
    public void eat(Food food) {
        this.setFoodEaten(this.getFoodEaten() + food.getQuantity());
    }

    @Override
    public String toString() {
//        {AnimalType} [{AnimalName}, {CatBreed}, {AnimalWeight}, {AnimalLivingRegion}, {FoodEaten}]
        return String.format(ConfigConstants.CAT_TO_STRING_PATTERN,
                this.getType(),
                this.getName(),
                this.getBreed(),
                new DecimalFormat(ConfigConstants.DECIMAL_FORMAT_PATTERN).format(this.getWeight()),
                this.getLivingRegion(),
                this.getFoodEaten());
    }
}
