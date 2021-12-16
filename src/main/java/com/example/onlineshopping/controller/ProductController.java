package com.example.onlineshopping.controller;

import com.example.onlineshopping.domain.Order;
import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.Review;
import com.example.onlineshopping.dto.ProductRequest;
import com.example.onlineshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public EntityModel<Product> getProductById(@PathVariable long id) {
        /**
         * Input:
         *      id: long
         * Returns:
         *      Product:
         **/
        Product product = productService.getProductById(id);
        EntityModel<Product> productEntity = EntityModel.of(product);
        WebMvcLinkBuilder productLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProducts());
        productEntity.add(productLink.withRel("Get all products"));
        return productEntity;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @PatchMapping("/products/{id}")
    public EntityModel<Product> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable long id){
        Product product = productService.updateProduct(productRequest, id);
        EntityModel<Product> productEntity = EntityModel.of(product);
        WebMvcLinkBuilder productLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProducts());
        productEntity.add(productLink.withRel("Get a products"));
        return productEntity;
    }

    @GetMapping("/products/{id}/reviews")
    public List<Review> getReviewsOfProduct(@PathVariable long id){
        /**
         * Input:
         *      pathvariable id: long
         * Returns:
         *      List of Review
         **/
        return productService.getReviewsOfProduct(id);
    }

    @GetMapping("products/{id}/orders")
    public List<Order> getOrdersOfProduct(@PathVariable long id){
        return productService.getOrdersOfProduct(id);
    }
}
