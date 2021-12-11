package com.example.onlineshopping.controller;

import com.example.onlineshopping.dto.ProductDto;
import com.example.onlineshopping.dto.RetailerDto;
import com.example.onlineshopping.service.RetailerService;
import com.example.onlineshopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retailers")  @RequiredArgsConstructor
public class RetailerController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RetailerService retailerService;

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<RetailerDto>> findRetailerById(@PathVariable("id") long id){
        RetailerDto retailerDto = retailerService.findById(id);
        EntityModel<RetailerDto> retailerDtoEntityModel = EntityModel.of(retailerDto);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findRetailerProducts(retailerDto.getId()));
        retailerDtoEntityModel.add(linkBuilder.withRel("get retailer products"));
        return ResponseEntity.ok().body(retailerDtoEntityModel);
    }

    @GetMapping("{id}/products")
    public ResponseEntity<EntityModel<List<ProductDto>>> findRetailerProducts(@PathVariable("id") long id){
        List<ProductDto> productDtos = retailerService.findRetailerProducts(id);
        EntityModel<List<ProductDto>> productDtosEntityModel = EntityModel.of(productDtos);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findRetailerById(id));
        productDtosEntityModel.add(linkBuilder.withRel("get retailer"));
        return ResponseEntity.ok().body(productDtosEntityModel);
    }

    @PostMapping("{id}/products")
    public ResponseEntity<EntityModel<ProductDto>> addProduct(@PathVariable("id") long id, @RequestBody ProductDto productDto){
        ProductDto productDtoR = retailerService.addNewProduct(id, productDto);
        EntityModel<ProductDto> productDtoEntityModel = EntityModel.of(productDtoR);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findRetailerProducts(id));
        productDtoEntityModel.add(linkBuilder.withRel("get retailer products"));
        return ResponseEntity.ok().body(productDtoEntityModel);
    }
}
