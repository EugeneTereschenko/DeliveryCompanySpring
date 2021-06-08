package com.delivery.springdelliverycompany.rest;

import com.delivery.springdelliverycompany.count.RateDeliveryCount;
import com.delivery.springdelliverycompany.model.Cart;
import com.delivery.springdelliverycompany.model.Citiescoordinate;
import com.delivery.springdelliverycompany.model.Rate;
import com.delivery.springdelliverycompany.repository.CartRepository;
import com.delivery.springdelliverycompany.repository.CitiescoordinatesRepository;
import com.delivery.springdelliverycompany.repository.RateRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/cart")
public class CartRestController {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(CartRestController.class);

    @Autowired
    private RateRepository rateRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartRepository userRepo;

    @Autowired
    private CitiescoordinatesRepository citiescoordinateRepo;


    @GetMapping("/")
    public List<Cart> getCounts()
    {
        return cartRepo.findAll();
    }



    @PostMapping(path="/countdelivery", consumes = {"application/json"})
    public Map<String, String> getCount(@RequestBody Cart cart){

       RateDeliveryCount rateDeliveryCount = new RateDeliveryCount();
       Citiescoordinate cityFrom = citiescoordinateRepo.findById(cart.getCityfromid());
       Citiescoordinate cityTo = citiescoordinateRepo.findById(cart.getCitytoid());

       double distanceBetween = rateDeliveryCount.distance(cityFrom.getLatitude(), cityFrom.getLongitude(), cityTo.getLatitude(), cityTo.getLongitude());

       int distanceBetweenCity = (int) distanceBetween;

       Rate rate = rateRepo.findByDistancefromAndDistancetoAndWeightAndLimit(distanceBetweenCity, distanceBetweenCity, cart.getWeight());
       cart.setDistance(distanceBetweenCity);

       int costrouteBetweenCity = (int) (distanceBetweenCity*rate.getCost());

       cart.setTotalprice(costrouteBetweenCity);
       Map<String, String> sum = new HashMap<>();
       sum.put("price", String.valueOf(costrouteBetweenCity));
       return sum;
    }

    @PostMapping(path="/putdelivery", consumes = {"application/json"})
    public Map<String, String> getPut(@RequestBody Cart cart){


        logger.info(cart);

        RateDeliveryCount rateDeliveryCount = new RateDeliveryCount();
        Citiescoordinate cityFrom = citiescoordinateRepo.findById(cart.getCityfromid());
        Citiescoordinate cityTo = citiescoordinateRepo.findById(cart.getCitytoid());

        double distanceBetween = rateDeliveryCount.distance(cityFrom.getLatitude(), cityFrom.getLongitude(), cityTo.getLatitude(), cityTo.getLongitude());

        int distanceBetweenCity = (int) distanceBetween;

        Rate rate = rateRepo.findByDistancefromAndDistancetoAndWeightAndLimit(distanceBetweenCity, distanceBetweenCity, cart.getWeight());
        cart.setDistance(distanceBetweenCity);

        int costrouteBetweenCity = (int) (distanceBetweenCity*rate.getCost());


        cart.setCitiesname(cityFrom.getName() + " - " + cityTo.getName());
        cart.setTotalprice(costrouteBetweenCity);
        cart.setShippingprice(costrouteBetweenCity + 100);
        cart.setCoupon(0);
        cart.setCheckoutstep("confirm");

        Cart retObject = cartRepo.saveAndFlush(cart);


        logger.info(retObject.getId());

        Map<String, String> sum = new HashMap<>();
        sum.put("cartid", String.valueOf(retObject.getId()));
        sum.put("distancebetween", String.valueOf(cart.getDistance()));
        sum.put("time", "00:00");
        sum.put("shippingprice", String.valueOf(cart.getShippingprice()));
        sum.put("citiesname", cart.getCitiesname());
        return sum;
    }

    @DeleteMapping(path="/removedelivery/{aid}",consumes= {"application/json"})
    public String deleteCart(@PathVariable int aid)
    {
        Cart a = cartRepo.getById(aid);

        cartRepo.delete(a);

        return "true";
    }


    @PutMapping(path="/updatenumdelivery",consumes= {"application/json"})
    public Cart saveOrUpdateCart(@RequestBody Cart cart)
    {

        Cart a = cartRepo.getById(cart.getId());
        a.setCount(cart.getCount());
        cartRepo.save(a);
        return cart;
    }



}
