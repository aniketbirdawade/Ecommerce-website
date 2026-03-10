package com.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByUserId(int userId);
}
