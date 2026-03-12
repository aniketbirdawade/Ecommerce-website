package com.ecommerce.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.HsnMaster;
import com.ecommerce.Repository.HsnMasterRepository;

@Service
public class HsnMasterService {

    private final HsnMasterRepository repo;

    public HsnMasterService(HsnMasterRepository repo) {
        this.repo = repo;
    }

    public List<HsnMaster> getAllHsn() {
        return repo.findAll();
    }

    public HsnMaster saveHsn(HsnMaster hsn) {
        return repo.save(hsn);
    }
}