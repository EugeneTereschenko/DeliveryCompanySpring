package com.delivery.springdelliverycompany.controller;

import com.delivery.springdelliverycompany.model.*;

import com.delivery.springdelliverycompany.repository.CartRepository;
import com.delivery.springdelliverycompany.repository.CitiescoordinatesRepository;
import com.delivery.springdelliverycompany.repository.RateRepository;
import com.delivery.springdelliverycompany.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AuthController {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RateRepository rateRepo;

    @Autowired
    private CitiescoordinatesRepository cityscoordinateRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/login")
    public String getLoginPage(Model model){


        model.addAttribute("listRates", rateRepo.findAll());
        model.addAttribute("listCites", cityscoordinateRepo.findAll());

        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(){ return "registration"; }

    @GetMapping("/success")
    public String getSuccessPage(HttpServletResponse response, Model model){


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


        logger.info(userFromDb.get().getRole());
        logger.info("user log in " + currentPrincipalName);

        Cookie cookie = new Cookie("userid", String.valueOf(userid));
        response.addCookie(cookie);

        model.addAttribute("listRates", rateRepo.findAll());
        model.addAttribute("listCites", cityscoordinateRepo.findAll());
        model.addAttribute("listCarts", cartRepo.findByUserid(userid));
        model.addAttribute("username", currentPrincipalName);

        return "success";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {

        Optional<User> userFromDb = userRepo.findByEmail(user.getEmail());
        if (userFromDb.isPresent()){
            model.addAttribute("messagegr", "User exists!");
            return "registration";
        }
        String passwordUser = user.getPassword();
        user.setPassword(passwordEncoder.encode(passwordUser));
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        userRepo.save(user);

        return "redirect:/login";
    }

    @RequestMapping("/test")
    public String hello(HttpServletResponse response, HttpServletRequest request) {

        Locale currentLocale = request.getLocale();
        String countryCode = currentLocale.getCountry();
        String countryName = currentLocale.getDisplayCountry();

        String langCode = currentLocale.getLanguage();
        String langName = currentLocale.getDisplayLanguage();

        System.out.println(countryCode);
        System.out.println(countryName);
        System.out.println(langCode);
        System.out.println(langName);

        return "test";
    }

}
