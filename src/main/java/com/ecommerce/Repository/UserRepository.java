package com.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);

}
