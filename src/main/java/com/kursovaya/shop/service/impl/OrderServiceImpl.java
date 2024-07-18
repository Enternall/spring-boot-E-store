package com.kursovaya.shop.service.impl;

import com.kursovaya.shop.domain.Order;
import com.kursovaya.shop.enums.OrderStatus;
import com.kursovaya.shop.repository.OrderRepository;
import com.kursovaya.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order getCart(long customerId) {
        return orderRepository.findByCustomerIdAndStatus(customerId, OrderStatus.CHECKOUT);
    }

    public void save(Order cart) {
        orderRepository.save(cart);
    }

}
