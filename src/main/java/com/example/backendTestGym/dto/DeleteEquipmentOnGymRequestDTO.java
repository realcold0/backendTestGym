package com.example.backendTestGym.dto;

import lombok.Data;

@Data
public class DeleteEquipmentOnGymRequestDTO {
    private Long gymId;
    private String name;
}
