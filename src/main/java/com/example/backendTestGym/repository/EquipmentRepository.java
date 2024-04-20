package com.example.backendTestGym.repository;

import com.example.backendTestGym.domain.Equipment;
import com.example.backendTestGym.domain.Gym;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByGymAndName(Gym gym, String name);

}
