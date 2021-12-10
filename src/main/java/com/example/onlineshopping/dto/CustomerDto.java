package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.CustomerStatus;
import com.example.onlineshopping.domain.ShoppingCart;
import com.example.onlineshopping.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {
    private  long id;
    private  String firstName;
    private  String lastName;
    private  CustomerStatus status;
    private ShoppingCart shoppingCart;
    private User user;
}
