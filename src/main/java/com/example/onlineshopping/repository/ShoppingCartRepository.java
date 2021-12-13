package com.example.onlineshopping.repository;


import com.example.onlineshopping.domain.Buyer;
import com.example.onlineshopping.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

    ShoppingCart findShoppingCartByBuyer(Buyer buyer);
    ShoppingCart findFirstByBuyer(Buyer buyer);
}
