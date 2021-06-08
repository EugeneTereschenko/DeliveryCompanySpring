package com.delivery.springdelliverycompany.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="addresses")
public class Address {

    @Id
    private int id;

    @Column(name = "address_type")
    private String sameaddress;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_id")
    private int userid;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private String zip;


}
