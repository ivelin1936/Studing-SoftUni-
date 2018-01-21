package com.company.problem7CompanyHierarchy.models;

public class Employee extends Person{

    private double salary;
    private String department;

    public Employee(int id, String firstName, String lastName, double salary, Department department) {
        super(id, firstName, lastName);
        this.setSalary(salary);
        this.setDepartment(department);
    }

    public double getSalary() {
        return salary;
    }

    private void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    private void setDepartment(Department department) {
        this.department = department.getDepartment();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}
