package com.kursovaya.shop.controller;

import com.kursovaya.shop.domain.Customer;
import com.kursovaya.shop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public String customerPanel(Principal principal, Model model){
        log.info(String.valueOf(principal));
        Customer customer = customerService.findByUsername(principal.getName());

        if (customer != null) {
            model.addAttribute("customer", customer);
        }else {
            return "error/404";
        }

        return "customer";
    }
}
