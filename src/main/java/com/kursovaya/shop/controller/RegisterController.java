package com.kursovaya.shop.controller;

import com.kursovaya.shop.validator.UserValidator;
import com.kursovaya.shop.domain.Customer;
import com.kursovaya.shop.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private final CustomerService customerService;
    private final UserValidator userValidator;

    @Autowired
    public RegisterController(CustomerService customerService, UserValidator userValidator) {
        this.customerService = customerService;
        this.userValidator = userValidator;
    }

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new Customer());

        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") Customer customerForm, BindingResult bindingResult) {
        userValidator.validate(customerForm, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()));
            return "register";
        }

        customerService.save(customerForm);
        customerService.login(customerForm.getUsername(), customerForm.getPasswordConfirm());

        return "redirect:/home";
    }
}
