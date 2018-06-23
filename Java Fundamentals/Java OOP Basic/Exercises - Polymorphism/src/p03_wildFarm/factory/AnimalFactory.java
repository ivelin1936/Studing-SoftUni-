package p03_wildFarm.factory;

import p03_wildFarm.models.animalModels.*;
import p03_wildFarm.util.ConfigConstants;
import p03_wildFarm.util.ConfigExMessage;

public final class AnimalFactory {

    public AnimalFactory() {
    }

    public Animal produceAnimal(String line) {
        String[] animalTokens = line.split("\\s+");
//        {AnimalType} {AnimalName} {AnimalWeight} {AnimalLivingRegion} [{CatBreed} = Only if its cat]
        String type = animalTokens[ConfigConstants.TYPE_INDEX];
        String name = animalTokens[ConfigConstants.NAME_INDEX];
        double weight = Double.parseDouble(animalTokens[ConfigConstants.WEIGHT_INDEX]);
        String livingRegion = animalTokens[ConfigConstants.LIVING_REGION_INDEX];

        switch (type) {
            case ConfigConstants.CAT_TYPE:
                String breed = animalTokens[ConfigConstants.BREED_INDEX];
                return new Cat(name, type, weight, livingRegion, breed);
            case ConfigConstants.TIGER_TYPE:
                return new Tiger(name, type, weight, livingRegion);
            case ConfigConstants.ZEBRA_TYPE:
                return new Zebra(name, type, weight, livingRegion);
            case ConfigConstants.MOUSE_TYPE:
                return new Mouse(name, type, weight, livingRegion);
            default:
                throw new IllegalArgumentException(ConfigExMessage.INVALID_ANIMAL_TYPE_EX_MESSAGE);
        }
    }
}
