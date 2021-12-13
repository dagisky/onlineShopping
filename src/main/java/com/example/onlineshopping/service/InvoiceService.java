package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Invoice;
import com.example.onlineshopping.domain.Order;

import java.util.List;


public interface InvoiceService {

    Invoice generateInvoice(List<Order> orders);
}
