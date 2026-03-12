package com.ecommerce.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.CSV.HsnImportService;
import com.ecommerce.Entity.HsnMaster;

@RestController
@RequestMapping("/api/hsn")
public class HsnController {

    private final HsnImportService hsnImportService;

    // Constructor Injection
    public HsnController(HsnImportService hsnImportService) {
        this.hsnImportService = hsnImportService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importHsnData() {
        try {
            hsnImportService.importHsnData();
            return ResponseEntity.ok("HSN data imported successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error importing HSN data: " + e.getMessage());
        }
    }
    
    @GetMapping("get-hsn")
    public List<HsnMaster> getAllHsn() {
        return hsnImportService.getAllHsn();
    }
}