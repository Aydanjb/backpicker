package com.aydanjb.backpicker.gearitem;

import com.aydanjb.backpicker.gearlist.GearList;
import com.aydanjb.backpicker.gearlist.GearListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GearItemService {
    private final GearItemRepository gearItemRepository;
    private final GearListRepository gearListRepository;

    public GearItemService(GearItemRepository gearItemRepository, GearListRepository gearListRepository) {
        this.gearItemRepository = gearItemRepository;
        this.gearListRepository = gearListRepository;
    }

    public GearItem createGearItem(Long gearListId, Long userId, String name, Double weight, Double price,
                                   Integer quantity, String category) {
        GearList gearList = gearListRepository.findByIdAndUserId(gearListId, userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Gear list not found"
                ));

        GearItem item = new GearItem();
        item.setGearList(gearList);
        item.setName(name);
        item.setWeight(weight);
        item.setPrice(price);
        item.setQuantity(quantity != null ? quantity : 1);
        item.setCategory(category);

        return gearItemRepository.save(item);
    }

    public List<GearItem> getGearListItems(Long gearListId, Long userId) {
        gearListRepository.findByIdAndUserId(gearListId, userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Gear list not found"
                ));

        return gearItemRepository.findAllByGearListId(gearListId);
    }

    public GearItem updateGearItem(Long itemId, Long userId, String name,
                                   Double weight, Double price, Integer quantity,
                                   String category) {
        GearItem item = gearItemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Gear item not found"
                ));

        if (!gearListRepository.existsByIdAndUserId(item.getGearList().getId(), userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        if (name != null) item.setName(name);
        if (weight != null) item.setWeight(weight);
        if (price != null) item.setPrice(price);
        if (quantity != null) item.setQuantity(quantity);
        if (category != null) item.setCategory(category);

        return gearItemRepository.save(item);
    }

    public void deleteGearItem(Long itemId, Long userId) {
        GearItem item = gearItemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Gear item not found"
                ));

        // Verify ownership
        if (!gearListRepository.existsByIdAndUserId(item.getGearList().getId(), userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        gearItemRepository.delete(item);
    }
}


