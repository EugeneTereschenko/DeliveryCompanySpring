package com.delivery.springdelliverycompany.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="ratedeliver")
public class Rate {

    @Id
    private int id;

    @Column(name = "weight")
    private double weight;
    @Column(name = "distancefrom")
    private int distancefrom;
    @Column(name = "distanceto")
    private int distanceto;
    @Column(name = "cost")
    private double cost;
    @Column(name = "created_at")
    private Date create_at;
    @Column(name = "update_at")
    private Date update_at;

}
