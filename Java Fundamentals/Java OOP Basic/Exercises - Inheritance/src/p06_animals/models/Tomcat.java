package p06_animals.models;

public class Tomcat extends Cat {

    private static final String DEFAULT_SOUND = "Give me one million b***h";

    public Tomcat(String animalType, String name, int age, String gender) {
        super(animalType, name, age, gender);
    }

    @Override
    public String produceSound() {
        return DEFAULT_SOUND;
    }
}
