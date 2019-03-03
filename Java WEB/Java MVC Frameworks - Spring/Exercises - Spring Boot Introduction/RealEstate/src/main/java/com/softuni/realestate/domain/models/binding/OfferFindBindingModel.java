package com.softuni.realestate.domain.models.binding;

import java.math.BigDecimal;

public class OfferFindBindingModel {

    private BigDecimal familyBudget;
    private String apartmentType;
    private String familyName;

    public OfferFindBindingModel() {
    }

    public BigDecimal getFamilyBudget() {
        return this.familyBudget;
    }

    public void setFamilyBudget(BigDecimal familyBudget) {
        this.familyBudget = familyBudget;
    }

    public String getApartmentType() {
        return this.apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
