package com.kursovaya.shop.controller;

import com.kursovaya.shop.domain.Customer;
import com.kursovaya.shop.domain.Order;
import com.kursovaya.shop.domain.Product;
import com.kursovaya.shop.service.CustomerService;
import com.kursovaya.shop.service.OrderService;
import com.kursovaya.shop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final ProductService productService;
    private final CustomerService customerService;

    public OrderController(OrderService orderService, ProductService productService, CustomerService customerService) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping("/cart")
    public String cart(Principal principal, Model model){
        Order cart = getCart(principal.getName());
        model.addAttribute("items", cart.getItems());
        model.addAttribute("totalPrice", cart.getTotalAmount());

        orderService.save(cart);
        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(Principal principal, @PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            Order cart = getCart(principal.getName());
            cart.addItem(product);
            logger.info(
                    String.format("Product with id: %s added to shopping cart of user: %s.",
                    id, principal.getName())
            );
            orderService.save(cart);
        }
        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(Principal principal, @PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            Order cart = getCart(principal.getName());
            cart.removeItem(product);
            logger.info(
                    String.format("Product with id: %s removed from shopping cart of user: %s.",
                            id, principal.getName())
            );
            orderService.save(cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearProductsInCart(Principal principal){
        Order cart = getCart(principal.getName());
        cart.getItems().clear();
        orderService.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String cartCheckout(Principal principal){
        Order cart = getCart(principal.getName());
        cart.checkout();
        orderService.save(cart);
        return "redirect:/cart";
    }

    private Order getCart(String username) {
        Customer customer = customerService.findByUsername(username);
        Order order = orderService.getCart(customer.getId());
        order = order == null? new Order(customer.getId()) : order;
        return order;
    }
}
