package com.company.animals;

public abstract class Animal {

    private String name;
    private int age;
    private int specialSkill;
    private CleansingStatus cleansingStatus;
    private CastrateStatus castrateStatus;

    public Animal(String name, int age, int specialSkill) {
        this.setName(name);
        this.setAge(age);
        this.setSpecialSkill(specialSkill);
        this.cleansingStatus = CleansingStatus.Uncleansed;
        this.castrateStatus = CastrateStatus.NonCastrated;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be less than 0.");
        }
        this.age = age;
    }

    public int getSpecialSkill() {
        return specialSkill;
    }

    private void setSpecialSkill(int specialSkill) {
        if (specialSkill < 0) {
            throw new IllegalArgumentException("special Skills cannot be less than 0.");
        }
        this.specialSkill = specialSkill;
    }

    public CleansingStatus getCleansingStatus() {
        return cleansingStatus;
    }

    public void setCleansingStatus(CleansingStatus cleansingStatus) {
        this.cleansingStatus = cleansingStatus;
    }

    public CastrateStatus getCastrateStatus() {
        return castrateStatus;
    }

    public void setCastrateStatus(CastrateStatus castrateStatus) {
        this.castrateStatus = castrateStatus;
    }
}
