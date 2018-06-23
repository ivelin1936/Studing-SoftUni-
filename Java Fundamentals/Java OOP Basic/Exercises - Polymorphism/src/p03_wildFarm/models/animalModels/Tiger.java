package p03_wildFarm.models.animalModels;

import p03_wildFarm.models.foodModels.Food;
import p03_wildFarm.util.ConfigExMessage;

public class Tiger extends Felime {

    private static final String DEFAULT_SOUND = "ROAAR!!!";
    private static final String EATEN_FOOD = "Meat";

    public Tiger(String name, String type, double weight, String livingRegion) {
        super(name, type, weight, livingRegion);
    }

    @Override
    public String makeSound() {
        return DEFAULT_SOUND;
    }

    @Override
    public void eat(Food food) {
        if (!food.getClass().getSimpleName().equalsIgnoreCase(EATEN_FOOD)) {
            throw new IllegalArgumentException(String.format(ConfigExMessage.WRONG_TYPE_FOOD_EX_MESSAGE, this.getType()));
        }
        this.setFoodEaten(this.getFoodEaten() + food.getQuantity());
    }
}
