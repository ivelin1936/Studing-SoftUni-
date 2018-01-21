package com.company.problem7CompanyHierarchy.models;

import java.util.HashSet;
import java.util.Set;

public class SalesEmployee extends RegularEmployee {

    private Set<Sale> sales;

    public SalesEmployee(int id, String firstName, String lastName, double salary, Department department) {
        super(id, firstName, lastName, salary, department);
        this.sales = new HashSet<>();
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void addSales(Sale sale) {
        sales.add(sale);
    }

    @Override
    public String toString() {
        return "SalesEmployee{" +
                "sales=" + sales +
                '}';
    }
}
