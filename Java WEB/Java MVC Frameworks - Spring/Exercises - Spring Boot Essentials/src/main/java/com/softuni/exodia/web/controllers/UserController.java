package com.softuni.exodia.web.controllers;

import com.softuni.exodia.domain.models.binding.UserLoginBindingModel;
import com.softuni.exodia.domain.models.binding.UserRegisterBindingModel;
import com.softuni.exodia.domain.models.service.UserServiceModel;
import com.softuni.exodia.service.userService.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView renderRegisterPage(ModelAndView model, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return this.redirect("home");
        }

        model.addObject("bindingModel", new UserRegisterBindingModel());
        return this.view("register", model);
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute(name = "bindingModel") @Valid UserRegisterBindingModel bindingModel,
                                 BindingResult bindingResult,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes,
                                 ModelAndView modelAndView) {
        if (session.getAttribute("username") != null) {
            return this.redirect("home");
        }

        //check for errors
        if (bindingResult.hasErrors()) {
            return this.view("register", modelAndView);
        }

        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("username", bindingModel.getUsername());
            redirectAttributes.addFlashAttribute("email", bindingModel.getEmail());
            return this.redirect("register");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(bindingModel, UserServiceModel.class);
        try {
            this.userService.register(userServiceModel);
            return this.redirect("login");
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();

            redirectAttributes.addFlashAttribute("username", bindingModel.getUsername());
            redirectAttributes.addFlashAttribute("email", bindingModel.getEmail());
            return this.redirect("register");
        }
    }

    @GetMapping("/login")
    public ModelAndView renderLoginPage(ModelAndView model, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return this.redirect("home");
        }

        model.addObject("bindingModel", new UserLoginBindingModel());
        return this.view("login", model);
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute(name = "bindingModel") @Valid UserLoginBindingModel bindingModel,
                              BindingResult bindingResult,
                              ModelAndView modelAndView,
                              HttpSession session) {
        if (session.getAttribute("username") != null) {
            return this.redirect("home");
        }

        //check for errors
        if (bindingResult.hasErrors()) {
            return this.view("login", modelAndView);
        }

        UserServiceModel serviceModel = this.userService
                .login(this.modelMapper.map(bindingModel, UserServiceModel.class));
        if (serviceModel == null) {
            //Show invalid credentials error message
            return this.redirect("login");
        }

        session.setAttribute("username", serviceModel.getUsername());
        session.setAttribute("userId", serviceModel.getId());

        return this.redirect("home");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return this.redirect("");
    }
}
