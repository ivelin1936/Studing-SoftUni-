package com.company.problem7CompanyHierarchy.models;

import java.util.HashSet;
import java.util.Set;

public class Manager extends Employee {

    private Set<Employee> employeeSet;

    public Manager(int id, String firstName, String lastName, double salary, Department department) {
        super(id, firstName, lastName, salary, department);
        this.employeeSet = new HashSet<>();
    }

    public void addEmployee(Employee employee) {
        employeeSet.add(employee);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "employeeSet=" + employeeSet +
                '}';
    }
}
