package com.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Entity.User;
import com.ecommerce.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
 
    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @PostMapping("add-user")
    public User addUser(@RequestBody User user) {
        return us.addUser(user);
    }
    
    @GetMapping("get-all-user")
    public List<User> getAllUsers() {
        return us.getAllUsers();
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return us.getUser(id);
    }
}
