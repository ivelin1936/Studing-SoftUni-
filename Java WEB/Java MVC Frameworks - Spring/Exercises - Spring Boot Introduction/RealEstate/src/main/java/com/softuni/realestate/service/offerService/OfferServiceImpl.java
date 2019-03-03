package com.softuni.realestate.service.offerService;

import com.softuni.realestate.domain.entities.Offer;
import com.softuni.realestate.domain.models.service.OfferFindServiceModel;
import com.softuni.realestate.domain.models.service.OfferServiceModel;
import com.softuni.realestate.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String DEFAULT_INVALID_MODEL_MESSAGE = "Invalid model!";
    private static final String DEFAULT_NOT_FOUND_OFFER_MESSAGE = "Does not found offer by this criteria.";
    private static final int DEFAULT_CONSTRAINT_VIOLATION_SIZE = 0;

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository,
                            ModelMapper modelMapper,
                            Validator validator) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }


    @Override
    public void register(OfferServiceModel serviceModel) {
        if (validator.validate(serviceModel).size() != DEFAULT_CONSTRAINT_VIOLATION_SIZE) {
            throw new IllegalArgumentException(DEFAULT_INVALID_MODEL_MESSAGE);
        }

        Offer offerEntity = this.modelMapper.map(serviceModel, Offer.class);
        this.offerRepository.saveAndFlush(offerEntity);
    }

    @Override
    public List<OfferServiceModel> findAll() {
        return this.offerRepository.findAll()
                .stream()
                .map(entity -> this.modelMapper.map(entity, OfferServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void findOffer(OfferFindServiceModel model) {
        if (this.validator.validate(model).size() != DEFAULT_CONSTRAINT_VIOLATION_SIZE) {
            throw new IllegalArgumentException(DEFAULT_INVALID_MODEL_MESSAGE);
        }

        Offer offer = this.offerRepository.findAll()
                .stream()
                .filter(o -> o.getApartmentType().toLowerCase().equals(model.getApartmentType().toLowerCase())
                        && this.isFamilyBudgetEnough(model.getFamilyBudget(), o))
                .findFirst()
                .orElse(null);

        if (offer == null) {
            throw new IllegalArgumentException(DEFAULT_NOT_FOUND_OFFER_MESSAGE);
        }

        this.offerRepository.delete(offer);
    }

    private boolean isFamilyBudgetEnough(BigDecimal familyBudget, Offer offer) {
       BigDecimal finalPrice = offer.getApartmentRent()
               .add(offer.getAgencyCommission()
                       .divide(new BigDecimal(100))
                       .multiply(offer.getApartmentRent()));

        return familyBudget.compareTo(finalPrice) >= 0;
    }
}