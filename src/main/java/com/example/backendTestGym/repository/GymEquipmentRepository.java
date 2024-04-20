package com.example.backendTestGym.repository;

import com.example.backendTestGym.domain.Equipment;
import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.domain.GymEquipment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymEquipmentRepository extends JpaRepository<GymEquipment, Long> {

    Optional<GymEquipment> findByGymAndEquipment(Gym gym, Equipment equipment);

    void deleteByGymAndEquipment(Gym gym, Equipment equipment);

    List<GymEquipment> findByEquipment(Equipment equipment);
}
