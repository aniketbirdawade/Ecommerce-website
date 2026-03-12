package com.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ecommerce.Entity.HsnMaster;
import com.ecommerce.Service.HsnMasterService;

@RestController
@RequestMapping("/hsn")
public class HsnMasterController {

    private final HsnMasterService service;

    public HsnMasterController(HsnMasterService service) {
        this.service = service;
    }

    @GetMapping("get-hsn")
    public List<HsnMaster> getAllHsn() {
        return service.getAllHsn();
    }

    @PostMapping("add-hsn")
    public HsnMaster addHsn(@RequestBody HsnMaster hsn) {
        return service.saveHsn(hsn);
    }
}