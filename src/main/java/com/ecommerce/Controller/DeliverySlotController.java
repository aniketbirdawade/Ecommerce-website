package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Delivary_Slot;
import com.ecommerce.Repository.DelivarySlotRepository;
 
@RestController
@RequestMapping("/api/delivery-slots")
public class DeliverySlotController {
 
    private final DelivarySlotRepository delivarySlotRepository;
 
    public DeliverySlotController(DelivarySlotRepository delivarySlotRepository) {
        this.delivarySlotRepository = delivarySlotRepository;
    }
 
    @PostMapping
    public Delivary_Slot addSlot(@RequestBody Delivary_Slot slot) {
        Delivary_Slot saved = delivarySlotRepository.save(slot);
        return delivarySlotRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Delivary_Slot> getAllSlots() {
        return delivarySlotRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Delivary_Slot getSlotById(@PathVariable int id) {
        return delivarySlotRepository.findById(id).orElse(null);
    }
 
    @PutMapping("/{id}")
    public Delivary_Slot updateSlot(@PathVariable int id, @RequestBody Delivary_Slot updated) {
        Delivary_Slot slot = delivarySlotRepository.findById(id).orElse(null);
        if (slot == null) return null;
        slot.setDate(updated.getDate());
        slot.setStart_time(updated.getStart_time());
        slot.setEnd_time(updated.getEnd_time());
        slot.setMax_order(updated.getMax_order());
        slot.setAvaliable_order(updated.getAvaliable_order());
        return delivarySlotRepository.save(slot);
    }
 
    @DeleteMapping("/{id}")
    public String deleteSlot(@PathVariable int id) {
        delivarySlotRepository.deleteById(id);
        return "Delivery slot deleted successfully";
    }
}