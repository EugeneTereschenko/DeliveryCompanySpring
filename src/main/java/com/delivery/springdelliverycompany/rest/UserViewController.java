package com.delivery.springdelliverycompany.rest;

import com.delivery.springdelliverycompany.model.User;
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
public class UserViewController {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(UserViewController.class);

    @Autowired
    private UserRepository userRepo;


    @GetMapping("/showusers")
    public String getUsers(HttpServletRequest req, Model model)
    {

        List<User> users = userRepo.findAll();

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


        logger.info("Show users");

        model.addAttribute("listUsers", users);
        model.addAttribute("username", currentPrincipalName);

        return "/view/users";
    }

}
