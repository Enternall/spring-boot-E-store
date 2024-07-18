package com.kursovaya.shop.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @NotNull
    private String username;

    @Email
    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String password;

    @NotEmpty
    @NotNull
    private String passwordConfirm;

    private String firstName;

    private String lastName;

    private int age;

    private String city;

    @NotEmpty
    @NotNull
    private String gender;

    private BigDecimal balance;

    @OneToMany
    @JoinColumn(name = "customerId")
    private List<Order> orders = new ArrayList<>();

}
