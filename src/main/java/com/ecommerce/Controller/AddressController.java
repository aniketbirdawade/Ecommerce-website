package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Address;
import com.ecommerce.Repository.AddressRepository;
 
@RestController
@RequestMapping("/api/addresses")
public class AddressController {
 
    private final AddressRepository addressRepository;
 
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
 
    @PostMapping
    public Address addAddress(@RequestBody Address address) {
        Address saved = addressRepository.save(address);
        return addressRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable int id) {
        return addressRepository.findById(id).orElse(null);
    }
 
    // GET addresses by user
    @GetMapping("/user/{userId}")
    public List<Address> getAddressesByUser(@PathVariable int userId) {
        return addressRepository.findByUserId(userId);
    }
 
    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable int id, @RequestBody Address updated) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) return null;
        address.setName(updated.getName());
        address.setMobile(updated.getMobile());
        address.setStreet(updated.getStreet());
        address.setCity(updated.getCity());
        address.setState(updated.getState());
        address.setPincode(updated.getPincode());
        address.setLandmark(updated.getLandmark());
        return addressRepository.save(address);
    }
 
    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable int id) {
        addressRepository.deleteById(id);
        return "Address deleted successfully";
    }
}