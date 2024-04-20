package com.example.backendTestGym.service;

import com.example.backendTestGym.domain.RentalItem;
import com.example.backendTestGym.repository.RentalItemRepository;
import com.example.backendTestGym.util.exception.RentalItemAlreadyExistsException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalItemService {

    private final RentalItemRepository rentalItemRepository;

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
}
