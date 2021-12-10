package com.example.onlineshopping.controller;

import com.example.onlineshopping.dto.CustomerDto;
import com.example.onlineshopping.dto.UserCustomerDto;
import com.example.onlineshopping.dto.UserDto;
import com.example.onlineshopping.dto.UserRetailerDto;
import com.example.onlineshopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users") @RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<UserDto>> findUserById(@PathVariable("id") long id){
        UserDto userDto = userService.findById(id);
        EntityModel<UserDto> userDtoEntityModel = EntityModel.of(userDto);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findUserById(userDto.getId()));
        userDtoEntityModel.add(linkBuilder.withRel("self"));
        return ResponseEntity.ok().body(userDtoEntityModel);
    }

    @PostMapping("/customers")
    public ResponseEntity<EntityModel<UserCustomerDto>> createCustomer(@RequestBody UserCustomerDto userDto){
        UserCustomerDto userD = userService.createCustomer(userDto);
        EntityModel<UserCustomerDto> userDtoEntityModel = EntityModel.of(userD);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findUserById(userDto.getId()));
        userDtoEntityModel.add(linkBuilder.withRel("Find User"));
        return ResponseEntity.ok().body(userDtoEntityModel);
    }

    @PostMapping("/retailers")
    public ResponseEntity<EntityModel<UserRetailerDto>> createRetailer(@RequestBody UserRetailerDto userDto){
        UserRetailerDto userD = userService.createRetailer(userDto);
        EntityModel<UserRetailerDto> userDtoEntityModel = EntityModel.of(userD);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findUserById(userDto.getId()));
        userDtoEntityModel.add(linkBuilder.withRel("Find User"));
        return ResponseEntity.ok().body(userDtoEntityModel);
    }




}
