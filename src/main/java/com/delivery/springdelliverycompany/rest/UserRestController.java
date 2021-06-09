package com.delivery.springdelliverycompany.rest;

import com.delivery.springdelliverycompany.model.Developer;
import com.delivery.springdelliverycompany.model.User;
import com.delivery.springdelliverycompany.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(UserRestController.class);


        @Autowired
        private UserRepository userRepo;

        @Autowired
        private PasswordEncoder passwordEncoder;


        @GetMapping
        public List<User> getAll() {
            return userRepo.findAll();
        }

        @GetMapping("/{id}")
        @PreAuthorize("hasAuthority('developers:read')")
        public User getById(@PathVariable Long id) {
            return userRepo.getById(id);
        }

        @PostMapping
        @PreAuthorize("hasAuthority('developers:write')")
        public User create(@RequestBody User user){

            logger.info(user);
            String passwordUser = user.getPassword();
            user.setPassword(passwordEncoder.encode(passwordUser));

            userRepo.save(user);
            return user;
        }

        @DeleteMapping("/{id}")
        @PreAuthorize("hasAuthority('developers:write')")
        public String deleteById(@PathVariable Long id){
            User user = userRepo.getOne(id);
            userRepo.delete(user);
            return "true";
        }

}
