package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private String state;

    private String city;

    private int zipCode;

    private String country;

    private AddressType addressType;
}
