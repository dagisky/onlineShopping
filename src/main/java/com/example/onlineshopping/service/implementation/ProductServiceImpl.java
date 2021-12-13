package com.example.onlineshopping.service.implementation;

import com.example.onlineshopping.domain.Order;
import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.Review;
import com.example.onlineshopping.dto.ProductRequest;
import com.example.onlineshopping.repository.OrderRepository;
import com.example.onlineshopping.repository.ProductRepository;
import com.example.onlineshopping.repository.ReviewRepository;
import com.example.onlineshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Product> getAllProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow();
    }
    @Override
    public void deleteProduct(long id) {
        Product product = getProductById(id);
        Order order = orderRepository.findFirstByProduct(product);
//        TODO add check if product has reviews
        if(order == null){
            productRepository.deleteById(id);
        }
    }

    @Override
    public Product updateProduct(ProductRequest productRequest, long id) {
        Product product = getProductById(id);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());

        return productRepository.save(product);
    }

    @Override
    public List<Review> getReviewsOfProduct(long id) {
        Product product = getProductById(id);
        return reviewRepository.findAllByProduct(product);
    }

    @Override
    public List<Order> getOrdersOfProduct(long id) {
        Product product = getProductById(id);
        return orderRepository.findAllByProduct(product);
    }
}
