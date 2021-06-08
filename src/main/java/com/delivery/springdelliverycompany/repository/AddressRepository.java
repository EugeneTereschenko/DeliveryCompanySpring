package com.delivery.springdelliverycompany.repository;

import com.delivery.springdelliverycompany.model.Address;
import com.delivery.springdelliverycompany.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Override
    List<Address> findAll();
}
