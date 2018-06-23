package p03_wildFarm.models.animalModels;

import p03_wildFarm.util.ConfigConstants;
import p03_wildFarm.util.ConfigExMessage;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {

    private static final String LIVING_REGION_STR = "Living region";

    private String livingRegion;

    public Mammal(String name, String type, double weight, String livingRegion) {
        super(name, type, weight);
        this.setLivingRegion(livingRegion);
    }

    private void setLivingRegion(String livingRegion) {
        if (livingRegion == null || livingRegion.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format(ConfigExMessage.EMPTY_STRING_EX_MESSAGE, LIVING_REGION_STR));
        }
        this.livingRegion = livingRegion;
    }

    public String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public String toString() {
//        {AnimalType} [{AnimalName}, {AnimalWeight}, {AnimalLivingRegion}, {FoodEaten}]
        return String.format(ConfigConstants.MAMMAL_TO_STRING_PATTERN,
                this.getType(),
                this.getName(),
                new DecimalFormat(ConfigConstants.DECIMAL_FORMAT_PATTERN).format(this.getWeight()),
                this.getLivingRegion(),
                this.getFoodEaten());
    }
}
