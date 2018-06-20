package p06_animals.models;

public class Dog extends Animal {

    private static final String DEFAULT_SOUND = "BauBau";

    public Dog(String animalType, String name, int age, String gender) {
        super(animalType, name, age, gender);
    }

    @Override
    public String produceSound() {
        return DEFAULT_SOUND;
    }
}
