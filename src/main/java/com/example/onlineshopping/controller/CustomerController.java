package com.example.onlineshopping.controller;

import com.example.onlineshopping.dto.CustomerDto;
import com.example.onlineshopping.dto.ShoppingCartDto;
import com.example.onlineshopping.dto.UserCustomerDto;
import com.example.onlineshopping.dto.UserDto;
import com.example.onlineshopping.service.CustomerService;
import com.example.onlineshopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers") @RequiredArgsConstructor
public class CustomerController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CustomerService customerService;

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<CustomerDto>> findCustomerById(@PathVariable("id") long id){
        CustomerDto customerDto = customerService.findById(id);
        EntityModel<CustomerDto> customerDtoEntityModel = EntityModel.of(customerDto);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getShoppingCart(customerDto.getId()));
        customerDtoEntityModel.add(linkBuilder.withRel("get cart"));
        return ResponseEntity.ok().body(customerDtoEntityModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<UserDto>> createCustomer(@RequestBody UserCustomerDto userDto){
        UserCustomerDto userD = userService.createCustomer(userDto);
        EntityModel<UserDto> userDtoEntityModel = EntityModel.of(modelMapper.map(userD, UserDto.class));
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).findUserById(userD.getId()));
        userDtoEntityModel.add(linkBuilder.withRel("Find User"));
        return ResponseEntity.ok().body(userDtoEntityModel);
    }

    @GetMapping("{id}/cart")
    public ResponseEntity<ShoppingCartDto>  getShoppingCart(@PathVariable("id") long id){
        ShoppingCartDto shoppingCartDto = customerService.findUserCart(id);
        return ResponseEntity.ok().body(shoppingCartDto);
    }




}
