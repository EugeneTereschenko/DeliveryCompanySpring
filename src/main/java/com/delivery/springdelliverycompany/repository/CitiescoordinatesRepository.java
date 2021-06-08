package com.delivery.springdelliverycompany.repository;

import com.delivery.springdelliverycompany.model.Citiescoordinate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitiescoordinatesRepository extends JpaRepository<Citiescoordinate, Long> {

    @Override
    List<Citiescoordinate> findAll();

    Citiescoordinate findById(int aid);

}