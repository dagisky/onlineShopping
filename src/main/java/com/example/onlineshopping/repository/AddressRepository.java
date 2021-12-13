package com.example.onlineshopping.repository;


import com.example.onlineshopping.domain.Address;
import com.example.onlineshopping.domain.Buyer;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AddressRepository extends CrudRepository<Address,Long> {

    List<Address> findAllByBuyer(Buyer buyer);
}
