package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.Customer;
import com.example.onlineshopping.domain.Item;
import com.example.onlineshopping.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto implements Serializable {
    private  long id;
    private  List<ItemDto> itemDtos;
}
