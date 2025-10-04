package com.aydanjb.backpicker.gearitem;

import com.aydanjb.backpicker.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GearItemController {

    private final GearItemService gearItemService;

    public GearItemController(GearItemService gearItemService) {
        this.gearItemService = gearItemService;
    }

    @PostMapping("/lists/{listId}/items")
    public ResponseEntity<GearItem> createGearItem(@AuthenticationPrincipal User user,
                                                   @PathVariable Long listId,
                                                   @Valid @RequestBody CreateGearItemRequest request) {
        GearItem item = gearItemService.createGearItem(
                listId,
                user.getId(),
                request.getName(),
                request.getWeight(),
                request.getPrice(),
                request.getQuantity(),
                request.getCategory()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping("/lists/{listId}/items")
    public ResponseEntity<List<GearItem>> getGearListItems(@AuthenticationPrincipal User user,
                                                           @PathVariable Long listId) {
        List<GearItem> items = gearItemService.getGearListItems(listId, user.getId());
        return ResponseEntity.ok(items);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<GearItem> updateGearItem(@AuthenticationPrincipal User user,
                                                   @PathVariable Long id,
                                                   @Valid @RequestBody UpdateGearItemRequest request) {
        GearItem updatedItem = gearItemService.updateGearItem(
                id,
                user.getId(),
                request.getName(),
                request.getWeight(),
                request.getPrice(),
                request.getQuantity(),
                request.getCategory()
        );

        return ResponseEntity.ok(updatedItem);
    }


    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteGearItem(@AuthenticationPrincipal User user,
                                               @PathVariable Long id) {
        gearItemService.deleteGearItem(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}
