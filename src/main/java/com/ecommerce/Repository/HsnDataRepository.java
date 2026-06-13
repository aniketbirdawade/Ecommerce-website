package com.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.Entity.HsnData;

public interface HsnDataRepository extends JpaRepository<HsnData, String> {

}