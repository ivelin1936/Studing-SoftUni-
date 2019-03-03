package com.softuni.realestate.web;

import com.softuni.realestate.domain.models.view.OfferViewModel;
import com.softuni.realestate.service.offerService.OfferService;
import com.softuni.realestate.util.htmlReader.HtmlFileReader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private static final String INDEX_HTML_FILE_PATH = "C:\\Users\\Admin\\Desktop\\RealEstate\\src\\main\\resources\\static\\index.html";

    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final HtmlFileReader htmlFileReader;

    @Autowired
    public HomeController(OfferService offerService,
                          ModelMapper modelMapper,
                          HtmlFileReader htmlFileReader) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.htmlFileReader = htmlFileReader;
    }

    @GetMapping("/")
    @ResponseBody
    public String getHomePage() throws IOException {
        return this.buildHtml();
    }

    private String buildHtml() throws IOException {
        List<OfferViewModel> offerViewModels = this.offerService.findAll()
                .stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, OfferViewModel.class))
                .collect(Collectors.toList());

        StringBuilder offersHtmlBuilder = new StringBuilder();
        if (offerViewModels.size() == 0) {
            offersHtmlBuilder
                    .append("<div class=\"apartment\" style=\"border: red solid 2px;\">")
                    .append("<p>There aren't any offers!</p>")
                    .append("</div>");
        } else {
            offerViewModels
                    .stream()
                    .map(model -> String.format("<div class=\"apartment\">\n" +
                                    "<p>Rent: %s</p>\n" +
                                    "<p>Type: %s</p>\n" +
                                    "<p>Commission: %s</p>\n" +
                                    "</div>",
                            model.getApartmentRent(),
                            model.getApartmentType(),
                            model.getAgencyCommission()))
                    .forEach(htmlOfferDivBlock -> offersHtmlBuilder
                            .append(htmlOfferDivBlock)
                            .append(System.lineSeparator()));
        }

        return this.htmlFileReader
                .readFile(INDEX_HTML_FILE_PATH)
                .replace("{{offers}}", offersHtmlBuilder.toString());
    }
}
