package com.softuni.realestate.service.offerService;

import com.softuni.realestate.domain.models.service.OfferFindServiceModel;
import com.softuni.realestate.domain.models.service.OfferServiceModel;

import java.util.List;

public interface OfferService {

    void register(OfferServiceModel serviceModel);

    List<OfferServiceModel> findAll();

    void findOffer(OfferFindServiceModel findServiceModel);
}
