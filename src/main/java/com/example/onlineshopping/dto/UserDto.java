package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.Customer;
import com.example.onlineshopping.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private  long id;
    private  String username;
    private  String password;
    private  String phone;
    private  String email;
    private Role role;
    private Customer customer;
}
