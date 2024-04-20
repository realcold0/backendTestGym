package com.example.backendTestGym.dto;

import lombok.Data;

@Data
public class GymEquipmentDTO {
    private String name;
    private Long gymId;
    private int quantity;
}
