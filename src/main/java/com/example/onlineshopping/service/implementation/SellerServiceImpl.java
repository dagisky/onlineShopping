package com.example.onlineshopping.service.implementation;

import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.Seller;
import com.example.onlineshopping.repository.OrderRepository;
import com.example.onlineshopping.repository.ProductRepository;
import com.example.onlineshopping.repository.SellerRepository;
import com.example.onlineshopping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Seller> getAllSellers() {
        return StreamSupport.stream(sellerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Seller getSellerById(long id) {
        return sellerRepository.findById(id).orElseThrow();
    }

    @Override
    public Seller addSeller(Seller seller) {
        seller.setApproved(false);
        return sellerRepository.save(seller);
    }

    @Override
    public void deleteSeller(long id) {
        sellerRepository.deleteById(id);
    }

    @Override
    public Set<Product> findProducts(long id) {
        return productRepository.findAllBySeller(getSellerById(id));
    }

    @Override
    public Product addProduct(Product product, long id) {
        Seller seller = getSellerById(id);
        product.setSeller(seller);
        return productRepository.save(product);
    }

    @Override
    public Seller approveSeller(long id) {
       Seller seller = getSellerById(id);
       seller.setApproved(true);
       return sellerRepository.save(seller);
    }

}
