package fdmc.domain.entities;

public class Cat {

    private String name;
    private String breed;
    private String color;
    private int age;

    public Cat(String name, String breed, String color, int age) {
        this.setName(name);
        this.setBreed(breed);
        this.setColor(color);
        this.setAge(age);
    }

    public Cat() {
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    private void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    private void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }
}
