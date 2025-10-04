package com.aydanjb.backpicker.gearlist;

import com.aydanjb.backpicker.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class GearListController {
    private final GearListService gearListService;

    public GearListController(GearListService gearListService) {
        this.gearListService = gearListService;
    }

    @PostMapping
    public ResponseEntity<GearList> createGearList(@AuthenticationPrincipal User user, @Valid @RequestBody CreateGearListRequest request) {
        GearList gearList = gearListService.createGearList(user, request.getName(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(gearList);

    }

    @GetMapping
    public ResponseEntity<List<GearList>> getUserGearLists(@AuthenticationPrincipal User user) {
        List<GearList> gearLists = gearListService.getUserGearLists(user.getId());
        return ResponseEntity.ok(gearLists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GearList> getGearListById(
            @AuthenticationPrincipal User user,
            @PathVariable Long id) {

        GearList gearList = gearListService.getGearListById(id, user.getId());
        return ResponseEntity.ok(gearList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGearList(
            @AuthenticationPrincipal User user,
            @PathVariable Long id) {

        gearListService.deleteGearList(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}
