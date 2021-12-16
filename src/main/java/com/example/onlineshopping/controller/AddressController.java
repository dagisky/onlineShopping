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

    @PatchMapping("/addresses/{id}")
    public Address updateAddress(@RequestBody AddressRequest addressRequest, @PathVariable long id){
        /**
         * Input:
         *      Request Body: Address Request object
         *      Path param: address id
         *  Returns:
         *      Address Object**/
        return addressService.updateAddress(addressRequest, id);
    }
    @DeleteMapping("/addresses/{id}")
    public void removeAddress(@PathVariable long id){
        /**
         * Address is removed by id
         * Input:
         *     path variable: id: long
         * Returns Void
         *
         * **/
        addressService.deleteAddress(id);
    }
}
