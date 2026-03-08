package com.ecommerce.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Delivary_Slot;
import com.ecommerce.Service.DeliverySlotService;

@RestController
@RequestMapping("/delivery-slot")
public class DeliverySlotController {

    private final DeliverySlotService dss;

    public DeliverySlotController(DeliverySlotService dss) {
        this.dss = dss;
    }

    @PostMapping("add-delivery-slot")
    public Delivary_Slot createSlot(@RequestBody Delivary_Slot slot) {
        return dss.createSlot(slot);
    }

    @GetMapping("get-slot")
    public List<Delivary_Slot> getAllSlots() {
        return dss.getAllSlots();
    }
}

