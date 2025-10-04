package com.aydanjb.backpicker.gearitem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GearItemRepository extends JpaRepository<GearItem, Long> {
    List<GearItem> findAllByGearListId(Long gearListId);

    Optional<GearItem> findByIdAndGearListId(Long id, Long gearListId);
}
