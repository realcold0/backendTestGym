package com.example.backendTestGym.dto;

import lombok.Data;

@Data
public class DeleteRequestDTO {
    private Long gymId;
    private String name;
}
