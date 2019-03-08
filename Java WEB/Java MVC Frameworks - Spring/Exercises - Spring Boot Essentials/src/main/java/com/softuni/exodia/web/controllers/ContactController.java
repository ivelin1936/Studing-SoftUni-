package com.softuni.exodia.web.controllers;

import com.softuni.exodia.domain.models.binding.ContactFormBindingModel;
import com.softuni.exodia.util.constants.MyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ContactController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);

    private final JavaMailSender emailSender;

    @Autowired
    public ContactController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @GetMapping("/contact")
    public ModelAndView renderContactPage(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return this.redirect("login");
        }

        return this.view("contact", modelAndView);
    }

    @PostMapping("/contact")
    public ModelAndView contactAction(@ModelAttribute(name = "bindingModel") ContactFormBindingModel bindingModel) {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.MY_EMAIL);
        message.setFrom(bindingModel.getFrom());
        message.setSubject(bindingModel.getSubject());
        message.setText(bindingModel.getMessage());

        try {
            // Send Message!
            this.emailSender.send(message);
            return this.redirect("home");
        } catch (MailException e) {
            logger.error("Error Sending email: " + e.getMessage());
            //Something went wrong
            return this.redirect("contact");
        }
    }
}
