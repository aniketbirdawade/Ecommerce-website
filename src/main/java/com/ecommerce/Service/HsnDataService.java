package com.ecommerce.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.hsndata;
import com.ecommerce.Repository.HsnDataRepository;

@Service
public class HsnDataService {

    private final HsnDataRepository repo;

    public HsnDataService(HsnDataRepository repo) {
        this.repo = repo;
    }

    public List<hsndata> getAllHsn() {
        return repo.findAll();
    }

    public hsndata saveHsn(hsndata hsn) {
        return repo.save(hsn);
    }
}