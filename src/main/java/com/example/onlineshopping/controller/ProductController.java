package com.example.onlineshopping.controller;

import com.example.onlineshopping.domain.Order;
import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.Review;
import com.example.onlineshopping.dto.ProductRequest;
import com.example.onlineshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @PatchMapping("/products/{id}")
    public Product updateProduct(@RequestBody ProductRequest productRequest, @PathVariable long id){
        return productService.updateProduct(productRequest, id);
    }

    @GetMapping("/products/{id}/reviews")
    public List<Review> getReviewsOfProduct(@PathVariable long id){
        return productService.getReviewsOfProduct(id);
    }

    @GetMapping("products/{id}/orders")
    public List<Order> getOrdersOfProduct(@PathVariable long id){
        return productService.getOrdersOfProduct(id);
    }
}
