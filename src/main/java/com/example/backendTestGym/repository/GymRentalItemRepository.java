package com.example.backendTestGym.repository;

import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.domain.GymRentalItem;
import com.example.backendTestGym.domain.RentalItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRentalItemRepository extends JpaRepository<GymRentalItem, Long> {
    Optional<GymRentalItem> findByGymAndRentalItem(Gym gym, RentalItem rentalItem);

    List<GymRentalItem> findByRentalItem(RentalItem rentalItem);
}
