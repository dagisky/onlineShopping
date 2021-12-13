package com.example.onlineshopping.service.implementation;

import com.example.onlineshopping.domain.Invoice;
import com.example.onlineshopping.domain.Order;
import com.example.onlineshopping.service.InvoiceService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public Invoice generateInvoice(List<Order> orders) {
        return null;
    }
}
