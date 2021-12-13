package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Address;
import com.example.onlineshopping.dto.AddressRequest;


public interface AddressService {

    void deleteAddress(long id);

    Address updateAddress(AddressRequest addressRequest, long id);
}
