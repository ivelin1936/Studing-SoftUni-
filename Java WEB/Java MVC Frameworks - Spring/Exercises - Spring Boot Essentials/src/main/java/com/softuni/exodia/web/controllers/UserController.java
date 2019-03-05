package com.softuni.exodia.web.controllers;

import com.softuni.exodia.domain.models.binding.UserLoginBindingModel;
import com.softuni.exodia.domain.models.binding.UserRegisterBindingModel;
import com.softuni.exodia.domain.models.service.UserServiceModel;
import com.softuni.exodia.service.userService.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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

        return this.view("register", model);
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute(name = "bindingModel") UserRegisterBindingModel bindingModel,
                                 HttpSession session) {
        if (session.getAttribute("username") != null) {
            return this.redirect("home");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(bindingModel, UserServiceModel.class);
        try {
            this.userService.register(userServiceModel);
            return this.redirect("login");
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return this.redirect("register");
        }
    }

    @GetMapping("/login")
    public ModelAndView renderLoginPage(ModelAndView model, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return this.redirect("home");
        }

        return this.view("login", model);
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute(name = "bindingModel") UserLoginBindingModel bindingModel,
                              HttpSession session) {
        if (session.getAttribute("username") != null) {
            return this.redirect("home");
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
