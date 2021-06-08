package com.delivery.springdelliverycompany.repository;

import com.delivery.springdelliverycompany.model.Address;
import com.delivery.springdelliverycompany.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    @Override
    List<Card> findAll();
}
