package com.delivery.springdelliverycompany.rest;

import com.delivery.springdelliverycompany.model.Developer;
import com.delivery.springdelliverycompany.model.User;
import com.delivery.springdelliverycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
public class UserRestController {


        @Autowired
        private UserRepository userRepo;


        @GetMapping
        public List<User> getAll() {
            return userRepo.findAll();
        }

        @GetMapping("/{id}")
        @PreAuthorize("hasAuthority('users:read')")
        public User getById(@PathVariable Long id) {
            return userRepo.getById(id);
        }

        @PostMapping
        @PreAuthorize("hasAuthority('users:write')")
        public User create(@RequestBody User user){
            userRepo.save(user);
            return user;
        }

        @DeleteMapping("/{id}")
        @PreAuthorize("hasAuthority('users:write')")
        public void deleteById(@PathVariable Long id){
            User user = userRepo.getOne(id);
            userRepo.delete(user);
        }

}
