package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.User;
import com.ecommerce.Repository.UserRepository;
 
@RestController
@RequestMapping("/api/users")
public class UserController {
 
    private final UserRepository userRepository;
 
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 
    // CREATE
    @PostMapping
    public User addUser(@RequestBody User user) {
        User saved = userRepository.save(user);
        return userRepository.findById(saved.getId()).orElse(saved);
    }
 
    // READ ALL
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
 
    // READ ONE
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id).orElse(null);
    }
 
    // SEARCH by name
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
 
    // UPDATE 
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setMobile(updatedUser.getMobile());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(updatedUser.getPassword());
        }
        return userRepository.save(user);
    }
 
    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}