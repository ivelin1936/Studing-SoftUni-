package com.company.centers;

public abstract class Center {

    private String name;

    public Center(String name) {
        this.setName(name);
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
}
