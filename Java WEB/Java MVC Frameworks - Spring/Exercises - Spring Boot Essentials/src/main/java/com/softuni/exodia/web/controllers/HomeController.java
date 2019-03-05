package com.softuni.exodia.web.controllers;

import com.softuni.exodia.domain.models.service.DocumentServiceModel;
import com.softuni.exodia.domain.models.view.DocumentHomeViewModel;
import com.softuni.exodia.service.documentService.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(DocumentService documentService,
                          ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ModelAndView renderWelcomePage(ModelAndView model, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return this.redirect("home");
        }

        return this.view("index", model);
    }

    @GetMapping("/home")
    public ModelAndView renderHomePage(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return this.redirect("login");
        }

        List<DocumentHomeViewModel> viewModels = this.documentService.findAll().stream()
                .map(docServiceModel -> {
                    DocumentHomeViewModel viewModel =
                            this.modelMapper.map(docServiceModel, DocumentHomeViewModel.class);

                    if (docServiceModel.getTitle().length() > 12) {
                        String cuttedTitle = docServiceModel.getTitle().substring(0, 12) + "...";
                        viewModel.setTitle(cuttedTitle);
                    }

                    return viewModel;
                })
                .collect(Collectors.toList());

        modelAndView.addObject("documents", viewModels);
        return this.view("home", modelAndView);
    }
}
