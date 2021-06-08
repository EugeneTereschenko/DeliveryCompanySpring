package com.delivery.springdelliverycompany.repository;


import com.delivery.springdelliverycompany.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RateRepository extends JpaRepository<Rate, Long> {

    @Override
    List<Rate> findAll();


    @Query(value  = "SELECT * from ratedeliver where distancefrom < ? and distanceto >= ? and weight >= ? limit 1",
        nativeQuery = true
    )
    Rate findByDistancefromAndDistancetoAndWeightAndLimit(int distanceFrom, int distanceTo, double weight);

}