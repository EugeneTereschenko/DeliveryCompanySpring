package com.delivery.springdelliverycompany.rest;

import com.delivery.springdelliverycompany.repository.CartRepository;
import com.delivery.springdelliverycompany.repository.CitiescoordinatesRepository;
import com.delivery.springdelliverycompany.repository.RateRepository;
import com.delivery.springdelliverycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class PaymentController {

    @Autowired
    private RateRepository rateRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CitiescoordinatesRepository citiescoordinateRepo;

    @GetMapping("/showdelivery")
    public String getDelivery(HttpServletRequest req, Model model)
    {
        int userid = 0;
        for (Cookie c : req.getCookies()) {
            if (c.getName().equals("userid")) {
                userid = Integer.parseInt(c.getValue());
            }
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();


        int num = cartRepo.getByUserid(userid);
        int totalsum = cartRepo.getTotalPriceByUserid(userid);

        model.addAttribute("num", num);
        model.addAttribute("totalsum", totalsum);

        model.addAttribute("listCarts", cartRepo.findByUserid(userid));
        model.addAttribute("username", currentPrincipalName);

        return "payment";
    }

}
