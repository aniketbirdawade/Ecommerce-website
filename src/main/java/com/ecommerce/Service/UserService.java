package com.ecommerce.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.Entity.User;
import com.ecommerce.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository ur;

    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public User addUser(User user) {
        return ur.save(user);
    }
    
    public List<User> getAllUsers() {
        return ur.findAll();
    }
    
    public User getUser(Integer id) {
        return ur.findById(id).orElse(null);
    }
}
