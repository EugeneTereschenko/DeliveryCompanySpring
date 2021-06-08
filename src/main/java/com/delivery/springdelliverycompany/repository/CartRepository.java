package com.delivery.springdelliverycompany.repository;


import com.delivery.springdelliverycompany.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Long>  {

    @Override
    List<Cart> findAll();

    @Query(value  = "SELECT * from carts where user_id = ?",
            nativeQuery = true
    )
    List<Cart> findByUserid(int user_id);

    Cart getById(int aid);

    @Override
    <S extends Cart> S saveAndFlush(S s);

    @Modifying()
    @Query(value = "UPDATE carts SET checkout_step = ?1 where user_id = ?2", nativeQuery = true)
    @Transactional
    void setCheckoutstepAndUserid(@Param("checkout_step") String checkout_step, int user_id);


    @Query(value = "SELECT COUNT(*) FROM carts where checkout_step = 'confirm' and user_id = ?", nativeQuery = true)
    int getByUserid(int user_id);


    @Query(value = "SELECT SUM(shipping_price) FROM carts where checkout_step = 'confirm' and user_id = ?", nativeQuery = true)
    int getTotalPriceByUserid(int user_id);
}
