package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.Retailer;
import com.example.onlineshopping.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRetailerDto {
    private  long id;
    private  String username;
    private  String password;
    private  String phone;
    private  String email;
    private  Role role;
    private Retailer retailer;
}
