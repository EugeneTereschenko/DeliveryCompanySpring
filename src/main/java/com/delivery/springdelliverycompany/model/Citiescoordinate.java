package com.delivery.springdelliverycompany.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name= "citiescoordinate")
public class Citiescoordinate {

    @Id
    private int id;

    @Column(name = "name")
    String name;

    @Column(name = "name_local")
    String namelocal;
    @Column(name = "latitude")
    double latitude;
    @Column(name = "longitude")
    double longitude;

}
