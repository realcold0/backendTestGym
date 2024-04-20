package com.example.backendTestGym.service;

import com.example.backendTestGym.domain.Equipment;
import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.dto.DeleteRequestDTO;
import com.example.backendTestGym.dto.GymIdAndQuantityDTO;
import com.example.backendTestGym.dto.ManyEquipmentDTO;
import com.example.backendTestGym.dto.OneEquipmentDTO;
import com.example.backendTestGym.repository.EquipmentRepository;
import com.example.backendTestGym.repository.GymRepository;
import com.example.backendTestGym.util.exception.EquipmentAlreadyExistsException;
import com.example.backendTestGym.util.exception.EquipmentNotExistsException;
import com.example.backendTestGym.util.exception.GymNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final GymRepository gymRepository;

    public void addEquipmentToGym(String name, int quantity, Long gymId) {
        Optional<Gym> optionalGym = gymRepository.findById(gymId);
        if (optionalGym.isPresent()) {
            Gym gym = optionalGym.get();
            Optional<Equipment> optionalEquipment = equipmentRepository.findByGymAndName(gym, name);
            if (optionalEquipment.isPresent()) {
                throw new EquipmentAlreadyExistsException("이미 존재하는 운동기구입니다.");
            } else {
                Equipment equipment = new Equipment();
                equipment.setName(name);
                equipment.setGym(gym);
                equipment.setQuantity(quantity);
                equipmentRepository.save(equipment);
            }
        } else {
            throw new GymNotFoundException("헬스장이 존재 하지 않습니다.");
        }
    }

    public void updateQuantityEquipment(OneEquipmentDTO oneEquipmentDTO) {
        Optional<Gym> optionalGym = gymRepository.findById(oneEquipmentDTO.getGymId());
        if (optionalGym.isPresent()) {
            Optional<Equipment> optionalEquipment = equipmentRepository.findByGymAndName(optionalGym.get(),
                    oneEquipmentDTO.getName());
            if (optionalEquipment.isPresent()) {
                Equipment equipment = optionalEquipment.get();
                equipment.setQuantity(oneEquipmentDTO.getQuantity());
                equipmentRepository.save(equipment);
            } else {
                throw new GymNotFoundException("해당 장비가 존재 하지 않습니다.");
            }
        } else {
            throw new GymNotFoundException("헬스장이 존재 하지 않습니다.");
        }

    }


    @Transactional
    public void addEquipmenttoManyGym(ManyEquipmentDTO manyEquipmentDTO) {
        String equipName = manyEquipmentDTO.getName();
        List<GymIdAndQuantityDTO> gymIdAndQuantitylist = manyEquipmentDTO.getGymIdAndQuantitylist();

        for (GymIdAndQuantityDTO gymIdAndQuantityDTO : gymIdAndQuantitylist) {
            addEquipmentToGym(equipName, gymIdAndQuantityDTO.getQuantity(),
                    gymIdAndQuantityDTO.getGymId());
        }
    }

    public void deleteEquipmenttoManyGym(DeleteRequestDTO deleteRequestDTO) {
        //id가 유효한지 검사하고
        //해당하는 이름의 장비 제거
        Optional<Gym> optionalGym = gymRepository.findById(deleteRequestDTO.getGymId());
        if (optionalGym.isPresent()) {
            Gym gym = optionalGym.get();
            Optional<Equipment> optionalEquipment = equipmentRepository.findByGymAndName(gym,
                    deleteRequestDTO.getName());
            if (optionalEquipment.isPresent()) {
                equipmentRepository.delete(optionalEquipment.get());
            } else {
                throw new EquipmentNotExistsException("존재하지 않는 운동 기구 입니다.");
            }
        } else {
            throw new GymNotFoundException("헬스장이 존재 하지 않습니다.");
        }
    }
}
