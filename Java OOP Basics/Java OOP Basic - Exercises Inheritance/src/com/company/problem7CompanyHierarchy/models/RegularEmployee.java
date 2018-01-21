package com.company.problem7CompanyHierarchy.models;

public class RegularEmployee extends Employee {

    public RegularEmployee(int id, String firstName, String lastName, double salary, Department department) {
        super(id, firstName, lastName, salary, department);
    }

    @Override
    public String toString() {
        return "RegularEmployee{}";
    }
}
