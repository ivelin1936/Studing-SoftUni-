package p06_animals.models;

public class Cat extends Animal {

    private static final String DEFAULT_SOUND = "MiauMiau";

    public Cat(String animalType, String name, int age, String gender) {
        super(animalType, name, age, gender);
    }

    @Override
    public String produceSound() {
        return DEFAULT_SOUND;
    }
}
