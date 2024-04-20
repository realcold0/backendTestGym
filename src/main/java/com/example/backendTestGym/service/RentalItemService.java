package com.example.backendTestGym.service;

import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.domain.GymRentalItem;
import com.example.backendTestGym.domain.RentalItem;
import com.example.backendTestGym.dto.GymIdAndQuantityDTO;
import com.example.backendTestGym.dto.MantyRentalItemDTO;
import com.example.backendTestGym.repository.GymRentalItemRepository;
import com.example.backendTestGym.repository.GymRepository;
import com.example.backendTestGym.repository.RentalItemRepository;
import com.example.backendTestGym.util.exception.GymNotFoundException;
import com.example.backendTestGym.util.exception.RentalItemAlreadyExistsException;
import com.example.backendTestGym.util.exception.RentalItemNotExistsException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalItemService {

    private final RentalItemRepository rentalItemRepository;
    private final GymRepository gymRepository;
    private final GymRentalItemRepository gymRentalItemRepository;

    public void addRentalItem(String name) {
        Optional<RentalItem> optionalEquipment = rentalItemRepository.findByName(name);
        if (optionalEquipment.isPresent()) {
            throw new RentalItemAlreadyExistsException("이미 존재하는 대여물품입니다.");
        } else {
            //장비 db에 저장
            RentalItem rentalItem = new RentalItem();
            rentalItem.setName(name);
            rentalItemRepository.save(rentalItem);
        }
    }

    public void addRentalItemToGym(Long gymId, String name, int quantity) {
        Optional<Gym> optionalGym = gymRepository.findById(gymId);
        if (optionalGym.isPresent()) {
            Optional<RentalItem> optionalRentalItem = rentalItemRepository.findByName(name);
            if (optionalRentalItem.isPresent()) {
                Gym gym = optionalGym.get();
                RentalItem rentalItem = optionalRentalItem.get();
                Optional<GymRentalItem> optionalGymRentalItem = gymRentalItemRepository.findByGymAndRentalItem(gym,
                        rentalItem);
                if (optionalGymRentalItem.isPresent()) {
                    throw new RentalItemAlreadyExistsException("이미 해당 장비가 존재합니다.");
                } else {
                    GymRentalItem gymRentalItem = new GymRentalItem();
                    gymRentalItem.setGym(gym);
                    gymRentalItem.setRentalItem(rentalItem);
                    gymRentalItem.setQuantity(quantity);
                    gymRentalItemRepository.save(gymRentalItem);
                }

            } else {
                throw new RentalItemNotExistsException("해당 대여물품이 존재 하지 않습니다.");
            }
        } else {
            throw new GymNotFoundException("헬스장이 존재 하지 않습니다.");
        }
    }

    @Transactional
    public void addGymRentalItemQuantityToManyGym(MantyRentalItemDTO manyRentalItemDTO) {
        String rentalItemName = manyRentalItemDTO.getName();
        List<GymIdAndQuantityDTO> gymIdAndQuantitylist = manyRentalItemDTO.getGymIdAndQuantitylist();

        for (GymIdAndQuantityDTO gymIdAndQuantityDTO : gymIdAndQuantitylist) {
            addRentalItemToGym(gymIdAndQuantityDTO.getGymId(), rentalItemName, gymIdAndQuantityDTO.getQuantity());
        }
    }


}
