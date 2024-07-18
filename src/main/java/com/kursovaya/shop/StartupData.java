package com.kursovaya.shop;

import com.kursovaya.shop.domain.Customer;
import com.kursovaya.shop.service.ProductService;
import com.kursovaya.shop.service.CustomerService;
import com.kursovaya.shop.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class  StartupData implements CommandLineRunner {
    private final CustomerService customerService;
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(StartupData.class);

    @Autowired
    public StartupData(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        adminAccount();
        userAccount();
        exampleProducts();
    }

    private void userAccount(){
        Customer customer = new Customer();

        customer.setUsername("user");
        customer.setPassword("user");
        customer.setPasswordConfirm("user");
        customer.setGender("Male");
        customer.setEmail("user@example.com");

        customerService.save(customer);
    }

    private void adminAccount(){
        Customer admin = new Customer();

        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setPasswordConfirm("admin");
        admin.setGender("Male");
        admin.setEmail("admin@example.com");

        customerService.save(admin);
    }

    private void exampleProducts(){
        final String NAME = "Example Name";
        final String IMAGE_URL = "https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX7389458.jpg";
        final String DESCRIPTION = "Example Description";
        final BigDecimal PRICE = BigDecimal.valueOf(22);

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();
        Product product6 = new Product();
        Product product7 = new Product();

        product1.setName("Quadricycle LG");
        product1.setImage("/assets/Product1.png");
        product1.setPrice(new BigDecimal(15500));

        product2.setName("Ice for kitchen");
        product2.setImage("assets/Product2.png");
        product2.setPrice(new BigDecimal(1399));

        product3.setName("Sofa GUCCI");
        product3.setImage("assets/Product3.png");
        product3.setPrice(new BigDecimal(799));

        product4.setName("Bicycle NVIDIA");
        product4.setImage("assets/Product4.png");
        product4.setPrice(new BigDecimal(400));

        product5.setName("Hairband Smart TV");
        product5.setImage("assets/Product5.png");
        product5.setPrice(new BigDecimal(550));

        product6.setName("Walk in a desert");
        product6.setImage("assets/Product6.png");
        product6.setPrice(new BigDecimal(350));

        product7.setName("Toy Desert Eagle");
        product7.setImage("assets/Product7.png");
        product7.setPrice(new BigDecimal(1000));

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
        productService.save(product5);
        productService.save(product6);
        productService.save(product7);
    }
}
