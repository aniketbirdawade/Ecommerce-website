package com.ecommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.Entity.hsndata;
import com.ecommerce.Entity.Order;

@Repository
public interface HsnRepository extends JpaRepository<hsndata, String> {


}