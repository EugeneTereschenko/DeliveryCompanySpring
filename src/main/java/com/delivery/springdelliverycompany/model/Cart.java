package com.delivery.springdelliverycompany.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "city_from_id")
    private int cityfromid;

    @Column(name = "city_to_id")
    private int citytoid;

    @Column(name = "type_deliver_id")
    private int typedeliverid;

    @Column(name = "count")
    private int count;

    @Column(name = "weight")
    private int weight;

    @Column(name = "length")
    private int length;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "distance")
    private int distance;

    @Column(name = "user_id")
    private int userid;

    @Column(name = "cities_name")
    private String citiesname;

    @Column(name = "total_price")
    private int totalprice;

    @Column(name = "shipping_price")
    private int shippingprice;

    @Column(name = "coupon")
    private int coupon;

    @Column(name = "checkout_step")
    private String checkoutstep;


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cityfromid=" + cityfromid +
                ", citytoid=" + citytoid +
                ", typedeliverid=" + typedeliverid +
                ", weight=" + weight +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", distance=" + distance +
                ", userid=" + userid +
                ", citiesname='" + citiesname + '\'' +
                ", totalprice=" + totalprice +
                ", shippingprice=" + shippingprice +
                ", coupon=" + coupon +
                ", checkoutstep='" + checkoutstep + '\'' +
                '}';
    }
}
