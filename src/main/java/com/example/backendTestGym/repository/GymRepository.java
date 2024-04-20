package com.example.backendTestGym.repository;

import com.example.backendTestGym.domain.Gym;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Gym save(Gym gym);

    Optional<Gym> findByName(String name);

}
