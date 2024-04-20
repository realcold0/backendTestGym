package com.example.backendTestGym.repository;

import com.example.backendTestGym.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Gym save(Gym gym);


}
