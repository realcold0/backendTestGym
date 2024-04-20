package com.example.backendTestGym.controller;

import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.dto.GymDTO;
import com.example.backendTestGym.service.GymService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final GymService gymService;

    @Autowired
    public AdminController(GymService gymService) {
        this.gymService = gymService;
    }

    @Operation(summary = "헬스장 등록")
    @ApiResponse(responseCode = "200", description = "헬스장 등록 성공")
    @PostMapping("/gym")
    public ResponseEntity<GymDTO> registerGym(@RequestBody GymDTO gymDTO) {
        Gym gym = new Gym(gymDTO.getName());
        gymService.saveGym(gym);
        return ResponseEntity.status(HttpStatus.OK).body(gymDTO);
    }


}
