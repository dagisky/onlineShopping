package com.example.onlineshopping.repository;


import com.example.onlineshopping.domain.Buyer;
import com.example.onlineshopping.domain.Order;
import com.example.onlineshopping.domain.Product;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Long> {

    List<Order> findAllByBuyer(Buyer buyer);

    Order findFirstByProduct(Product product);

    List<Order> findAllByProduct(Product product);
}
