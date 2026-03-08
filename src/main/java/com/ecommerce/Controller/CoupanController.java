package com.ecommerce.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Coupan;
import com.ecommerce.Service.CoupanService;

@RestController
@RequestMapping("/coupan")
public class CoupanController {

    private final CoupanService cs;

    public CoupanController(CoupanService cs) {
        this.cs = cs;
    }

    @PostMapping("add-coupan")
    public Coupan createCoupan(@RequestBody Coupan coupan) {
        return cs.createCoupan(coupan);
    }

    @GetMapping("get-coupan")
    public List<Coupan> getAllCoupan() {
        return cs.getAllCoupan();
    }
}
