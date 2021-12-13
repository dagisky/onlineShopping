package com.example.onlineshopping.repository;


import com.example.onlineshopping.domain.Buyer;
import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.Review;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findAllByBuyer(Buyer buyer);
    List<Review> findAll();
    List<Review> findAllByProduct(Product product);
}
