package com.ecommerce.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ecommerce.Entity.Delivary_Slot;
import com.ecommerce.Repository.DeliverySlotRepository;

@Service
public class DeliverySlotService {

    private final DeliverySlotRepository dsr;

    public DeliverySlotService(DeliverySlotRepository dsr) {
        this.dsr = dsr;
    }

    public Delivary_Slot createSlot(Delivary_Slot slot) {
        return dsr.save(slot);
    }

    public List<Delivary_Slot> getAllSlots() {
        return dsr.findAll();
    }
}
