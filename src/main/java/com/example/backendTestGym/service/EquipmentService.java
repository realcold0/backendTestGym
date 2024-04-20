package com.example.backendTestGym.service;

import com.example.backendTestGym.domain.Equipment;
import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.dto.EquipmentDTO;
import com.example.backendTestGym.repository.EquipmentRepository;
import com.example.backendTestGym.repository.GymRepository;
import com.example.backendTestGym.util.exception.GymNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final GymRepository gymRepository;

    public void addEquipmentToGym(EquipmentDTO equipmentDTO) {
        Optional<Gym> optionalGym = gymRepository.findById(equipmentDTO.getGymId());
        if (optionalGym.isPresent()) {
            Gym gym = optionalGym.get();
            Equipment equipment = new Equipment();
            equipment.setName(equipmentDTO.getName());
            equipment.setGym(gym);
            equipment.setQuantity(equipmentDTO.getQuantity());
            equipmentRepository.save(equipment);
        } else {
            throw new GymNotFoundException("헬스장이 존재 하지 않습니다.");
        }
    }

    public void updateQuantityEquipment(EquipmentDTO equipmentDTO) {
        Optional<Gym> optionalGym = gymRepository.findById(equipmentDTO.getGymId());
        if (optionalGym.isPresent()) {
            Optional<Equipment> optionalEquipment = equipmentRepository.findByGymAndName(optionalGym.get(),
                    equipmentDTO.getName());
            if (optionalEquipment.isPresent()) {
                Equipment equipment = optionalEquipment.get();
                equipment.setQuantity(equipmentDTO.getQuantity());
                equipmentRepository.save(equipment);
            } else {
                throw new GymNotFoundException("해당 장비가 존재 하지 않습니다.");
            }
        } else {
            throw new GymNotFoundException("헬스장이 존재 하지 않습니다.");
        }

    }
}
