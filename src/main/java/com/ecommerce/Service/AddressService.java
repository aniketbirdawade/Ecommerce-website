package com.ecommerce.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ecommerce.Entity.Address;
import com.ecommerce.Entity.User;
import com.ecommerce.Repository.AddressRepository;
import com.ecommerce.Repository.UserRepository;

@Service
public class AddressService {

    private final AddressRepository ar;
    private final UserRepository ur;

    public AddressService(AddressRepository ar, UserRepository ur) {
        this.ar = ar;
        this.ur = ur;
    }

    public Address createAddress(Address address) {
        User user = ur.findById(address.getUser().getId()).orElseThrow();
    	address.setUser(user);
    	
    	return ar.save(address);
    }

    public List<Address> getAllAddress() {
        return ar.findAll();
    }

    public Address getAddress(Integer id) {
        return ar.findById(id).orElse(null);
    }
    
    public void deleteAddress(int id) {
        ar.deleteById(id);
    }
}
