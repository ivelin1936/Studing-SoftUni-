package com.company.problem7CompanyHierarchy.models;

public class Department {

    private String department;

    public Department(String department) {
        this.setDepartment(department);
    }

    public String getDepartment() {
        return department;
    }

    private void setDepartment(String department) {
        if (department == null
                || !department.equals("Production")
                && !department.equals("Accounting")
                && !department.equals("Sales")
                && !department.equals("Marketing")) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.department = department;
    }
}
