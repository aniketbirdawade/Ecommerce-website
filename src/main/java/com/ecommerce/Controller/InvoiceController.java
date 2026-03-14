package com.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.Service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private final InvoiceService is;
    
    InvoiceController(InvoiceService is){
    	this.is = is;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<byte[]> downloadInvoice(@PathVariable int orderId) {

        byte[] pdf = is.generateInvoice(orderId);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; "
        		+ "filename=invoice_" + orderId + ".pdf").contentType(MediaType.APPLICATION_PDF).body(pdf);
    }
}