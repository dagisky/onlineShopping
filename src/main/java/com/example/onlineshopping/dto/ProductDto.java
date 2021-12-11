package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.Retailer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    private  long id;
    private  String description;
    private  String make;
    private  int year;
//    private Retailer retailer;
}
