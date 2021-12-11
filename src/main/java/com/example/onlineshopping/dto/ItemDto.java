package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.Order;
import com.example.onlineshopping.domain.Product;
import com.example.onlineshopping.domain.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto implements Serializable {
    private  long id;
    private  double price;
    private ProductDto product;
}
