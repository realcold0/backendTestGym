package com.example.backendTestGym.service;

import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.repository.GymRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GymService {
    private final GymRepository gymRepository;

    public void saveGym(Gym gym) {
        gymRepository.save(gym);
    }

    public boolean updateGym(Long id, String newName) {
        Optional<Gym> optionalGym = gymRepository.findById(id);
        if (optionalGym.isPresent()) {
            Gym gym = optionalGym.get();
            gym.setName(newName);
            gymRepository.save(gym);
            return true;
        } else {
            return false;
        }
    }
}
