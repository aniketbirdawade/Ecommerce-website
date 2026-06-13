package com.ecommerce.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.HsnData;
import com.ecommerce.Repository.HsnDataRepository;

@Service
public class HsnDataService {

    private final HsnDataRepository repo;

    public HsnDataService(HsnDataRepository repo) {
        this.repo = repo;
    }

    public List<HsnData> getAllHsn() {
        return repo.findAll();
    }

    public HsnData saveHsn(HsnData hsn) {
        return repo.save(hsn);
    }
}