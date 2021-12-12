package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.dto.ProductDto;
import com.example.onlineshopping.globalExecption.ResourceNotFoundException;
import com.example.onlineshopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductDto findById(long id){
        ProductDto productDto = modelMapper.map(productRepository.findById(id).orElse(null), ProductDto.class);
        if(productDto == null)
            throw new ResourceNotFoundException("Product id: "+id+" not found");
        return productDto;
    }

    public ProductDto save(ProductDto productDto){
        return modelMapper.map(productRepository.save(modelMapper.map(productDto, Product.class)), ProductDto.class);
    }

    public void deleteById(long id){
        if(!productRepository.existsById(id))
            throw new ResourceNotFoundException("Product id: "+id+" not found");
        productRepository.deleteById(id);
    }


}
