package com.example.backendTestGym.service;

import com.example.backendTestGym.domain.Equipment;
import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.domain.GymEquipment;
import com.example.backendTestGym.dto.DeleteEquipmentOnGymRequestDTO;
import com.example.backendTestGym.dto.GymEquipmentDTO;
import com.example.backendTestGym.dto.GymIdAndQuantityDTO;
import com.example.backendTestGym.dto.ManyEquipmentDTO;
import com.example.backendTestGym.repository.EquipmentRepository;
import com.example.backendTestGym.repository.GymEquipmentRepository;
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
    private final GymEquipmentRepository gymEquipmentRepository;

    public void addEquipment(String name) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findByName(name);
        if (optionalEquipment.isPresent()) {
            throw new EquipmentAlreadyExistsException("이미 존재하는 운동기구입니다.");
        } else {
            //장비 db에 저장
            Equipment equipment = new Equipment();
            equipment.setName(name);
            equipmentRepository.save(equipment);

        }
    }

    public void addEquipmentToGym(Long gymId, String name, int quantity) {
        Optional<Gym> optionalGym = gymRepository.findById(gymId);
        if (optionalGym.isPresent()) {
            Optional<Equipment> optionalEquipment = equipmentRepository.findByName(name);
            if (optionalEquipment.isPresent()) {
                Gym gym = optionalGym.get();
                Equipment equipment = optionalEquipment.get();
                Optional<GymEquipment> optionalGymEquipment = gymEquipmentRepository.findByGymAndEquipment(gym,
                        equipment);

                if (optionalGymEquipment.isPresent()) {
                    throw new EquipmentAlreadyExistsException("이미 해당 장비가 존재합니다.");
                } else {
                    GymEquipment gymEquipment = new GymEquipment();
                    gymEquipment.setGym(gym);
                    gymEquipment.setEquipment(equipment);
                    gymEquipment.setQuantity(quantity);
                    gymEquipmentRepository.save(gymEquipment);
                }

            } else {
                throw new GymNotFoundException("해당 장비가 존재 하지 않습니다.");
            }
        } else {
            throw new GymNotFoundException("헬스장이 존재 하지 않습니다.");
        }
    }

    @Transactional
    public void updateQuantityEquipment(GymEquipmentDTO gymEquipmentDTO) {
        Optional<Gym> optionalGym = gymRepository.findById(gymEquipmentDTO.getGymId());
        if (optionalGym.isPresent()) {
            Optional<Equipment> optionalEquipment = equipmentRepository.findByName(gymEquipmentDTO.getName());
            if (optionalEquipment.isPresent()) {//존재하는 장비라면
                Gym gym = optionalGym.get();
                Equipment equipment = optionalEquipment.get();
                Optional<GymEquipment> optionalGymEquipment = gymEquipmentRepository.findByGymAndEquipment(gym,
                        equipment);

                if (optionalGymEquipment.isPresent()) {
                    GymEquipment gymEquipment = optionalGymEquipment.get();
                    gymEquipment.setQuantity(gymEquipmentDTO.getQuantity());
                    gymEquipmentRepository.save(gymEquipment);
                } else {
                    throw new GymNotFoundException("해당 장비가 존재 하지 않습니다.");
                }

            } else {
                throw new GymNotFoundException("해당 장비가 존재 하지 않습니다.");
            }
        } else {
            throw new GymNotFoundException("헬스장이 존재 하지 않습니다.");
        }

    }


    @Transactional
    public void addGymEquipmentQuantityFromManyGym(ManyEquipmentDTO manyEquipmentDTO) {
        String equipName = manyEquipmentDTO.getName();
        List<GymIdAndQuantityDTO> gymIdAndQuantitylist = manyEquipmentDTO.getGymIdAndQuantitylist();

        for (GymIdAndQuantityDTO gymIdAndQuantityDTO : gymIdAndQuantitylist) {
            addEquipmentToGym(gymIdAndQuantityDTO.getGymId(), equipName, gymIdAndQuantityDTO.getQuantity());
        }
    }

    @Transactional
    public void deleteEquipmentOnGym(DeleteEquipmentOnGymRequestDTO deleteEquipmentOnGymRequestDTO) {
        //id가 유효한지 검사하고
        //해당하는 이름의 장비 제거
        Optional<Gym> optionalGym = gymRepository.findById(deleteEquipmentOnGymRequestDTO.getGymId());
        if (optionalGym.isPresent()) {
            Gym gym = optionalGym.get();
            Optional<Equipment> optionalEquipment = equipmentRepository.findByName(
                    deleteEquipmentOnGymRequestDTO.getName());
            if (optionalEquipment.isPresent()) {
                Equipment equipment = optionalEquipment.get();
                gymEquipmentRepository.deleteByGymAndEquipment(gym, equipment);
            } else {
                throw new EquipmentNotExistsException("존재하지 않는 운동 기구 입니다.");
            }
        } else {
            throw new GymNotFoundException("헬스장이 존재 하지 않습니다.");
        }
    }
}
