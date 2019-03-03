package com.softuni.realestate.domain.models.service;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OfferFindServiceModel {

    @DecimalMin("0.0001")
    private BigDecimal familyBudget;

    @NotNull
    @NotEmpty
    private String apartmentType;

    @NotNull
    @NotEmpty
    private String familyName;

    public OfferFindServiceModel() {
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
