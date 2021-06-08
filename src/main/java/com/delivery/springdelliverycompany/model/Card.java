package com.delivery.springdelliverycompany.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="cards")
public class Card {

    @Id
    private int id;

    @Column(name = "card_number")
    private String cardnumber;

    @Column(name = "name")
    private String name;

    @Column(name = "cvv")
    private int cvv;

    @Column(name = "user_id")
    private int userid;

    @Column(name = "expiration_month_year")
    private String expirationmonthyear;
}
