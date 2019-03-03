package com.softuni.realestate.domain.models.view;

import java.math.BigDecimal;

public class OfferViewModel {

    private String id;
    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;

    public OfferViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getApartmentRent() {
        return this.apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    public String getApartmentType() {
        return this.apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public BigDecimal getAgencyCommission() {
        return this.agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
