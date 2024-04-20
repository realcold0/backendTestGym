package com.example.backendTestGym.controller.admin;

import com.example.backendTestGym.dto.MantyRentalItemDTO;
import com.example.backendTestGym.service.RentalItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자 헬스장 대여 물품")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminGymRentalItemController {
    private final RentalItemService rentalItemService;


    @Operation(summary = "여러 헬스장에 대여물품 등록")
    @ApiResponse(responseCode = "200", description = "모두 운동기구 등록 성공")
    @PostMapping("/rental-many")
    public ResponseEntity<MantyRentalItemDTO> registerRentalToGym(@RequestBody MantyRentalItemDTO manyRentalItemDTO) {
        rentalItemService.addGymRentalItemQuantityToManyGym(manyRentalItemDTO);
        return ResponseEntity.status(HttpStatus.OK).body(manyRentalItemDTO);
    }
}
