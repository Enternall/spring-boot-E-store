package com.kursovaya.shop.service;

import com.kursovaya.shop.domain.Customer;

public interface CustomerService {
    void save(Customer customer);
    void login(String username, String password);
    Customer findByUsername(String username);
    Customer findByEmail(String email);
    Customer findById(long id);
}
