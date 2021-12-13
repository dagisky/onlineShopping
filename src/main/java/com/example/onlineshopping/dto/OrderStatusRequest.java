package com.example.onlineshopping.dto;

import com.example.onlineshopping.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusRequest {

    private OrderStatus status;
}
