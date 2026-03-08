package com.ecommerce.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Address;
import com.ecommerce.Service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService as;

    public AddressController(AddressService as) {
        this.as = as;
    }

    @PostMapping("add-address")
    public Address createAddress(@RequestBody Address address) {
    	System.out.println("Address Added Successfully...");
        return as.createAddress(address);
    }

    @GetMapping("get-address")
    public List<Address> getAllAddress() {
        return as.getAllAddress();
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Integer id) {
        return as.getAddress(id);
    }
}
