package com.aydanjb.backpicker.gearlist;

import com.aydanjb.backpicker.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GearListService {
    private final GearListRepository gearListRepository;

    public GearListService(GearListRepository gearListRepository) {
        this.gearListRepository = gearListRepository;
    }

    public GearList createGearList(User user, String name, String description) {
        GearList gearList = new GearList();
        gearList.setUser(user);
        gearList.setName(name);
        gearList.setDescription(description);

        return gearListRepository.save(gearList);
    }

    public List<GearList> getUserGearLists(Long userId) {
        return gearListRepository.findAllByUserId(userId);
    }

    public GearList getGearListById(Long listId, Long userId) {
        return gearListRepository.findByIdAndUserId(listId, userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Gear list not found"
                ));
    }

    public GearList updateGearList(Long listId, Long userId, String name, String description) {
        GearList gearList = getGearListById(listId, userId);

        if (name != null) {
            gearList.setName(name);
        }
        if (description != null) {
            gearList.setDescription(description);
        }

        return gearListRepository.save(gearList);
    }

    public void deleteGearList(Long listId, Long userId) {
        GearList gearList = getGearListById(listId, userId);
        gearListRepository.delete(gearList);
    }
}
