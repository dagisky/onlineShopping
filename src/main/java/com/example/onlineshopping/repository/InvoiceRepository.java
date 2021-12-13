package com.example.onlineshopping.repository;


import com.example.onlineshopping.domain.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
