package com.delivery.springdelliverycompany.rest;

import com.delivery.springdelliverycompany.model.Cart;
import com.delivery.springdelliverycompany.model.User;
import com.delivery.springdelliverycompany.repository.CartRepository;
import com.delivery.springdelliverycompany.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class CartViewController {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(CartViewController.class);

    @Autowired
    private CartRepository cartRepo;


    @Autowired
    private UserRepository userRepo;

    @GetMapping("/showcarts")
    public String getCarts(HttpServletRequest req, Model model)
    {

        List<Cart> carts = cartRepo.findAll();


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> userFromDb = userRepo.findByEmail(currentPrincipalName);

        int userid = 0;

        if (userFromDb.isPresent()) {

            userid = Math.toIntExact(userFromDb.get().getId());
        }

        String tempRole = String.valueOf(userFromDb.get().getRole());

        if ((tempRole == "DEVELOPERS") || (tempRole == "ADMIN")){
            logger.info("show button");
            logger.info(userFromDb.get().getRole());
            model.addAttribute("userrole", String.valueOf(userFromDb.get().getRole()));
        }



        logger.info("Show carts");

        model.addAttribute("listCarts", carts);
        model.addAttribute("username", currentPrincipalName);

        return "/view/carts";
    }

}