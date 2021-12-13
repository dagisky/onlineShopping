package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Order;


public interface OrderService {

    Order findOrderById(long id);

    Order cancelOrder(long orderId);

    Order shipOrder(long orderId);

    Order deliverOrder(long orderId);
}
