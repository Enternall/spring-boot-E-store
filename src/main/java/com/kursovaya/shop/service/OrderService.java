package com.kursovaya.shop.service;

import com.kursovaya.shop.domain.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order getCart(long customerId);

    void save(Order cart);

}
