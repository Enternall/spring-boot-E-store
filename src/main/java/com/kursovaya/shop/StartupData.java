package com.kursovaya.shop;

import com.kursovaya.shop.domain.User;
import com.kursovaya.shop.service.ProductService;
import com.kursovaya.shop.service.UserService;
import com.kursovaya.shop.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StartupData implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(StartupData.class);

    @Autowired
    public StartupData(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        adminAccount();
        userAccount();
        exampleProducts();
    }

    private void userAccount(){
        User user = new User();

        user.setUsername("user");
        user.setPassword("user");
        user.setPasswordConfirm("user");
        user.setGender("Male");
        user.setEmail("user@example.com");

        userService.save(user);
    }

    private void adminAccount(){
        User admin = new User();

        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setPasswordConfirm("admin");
        admin.setGender("Male");
        admin.setEmail("admin@example.com");

        userService.save(admin);
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
        product1.setImageUrl("/assets/Product1.png");
        product1.setPrice(new BigDecimal(15500));

        product2.setName("Ice for kitchen");
        product2.setImageUrl("assets/Product2.png");
        product2.setPrice(new BigDecimal(1399));

        product3.setName("Sofa GUCCI");
        product3.setImageUrl("assets/Product3.png");
        product3.setPrice(new BigDecimal(799));

        product4.setName("Bicycle NVIDIA");
        product4.setImageUrl("assets/Product4.png");
        product4.setPrice(new BigDecimal(400));

        product5.setName("Hairband Smart TV");
        product5.setImageUrl("assets/Product5.png");
        product5.setPrice(new BigDecimal(550));

        product6.setName("Walk in a desert");
        product6.setImageUrl("assets/Product6.png");
        product6.setPrice(new BigDecimal(350));

        product7.setName("Toy Desert Eagle");
        product7.setImageUrl("assets/Product7.png");
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
