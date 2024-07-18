package com.kursovaya.shop.validator;

import com.kursovaya.shop.domain.Customer;
import com.kursovaya.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final CustomerService customerService;

    @Autowired
    public UserValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Customer customer = (Customer) o;

        //Username and password can't me empty or contain whitespace
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.not_empty");

        // Username must have from 4 characters to 32
        if (customer.getUsername().length() < 4) {
            errors.rejectValue("username", "register.error.username.less_4");
        }
        if(customer.getUsername().length() > 32){
            errors.rejectValue("username","register.error.username.over_32");
        }
        //Username can't be duplicated
        if (customerService.findByUsername(customer.getUsername()) != null) {
            errors.rejectValue("username", "register.error.duplicated.username");
        }
        //Email can't be duplicated
        if (customerService.findByEmail(customer.getEmail()) != null){
            errors.rejectValue("email", "register.error.duplicated.email");
        }
        //Password must have at least 8 characters and max 32
        if (customer.getPassword().length() < 8) {
            errors.rejectValue("password", "register.error.password.less_8");
        }
        if (customer.getPassword().length() > 32){
            errors.rejectValue("password", "register.error.password.over_32");
        }
        //Password must be the same as the confirmation password
        if (!customer.getPasswordConfirm().equals(customer.getPassword())) {
            errors.rejectValue("passwordConfirm", "register.error.diff_password");
        }
        //Age needs to be higher than 13
        if (customer.getAge() <= 13){
            errors.rejectValue("age", "register.error.age_size");
        }
    }
}
