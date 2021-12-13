package com.example.onlineshopping.service.implementation;

import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.ShoppingCart;
import com.example.onlineshopping.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Override
    public Product addProductToShoppingCart(Product product) {
        return null;
    }

    @Override
    public ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public void deleteShoppingCart(ShoppingCart shoppingCart) {

    }
}
