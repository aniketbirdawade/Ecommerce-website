package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.DeliverySlot;
import com.ecommerce.Repository.DeliverySlotRepository;
 
@RestController
@RequestMapping("/api/delivery-slots")
public class DeliverySlotController {
 
    private final DeliverySlotRepository deliverySlotRepository;
 
    public DeliverySlotController(DeliverySlotRepository deliverySlotRepository) {
        this.deliverySlotRepository = deliverySlotRepository;
    }
 
    @PostMapping
    public DeliverySlot addSlot(@RequestBody DeliverySlot slot) {
        DeliverySlot saved = deliverySlotRepository.save(slot);
        return deliverySlotRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<DeliverySlot> getAllSlots() {
        return deliverySlotRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public DeliverySlot getSlotById(@PathVariable int id) {
        return deliverySlotRepository.findById(id).orElse(null);
    }
 
    @PutMapping("/{id}")
    public DeliverySlot updateSlot(@PathVariable int id, @RequestBody DeliverySlot updated) {
        DeliverySlot slot = deliverySlotRepository.findById(id).orElse(null);
        if (slot == null) return null;
        slot.setDate(updated.getDate());
        slot.setStart_time(updated.getStart_time());
        slot.setEnd_time(updated.getEnd_time());
        slot.setMax_order(updated.getMax_order());
        slot.setAvaliable_order(updated.getAvaliable_order());
        return deliverySlotRepository.save(slot);
    }
 
    @DeleteMapping("/{id}")
    public String deleteSlot(@PathVariable int id) {
        deliverySlotRepository.deleteById(id);
        return "Delivery slot deleted successfully";
    }
}