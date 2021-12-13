package com.example.onlineshopping.service.implementation;

import com.example.onlineshopping.domain.Address;
import com.example.onlineshopping.dto.AddressRequest;
import com.example.onlineshopping.repository.AddressRepository;
import com.example.onlineshopping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address updateAddress(AddressRequest addressRequest, long id) {
       Address address = addressRepository.findById(id).get();
       address.setCity(addressRequest.getCity());
       address.setState(addressRequest.getState());
       address.setZipCode(addressRequest.getZipCode());
       address.setAddressType(addressRequest.getAddressType());
       return addressRepository.save(address);
    }
}
