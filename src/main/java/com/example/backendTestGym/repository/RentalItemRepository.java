package com.example.backendTestGym.repository;

import com.example.backendTestGym.domain.RentalItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalItemRepository extends JpaRepository<RentalItem, Long> {
    Optional<RentalItem> findByName(String name);
}
