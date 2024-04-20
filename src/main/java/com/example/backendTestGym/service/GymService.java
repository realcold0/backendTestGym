package com.example.backendTestGym.service;

import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GymService {
    private final GymRepository gymRepository;

    public void saveGym(Gym gym) {
        gymRepository.save(gym);
    }


}
