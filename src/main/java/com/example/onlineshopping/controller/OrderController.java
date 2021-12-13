package com.example.onlineshopping.controller;

import com.example.onlineshopping.domain.Order;
import com.example.onlineshopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PatchMapping("/orders/{orderId}/cancel") // cancel Order
    public Order cancelOrder(@PathVariable long orderId){
        return orderService.cancelOrder(orderId);
    }

    @PatchMapping("/orders/{orderId}/ship") // cancel Order
    public Order shipOrder(@PathVariable long orderId){
        return orderService.shipOrder(orderId);
    }

    @PatchMapping("/orders/{orderId}/deliver") // cancel Order
    public Order deliverOrder(@PathVariable long orderId){
        return orderService.deliverOrder(orderId);
    }
}
