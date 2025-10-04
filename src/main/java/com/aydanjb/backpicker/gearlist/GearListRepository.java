package com.aydanjb.backpicker.gearlist;

import com.aydanjb.backpicker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GearListRepository extends JpaRepository<GearList, Long> {
    List<GearList> findAllByUserId(Long userId);
    Optional<GearList> findByIdAndUserId(Long id, Long userId) ;
    boolean existsByIdAndUserId(Long id, Long userId);
}
