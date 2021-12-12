package com.example.onlineshopping.controller;


import com.example.onlineshopping.dto.UserDto;
import com.example.onlineshopping.dto.UserRetailerDto;
import com.example.onlineshopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users") @RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<UserDto>> findUserById(@PathVariable("id") long id){
        UserDto userDto = userService.findById(id);
        EntityModel<UserDto> userDtoEntityModel = EntityModel.of(userDto);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteUser(userDto.getId()));
        userDtoEntityModel.add(linkBuilder.withRel("self"));
        return ResponseEntity.ok().body(userDtoEntityModel);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().body(null);
    }



}
