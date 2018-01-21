package com.company.problem7CompanyHierarchy.models;

public class Person {

    private int id;
    private String firstName;
    private String lastName;

    public Person(int id, String firstName, String lastName) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    protected int getId() {
        return id;
    }

    private void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.id = id;
    }

    protected String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        if (firstName == null || firstName.length() < 3) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.firstName = firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        if (lastName == null || lastName.length() < 3) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
