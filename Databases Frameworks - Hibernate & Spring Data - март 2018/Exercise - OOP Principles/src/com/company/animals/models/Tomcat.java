package com.company.animals.models;

public class Tomcat extends Cat {

    public static final String DEFAULT_MESSAGE_FOR_INVALID_INPUT = "Invalid input!";

    public Tomcat(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    protected void setGender(String gender) {
        if (gender == null || gender.isEmpty() || gender.equals("Female")) {
            throw new IllegalArgumentException(DEFAULT_MESSAGE_FOR_INVALID_INPUT);
        }
        super.setGender(gender);
    }

    @Override
    public void produceSound() {
        System.out.println("Give me one million b***h");
    }

    @Override
    public String toString() {
        return String.format("Tomcat%n%s %d %s", this.getName(), this.getAge(), this.getGender());
    }
}
