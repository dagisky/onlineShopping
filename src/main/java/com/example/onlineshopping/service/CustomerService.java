package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Customer;
import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.dto.CustomerDto;
import com.example.onlineshopping.dto.ProductDto;
import com.example.onlineshopping.dto.ShoppingCartDto;
import com.example.onlineshopping.globalExecption.UserNotFoundException;
import com.example.onlineshopping.repository.CustomerRepository;
import com.example.onlineshopping.util.ListMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final CartService cartService;
    ListMapper<Product, ProductDto> itemToItemDtoListMapper;

    public CustomerDto findById(long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null)
            throw new UserNotFoundException("Customer id:"+id+" not found");
        return modelMapper.map(customer, CustomerDto.class);
    }



    public ShoppingCartDto getUserCart(long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null)
            throw new UserNotFoundException("Customer id:"+id+" not found");
        ShoppingCartDto shoppingCartDto = modelMapper.map(customer.getCart(), ShoppingCartDto.class);
        if(shoppingCartDto != null) {
            List<ProductDto> productDtos = (List<ProductDto>) itemToItemDtoListMapper.mapList(customer.getCart().getProducts(), new ProductDto());
            shoppingCartDto.setProductDtos(productDtos);
        }
        return shoppingCartDto;
    }


    public ShoppingCartDto addToCart(long c_id, long p_id){
        CustomerDto customerDto = findById(c_id);
        ProductDto productDto = productService.findById(p_id);
        return cartService.addProductToCart(customerDto.getId(), productDto);
    }




}
