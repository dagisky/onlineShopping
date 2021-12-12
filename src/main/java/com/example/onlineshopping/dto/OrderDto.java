package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.Customer;
import com.example.onlineshopping.domain.OrderStatus;
import com.example.onlineshopping.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    private  long id;
    private  LocalDateTime orderDateTime;
    private  List<Product> products;
    private Customer customer;
    private  OrderStatus orderStatus;
    private  BillDto bill;
}
