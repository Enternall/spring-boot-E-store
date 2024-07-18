package com.kursovaya.shop.domain;

import com.kursovaya.shop.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@RequiredArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private long customerId;

    private final Instant orderDate = Instant.now();

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CHECKOUT;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    private BigDecimal totalAmount = BigDecimal.ZERO;

    public void checkout() {
        this.status = OrderStatus.IN_DELIVERY;
    }

    public void addItem(Product product) {
        OrderItem item = items.stream().filter(items -> items.getProduct().equals(product))
                .findAny().orElse(null);
        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
        } else {
            items.add(new OrderItem(this, product, 1));
        }
        totalAmount = totalAmount.add(product.getPrice());
    }

    public void removeItem(Product product) {
        OrderItem item = items.stream().filter(items -> items.getProduct().equals(product))
                .findAny().orElse(null);

        if (item != null) {
            if (item.getQuantity() <= 1) {
                items.remove(item);
            } else {
                item.setQuantity(item.getQuantity() - 1);
            }
            totalAmount = totalAmount.subtract(product.getPrice());
        }
    }

}
