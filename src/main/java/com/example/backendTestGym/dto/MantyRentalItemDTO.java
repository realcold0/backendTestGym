package com.example.backendTestGym.dto;

import java.util.List;
import lombok.Data;

@Data
public class MantyRentalItemDTO {
    private List<GymIdAndQuantityDTO> GymIdAndQuantitylist;
    private String name;

}
