package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.Retailer;
import com.example.onlineshopping.dto.ProductDto;
import com.example.onlineshopping.dto.RetailerDto;
import com.example.onlineshopping.globalExecption.UserNotFoundException;
import com.example.onlineshopping.repository.ProductRepository;
import com.example.onlineshopping.repository.RetailerRepository;
import com.example.onlineshopping.util.ListMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service    @RequiredArgsConstructor
public class RetailerService {
    private final RetailerRepository retailerRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ListMapper<Product, ProductDto> productToProductDtoListMapper;

    public RetailerDto findById(long id){
        /**
         * Input:
         *      id: long - retailer id
         * Returns:
         *      RetailerDto
         **/
        Retailer retailer = retailerRepository.findById(id).orElse(null);
        if(retailer == null)
            throw new UserNotFoundException("Retailer id: "+id+ " not found");
        return modelMapper.map(retailerRepository.findById(id).orElse(null), RetailerDto.class);
    }

    public List<ProductDto> findRetailerProducts(long id){
        /**
         * Input:
         *      id: long - retailer id
         * Returns:
         *      ProductDto
         * **/
        Retailer retailer = retailerRepository.findById(id).orElse(null);
        if (retailer == null)
            throw new UserNotFoundException("Retailer id: "+id+ " not found");
        return (List<ProductDto>) productToProductDtoListMapper.mapList(retailer.getProducts(), new ProductDto());
    }

    public ProductDto addNewProduct(long id, ProductDto productDto){
        /**
         * Input:
         *      id: long - retailer id
         *      productDto: ProductDto
         * Returns:
         *      ProductDto
         **/
        Retailer retailer = retailerRepository.findById(id).orElse(null);
        if (retailer == null)
            throw new UserNotFoundException("Retailer id: "+id+ " not found");
        Product product = modelMapper.map(productDto, Product.class);
        product.setRetailer(retailer);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDto.class);
    }





}
