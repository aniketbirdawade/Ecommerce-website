package com.ecommerce.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Entity.hsndata;
import com.ecommerce.Service.HsnImportService;

@RestController
@RequestMapping("/api/hsn")
public class HsnController {

    private final com.ecommerce.Service.HsnImportService hsnImportService;

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
    public List<hsndata> getAllHsn() {
        return hsnImportService.getAllHsn();
    }
}