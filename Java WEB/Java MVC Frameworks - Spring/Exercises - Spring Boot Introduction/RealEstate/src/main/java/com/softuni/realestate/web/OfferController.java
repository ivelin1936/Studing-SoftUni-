package com.softuni.realestate.web;

import com.softuni.realestate.domain.models.binding.OfferFindBindingModel;
import com.softuni.realestate.domain.models.binding.OfferRegisterBindingModel;
import com.softuni.realestate.domain.models.service.OfferFindServiceModel;
import com.softuni.realestate.domain.models.service.OfferServiceModel;
import com.softuni.realestate.service.offerService.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService,
                           ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/find")
    public String getFindOfferPage() {
        return "find.html";
    }

    @GetMapping("/reg")
    public String getRegisterOfferPage() {
        return "register.html";
    }

    @PostMapping("/reg")
    public String registerConfirm(@ModelAttribute(name = "model") OfferRegisterBindingModel model) {
        OfferServiceModel serviceModel = this.modelMapper.map(model, OfferServiceModel.class);

        try {
            this.offerService.register(serviceModel);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();

            return "redirect:/reg";
        }

        return "redirect:/";
    }

    @PostMapping("/find")
    public String findOffer(@ModelAttribute(name = "model") OfferFindBindingModel model) {
        OfferFindServiceModel serviceModel = this.modelMapper.map(model, OfferFindServiceModel.class);

        try {
            this.offerService.findOffer(serviceModel);
        } catch (IllegalArgumentException iae) {
            //ErrorMessage...
            iae.printStackTrace();
            return "redirect:/find";
        }

        return "redirect:/";
    }
}
