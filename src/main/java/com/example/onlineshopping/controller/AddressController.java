package com.example.onlineshopping.controller;


import com.example.onlineshopping.domain.Address;
import com.example.onlineshopping.dto.AddressRequest;
import com.example.onlineshopping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @PatchMapping("/addresses/{id}") // patch an address
    public Address updateAddress(@RequestBody AddressRequest addressRequest, @PathVariable long id){
        return addressService.updateAddress(addressRequest, id);
    }
    @DeleteMapping("/addresses/{id}") // delete an address
    public void deleteAddress(@PathVariable long id){
        addressService.deleteAddress(id);
    }
}
