package com.example.backendTestGym.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gym {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private float rate = 0.0f;
    private int quantityEquipment = 0;


    public Gym(String name) {
        this.name = name;
        this.rate = 0.0f;
        this.quantityEquipment = 0;
    }
}
