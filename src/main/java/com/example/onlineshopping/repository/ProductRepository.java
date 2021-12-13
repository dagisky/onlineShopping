package com.example.onlineshopping.repository;


import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Set<Product> findAllBySeller(Seller seller);
 }
