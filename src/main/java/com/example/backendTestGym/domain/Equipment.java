package com.example.backendTestGym.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@NoArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue
    private Long id;
    private String name;


}
