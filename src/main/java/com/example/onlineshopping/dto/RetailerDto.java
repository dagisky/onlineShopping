package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailerDto implements Serializable {
    private  long id;
    private  String firstName;
    private  String lastName;
}
