package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.ShoppingCart;


public interface ShoppingCartService {


    Product addProductToShoppingCart(Product product);

    ShoppingCart addShoppingCart(ShoppingCart shoppingCart);

    void deleteShoppingCart(ShoppingCart shoppingCart);


}
