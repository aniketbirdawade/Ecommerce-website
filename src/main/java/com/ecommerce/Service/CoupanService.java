package com.ecommerce.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ecommerce.Entity.Coupan;
import com.ecommerce.Repository.CoupanRepository;

@Service
public class CoupanService {

    private final CoupanRepository cr;

    public CoupanService(CoupanRepository cr) {
        this.cr = cr;
    }

    public Coupan createCoupan(Coupan coupan) {
        return cr.save(coupan);
    }

    public List<Coupan> getAllCoupan() {
        return cr.findAll();
    }
}
