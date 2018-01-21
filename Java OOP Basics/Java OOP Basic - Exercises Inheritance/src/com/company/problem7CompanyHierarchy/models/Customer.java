package com.company.problem7CompanyHierarchy.models;

public class Customer extends Person {

    private double netPurchaseAmount;

    public Customer(int id, String firstName, String lastName, double netPurchaseAmount) {
        super(id, firstName, lastName);
        this.setNetPurchaseAmount(netPurchaseAmount);
    }

    public double getTotalAmountSpent() {
        return netPurchaseAmount;
    }

    private void setNetPurchaseAmount(double netPurchaseAmount) {
        if (netPurchaseAmount < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.netPurchaseAmount = netPurchaseAmount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "netPurchaseAmount=" + netPurchaseAmount +
                '}';
    }
}
