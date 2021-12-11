package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Customer;
import com.example.onlineshopping.domain.Item;
import com.example.onlineshopping.dto.CustomerDto;
import com.example.onlineshopping.dto.ItemDto;
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
    ListMapper<Item, ItemDto> itemToItemDtoListMapper;

    public CustomerDto findById(long id){
        return modelMapper.map(customerRepository.findById(id).orElse(null), CustomerDto.class);
    }

    public ShoppingCartDto findUserCart(long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null)
            throw new UserNotFoundException("Customer id:"+id+" not found");
        ShoppingCartDto shoppingCartDto = modelMapper.map(customer.getCart(), ShoppingCartDto.class);
        if(shoppingCartDto != null) {
            List<ItemDto> itemDtos = (List<ItemDto>) itemToItemDtoListMapper.mapList(customer.getCart().getItems(), new ItemDto());
            shoppingCartDto.setItemDtos(itemDtos);
        }
        return shoppingCartDto;
    }


}
