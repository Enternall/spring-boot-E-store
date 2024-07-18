package com.kursovaya.shop.repository;

import com.kursovaya.shop.domain.Order;
import com.kursovaya.shop.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByCustomerIdAndStatus(long customerId, OrderStatus status);
}
