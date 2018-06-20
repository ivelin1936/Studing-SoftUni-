package p06_animals.models;

public class Kitten extends Cat {

    private static final String DEFAULT_SOUND = "Miau";

    public Kitten(String animalType, String name, int age, String gender) {
        super(animalType, name, age, gender);
    }

    @Override
    public String produceSound() {
        return DEFAULT_SOUND;
    }
}
