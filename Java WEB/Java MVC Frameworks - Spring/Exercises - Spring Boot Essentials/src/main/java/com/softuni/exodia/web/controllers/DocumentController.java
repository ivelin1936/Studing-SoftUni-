package com.softuni.exodia.web.controllers;

import com.softuni.exodia.domain.models.binding.DocumentScheduleBindingModel;
import com.softuni.exodia.domain.models.service.DocumentServiceModel;
import com.softuni.exodia.domain.models.view.DocumentViewModel;
import com.softuni.exodia.service.documentService.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class DocumentController extends BaseController {

    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentController(DocumentService documentService,
                              ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/schedule")
    public ModelAndView renderSchedule(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return this.redirect("login");
        }
        modelAndView.addObject("bindingModel", new DocumentScheduleBindingModel());
        return this.view("schedule", modelAndView);
    }

    @PostMapping("/schedule")
    public ModelAndView shcedule(@ModelAttribute(name = "bindingModel") @Valid DocumentScheduleBindingModel bindingModel,
                                 BindingResult bindingResult,
                                 ModelAndView modelAndView,
                                 RedirectAttributes redirectAttributes) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return this.view("schedule", modelAndView);
        }

        DocumentServiceModel serviceModel = this.modelMapper.map(bindingModel, DocumentServiceModel.class);

        try {
            DocumentServiceModel document = this.documentService.addDocument(serviceModel);
            return this.redirect("details/" + document.getId());
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();

            redirectAttributes.addFlashAttribute("title", bindingModel.getTitle());
            redirectAttributes.addFlashAttribute("content", bindingModel.getContent());
            return this.redirect("schedule");
        }
    }

    @GetMapping("/details/{id}")
    public ModelAndView renderDetailsPage(@PathVariable("id") String id,
                                          ModelAndView modelAndView,
                                          HttpSession session) {
        if (session.getAttribute("username") == null) {
            this.redirect("login");
        }

        DocumentServiceModel documentServiceModel = this.documentService.findById(id);
        DocumentViewModel documentViewModel = this.modelMapper.map(documentServiceModel, DocumentViewModel.class);

        modelAndView.addObject("model", documentViewModel);
        return this.view("details", modelAndView);
    }

    @GetMapping("/print/{id}")
    public ModelAndView renderPrintPage(@PathVariable("id") String id,
                                        ModelAndView modelAndView,
                                        HttpSession session) {
        if (session.getAttribute("username") == null) {
            this.redirect("login");
        }

        DocumentServiceModel serviceModel = this.documentService.findById(id);
        if (serviceModel == null) {
            //404 Not Found
            return this.redirect("home");
        }

        DocumentViewModel viewModel = this.modelMapper.map(serviceModel, DocumentViewModel.class);
        modelAndView.addObject("model", viewModel);
        return this.view("print", modelAndView);
    }

    @PostMapping("/print/{id}")
    public ModelAndView print(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isPrinted = this.documentService.printDocumentById(id);

        if (!isPrinted) {
            //ErrorMessage -> something went wrong
            return this.redirect("home");
        }

        return redirect("home");
    }
}
