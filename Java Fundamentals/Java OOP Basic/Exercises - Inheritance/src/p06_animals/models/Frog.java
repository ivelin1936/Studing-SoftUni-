package p06_animals.models;

public class Frog extends Animal {

    private static final String DEFAULT_SOUND = "Frogggg";

    public Frog(String animalType, String name, int age, String gender) {
        super(animalType, name, age, gender);
    }

    @Override
    public String produceSound() {
        return DEFAULT_SOUND;
    }
}
