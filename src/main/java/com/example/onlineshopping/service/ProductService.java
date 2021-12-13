package com.example.onlineshopping.service;


import com.example.onlineshopping.domain.Order;
import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.Review;
import com.example.onlineshopping.dto.ProductRequest;
import java.util.List;


public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(long id);

    void deleteProduct(long id);

    Product updateProduct(ProductRequest product, long id);

    List<Review> getReviewsOfProduct(long id);

    List<Order> getOrdersOfProduct(long id);
}
