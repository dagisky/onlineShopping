package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.Customer;
import com.example.onlineshopping.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCustomerDto {
    private  long id;
    private  String username;
    private  String password;
    private  String phone;
    private  String email;
    private Role role;
    private Customer customer;
}
